package com.triloyed.booksystembookservice.service;

import com.triloyed.booksystembookservice.dao.BookDao;
import com.triloyed.booksystembookservice.model.Book;
import com.triloyed.booksystembookservice.viewmodel.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookService {

  BookDao bookDao;

  @Autowired
  public BookService(BookDao bookDao) {
    this.bookDao = bookDao;
  }

  public BookViewModel createBook(BookViewModel bookViewModel) {

    Book book = new Book();
    book.setAuthor(bookViewModel.getAuthor());
    book.setTitle(bookViewModel.getTitle());
    Book createdBook = bookDao.createBook(book);

    bookViewModel.setBookId(createdBook.getBookId());
    return bookViewModel;
  }

  public BookViewModel getBook(int id) {

    Book book = bookDao.getBook(id);
    if (book == null) {
      return null;
    } else {
        return buildBookViewModel(book);
    }
  }

  public List<BookViewModel> getAllBooks() {

    List<Book> bookList = bookDao.getAllBooks();
    List<BookViewModel> bvmList = new ArrayList<>();

    for (Book book: bookList) {
      BookViewModel bookViewModel = buildBookViewModel(book);
      bvmList.add(bookViewModel);
    }
    return bvmList;

  }

  public void updateBook(BookViewModel bookViewModel) {

    Book book = new Book();
    book.setTitle(bookViewModel.getTitle());
    book.setAuthor(bookViewModel.getAuthor());
    book.setBookId(bookViewModel.getBookId());
    bookDao.updateBook(book);

  }

  public void deleteBook(int bookId) {

    bookDao.deleteBook(bookId);

  }

  private BookViewModel buildBookViewModel(Book book) {

    BookViewModel bookViewModel = new BookViewModel();
    bookViewModel.setBookId(book.getBookId());
    bookViewModel.setTitle(book.getTitle());
    bookViewModel.setAuthor(book.getAuthor());

    return bookViewModel;
  }
}

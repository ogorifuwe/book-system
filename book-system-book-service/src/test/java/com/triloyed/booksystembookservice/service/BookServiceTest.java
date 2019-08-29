package com.triloyed.booksystembookservice.service;

import com.triloyed.booksystembookservice.dao.BookDao;
import com.triloyed.booksystembookservice.dao.BookDaoJdbcTemplateImpl;
import com.triloyed.booksystembookservice.model.Book;
import com.triloyed.booksystembookservice.viewmodel.BookViewModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class BookServiceTest {

  BookDao mockBookDao;
  BookService mockBookService;

  @Before
  public void setUp() throws Exception {

    setUpBookDaoMock();
    mockBookService = new BookService(mockBookDao);
  }

  @Test
  public void createGetBook() {

    BookViewModel bvm = new BookViewModel();
    bvm.setTitle("Anthills of the Savannah");
    bvm.setAuthor("Chinua Achebe");
    bvm = mockBookService.createBook(bvm);

    BookViewModel bvmTest = mockBookService.getBook(bvm.getBookId());
    assertEquals(bvm, bvmTest);

  }

  @Test
  public void getAllBooks() {

    List<BookViewModel> bvmList = mockBookService.getAllBooks();
    assertEquals(2, bvmList.size());
  }


  private void setUpBookDaoMock() {

    mockBookDao = mock(BookDaoJdbcTemplateImpl.class);

    Book book = new Book();
    book.setBookId(1);
    book.setTitle("Anthills of the Savannah");
    book.setAuthor("Chinua Achebe");

    Book book2 = new Book();
    book2.setTitle("Anthills of the Savannah");
    book2.setAuthor("Chinua Achebe");

    Book book3 = new Book();
    book3.setBookId(2);
    book3.setTitle("The Interpreters");
    book3.setAuthor("Wole Soyinka");

    Book book4 = new Book();
    book4.setTitle("The Interpreters");
    book4.setAuthor("Wole Soyinka");

    List<Book> bookList = new ArrayList<>();
    bookList.add(book);
    bookList.add(book3);

    doReturn(book).when(mockBookDao).createBook(book2);
    doReturn(book).when(mockBookDao).getBook(1);
    doReturn(book3).when(mockBookDao).createBook(book4);
    doReturn(book3).when(mockBookDao).getBook(2);
    doReturn(bookList).when(mockBookDao).getAllBooks();
  }


}
package com.triloyed.booksystembookservice.dao;

import com.triloyed.booksystembookservice.model.Book;

import java.util.List;

public interface BookDao {

  Book createBook(Book book);
  Book getBook(int bookId);
  List<Book> getAllBooks();
  void updateBook(Book book);
  void deleteBook(int bookId);

}

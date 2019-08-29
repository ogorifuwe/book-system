package com.triloyed.booksystembookservice.dao;

import com.triloyed.booksystembookservice.model.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoTest {

  @Autowired
  private BookDao dao;

  @Before
  public void setUp() {

    List<Book> bList = dao.getAllBooks();

    bList.stream()
            .forEach(book -> dao.deleteBook(book.getBookId()));

  }

  @After
  public void tearDown() {
  }

  @Test
  public void createGetDeleteBook() {

    Book book = new Book();
    book.setBookId(1);
    book.setTitle("Things Fall Apart");
    book.setAuthor("Chinua Achebe");
    book = dao.createBook(book);

    Book book2 = new Book();
    book2.setBookId(2);
    book2.setTitle("Americana");
    book2.setAuthor("Chimamanda Adichie");
    book2 = dao.createBook(book2);

    Book bookTest = dao.getBook(book.getBookId());
    assertEquals(bookTest, book);

    dao.deleteBook(book.getBookId());
    bookTest = dao.getBook(book.getBookId());
    assertNull(bookTest);

  }

  @Test
  public void getAllBooks() {

    Book book = new Book();
    book.setBookId(1);
    book.setTitle("Things Fall Apart");
    book.setAuthor("Chinua Achebe");
    book = dao.createBook(book);

    Book book2 = new Book();
    book2.setBookId(2);
    book2.setTitle("Americana");
    book2.setAuthor("Chimamanda Adichie");
    book2 = dao.createBook(book2);

    List<Book> bList = dao.getAllBooks();
    assertEquals(bList.size(), 2);

  }

  @Test
  public void updateBook() {

    Book book = new Book();
    book.setBookId(1);
    book.setTitle("Things Fall Apart");
    book.setAuthor("Chinua Achebe");
    book = dao.createBook(book);

    book.setTitle("There was a country");
    dao.updateBook(book);

    Book bookTest = dao.getBook(book.getBookId());
    assertEquals(bookTest, book);
  }

}

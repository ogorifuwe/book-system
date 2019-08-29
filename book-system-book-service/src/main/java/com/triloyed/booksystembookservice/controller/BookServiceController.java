package com.triloyed.booksystembookservice.controller;

import com.triloyed.booksystembookservice.service.BookService;
import com.triloyed.booksystembookservice.util.messages.Note;
import com.triloyed.booksystembookservice.viewmodel.BookViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
public class BookServiceController {

  public static final String EXCHANGE = "note-queue-exchange";
  public static final String ROUTING_KEY = "note.add.book.controller";

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private BookService bookService;

  public BookServiceController(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @RequestMapping(value = "/books", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  public BookViewModel createBook(@RequestBody @Valid BookViewModel reqBVM) {

    /* create message to send to note creation queue */
    Note msg = new Note(reqBVM.getBookId());
    System.out.println("Sending message...");
    rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg);
    System.out.println("Message Sent");

    BookViewModel resBVM = bookService.createBook(reqBVM);
    return resBVM;
  }

  @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public BookViewModel getBook(@PathVariable("id") int id) {

    if (id < 1) {
      throw new IllegalArgumentException("BookId must be greater than 0");
    }

    BookViewModel resBVM = bookService.getBook(id);
    return resBVM;

  }

  @RequestMapping(value = "/books", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public List<BookViewModel> getAllBooks() {

    List<BookViewModel> resBVMList = bookService.getAllBooks();
    if (resBVMList.size() == 0 || resBVMList == null) {
      throw new IllegalArgumentException("No books was found in the Book System.");
    }

    return resBVMList;

  }

  @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void updateBook(@RequestBody @Valid BookViewModel reqBVM, @PathVariable int id) {
    /* make sure the ID on the path matches the ID of the book view model object */
    if (id != reqBVM.getBookId()) {
      throw new IllegalArgumentException("Book ID on path must match the ID in the Book object.");
    }

    /* in a real world application we would update the entry in the backing data store */
    bookService.updateBook(reqBVM);

  }

  @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteBook(@PathVariable("id") int id) {

    bookService.deleteBook(id);

  }
}

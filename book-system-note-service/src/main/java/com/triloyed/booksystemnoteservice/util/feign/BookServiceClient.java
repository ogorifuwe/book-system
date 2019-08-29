package com.triloyed.booksystemnoteservice.util.feign;

import com.triloyed.booksystemnoteservice.viewmodel.BookViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "book-system-book-service")
public interface BookServiceClient {

  @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
  public String getNotesByBook(@PathVariable("id") int bookId);

  @RequestMapping(value = "/books", method = RequestMethod.GET)
  public List<BookViewModel> getAllBooks();
}

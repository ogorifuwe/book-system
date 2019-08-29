package com.triloyed.booksystemnoteservice.service;

import com.triloyed.booksystemnoteservice.dao.NoteDao;
import com.triloyed.booksystemnoteservice.util.feign.BookServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoteService {

  NoteDao noteDao;

  @Autowired
  BookServiceClient client;

  @Autowired
  public NoteService(NoteDao noteDao, BookServiceClient client) {

    this.noteDao = noteDao;
    this.client = client;

  }

  /* create a new Boo\k  */
}

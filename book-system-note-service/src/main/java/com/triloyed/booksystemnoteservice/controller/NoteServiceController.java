package com.triloyed.booksystemnoteservice.controller;

import com.triloyed.booksystemnoteservice.viewmodel.BookViewModel;
import com.triloyed.booksystemnoteservice.dao.NoteDao;
import com.triloyed.booksystemnoteservice.model.Note;
import com.triloyed.booksystemnoteservice.util.feign.BookServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RefreshScope
public class NoteServiceController {

  @Autowired
  private NoteDao noteDao;

  @Autowired
  private final BookServiceClient client;


  public NoteServiceController(BookServiceClient client) {
    this.client = client;
  }

  @RequestMapping(value = "/notes", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  public Note createNote(@RequestBody @Valid Note note) {

    note = noteDao.createNote(note);
    return note;

  }

  @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public Note getNote(@PathVariable("id") int noteId) {

    if (noteId < 1) {
      throw new IllegalArgumentException("NoteId must be greater than 0");
    }

    Note note = noteDao.getNote(noteId);
    return note;

  }

  @RequestMapping(value = "/notes/book/{book_id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public String getsNotesByBook(@PathVariable("book_id") int bookId) {

    return client.getNotesByBook(bookId);

  }

  @RequestMapping(value = "/notes", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public List<BookViewModel> getAllNotes() {

    //List<Note> noteList = noteDao.getAllNotes();
    List<BookViewModel> noteList = client.getAllBooks();

    if (noteList.size() == 0 || noteList == null) {
      throw new IllegalArgumentException("No Note was found int the Book System.");
    }

    return noteList;

  }

  @RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void updateNote(@RequestBody @Valid Note note, @PathVariable("id") int noteId) {
    /* ensure that noteId on the path matches the id of the motorcycle object */
    if (noteId != note.getNoteId()) {
      throw new IllegalArgumentException("Note ID on path must match the ID in the Note object.");
    }

    noteDao.updateNote(note);

  }

  @RequestMapping(value = "/notes/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteNote(@PathVariable("id") int noteId) {

    noteDao.deleteNote(noteId);

  }
}

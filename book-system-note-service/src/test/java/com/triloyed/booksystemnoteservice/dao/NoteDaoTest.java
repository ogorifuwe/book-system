package com.triloyed.booksystemnoteservice.dao;

import com.triloyed.booksystemnoteservice.model.Note;
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
public class NoteDaoTest {

  @Autowired
  NoteDao dao;

  @Before
  public void setUp() {

    List<Note> nList = dao.getAllNotes();
    nList.stream()
            .forEach(note -> dao.deleteNote(note.getNoteId()));

  }

  @After
  public void tearDown() {
  }

  @Test()
  public void createGetDelete() {

    Note note = new Note();
    note.setNoteId(1);
    note.setBookId(2);
    note.setNote("Note 1");
    note = dao.createNote(note);

    Note note2 = new Note();
    note2.setNoteId(2);
    note2.setBookId(1);
    note2.setNote("Note 2");
    note2 = dao.createNote(note2);

    Note noteTest = dao.getNote(note.getNoteId());
    assertEquals(noteTest, note);

    dao.deleteNote(note.getNoteId());
    noteTest = dao.getNote(note.getNoteId());
    assertNull(noteTest);

  }

  @Test()
  public void getNoteByBook() {

    Note note = new Note();
    note.setNoteId(1);
    note.setBookId(2);
    note.setNote("Note 1");
    note = dao.createNote(note);

    Note note2 = new Note();
    note2.setNoteId(2);
    note2.setBookId(1);
    note2.setNote("Note 2");
    note2 = dao.createNote(note2);

    Note noteTest = dao.getNoteByBook(note2.getBookId());
    assertEquals(noteTest, note2);

  }

  @Test
  public void getAllNotes() {

    Note note = new Note();
    note.setNoteId(1);
    note.setBookId(2);
    note.setNote("Note 1");
    note = dao.createNote(note);

    Note note2 = new Note();
    note2.setNoteId(2);
    note2.setBookId(1);
    note2.setNote("Note 2");
    note2 = dao.createNote(note2);

    List<Note> nList = dao.getAllNotes();
    assertEquals(nList.size(), 2);

  }

  @Test
  public void updateNote() {

    Note note = new Note();
    note.setNoteId(1);
    note.setBookId(2);
    note.setNote("Note 1");
    note = dao.createNote(note);

    note.setBookId(1);
    note.setNote("Note 2");

    dao.updateNote(note);
    Note noteTest = dao.getNoteByBook(note.getBookId());
    assertEquals(noteTest, note);

  }

}

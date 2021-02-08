package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NoteMapper noteMapper;
    private UserService userService;


    public NoteService(NoteMapper noteMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    // GET Note by user id
    public List<Note> getNotesByUserId(Integer userId){

        return noteMapper.getNotesByUserId(userId);

    }

    // GET All Notes
    public List<Note> getAllNotes(){
        return noteMapper.getAllNotes();
    }

    // INSERT OR UPDATE notes
    public boolean insertOrUpdateNote(NoteModel newNote, String username){

       //for get user id
        Integer userId = userService.getUserByUsername(username).getUserId();
        if (newNote.getNoteId()==null){

            // add new note
            Note note = new Note();
            note.setUserId(userId);
            note.setNoteTitle(newNote.getNoteTitle());
            note.setNoteDescription(newNote.getNoteDescription());
            noteMapper.addNote(note);


        }else{

            //update note
            noteMapper.updateNote(newNote.getNoteId(), newNote.getNoteTitle(), newNote.getNoteDescription());

        }

        return true;
    }

    public boolean deleteNote(Integer noteId){
        noteMapper.deleteNote(noteId);
        return true;
    }



}

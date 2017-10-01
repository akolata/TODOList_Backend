package com.company.controller;

import com.company.domain.TODONote;
import com.company.repository.TODONotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller handling request from frontend
 * Created by Aleksander on 01.10.2017.
 */
@RestController
@RequestMapping("/api/todo")
public class ToDoNotesController {

    private TODONotesRepository todoNotesRepository;

    @Autowired
    public ToDoNotesController(TODONotesRepository todoNotesRepository) {
        this.todoNotesRepository = todoNotesRepository;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<TODONote> getAllNotes(){
        return this.todoNotesRepository.findAll();
    }

    @GetMapping("/count")
    public long count() {
        return this.todoNotesRepository.count();
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody TODONote todoFromRequest){

        TODONote todo;

        if(noteExists(todoFromRequest.getId())){
            todo = this.todoNotesRepository.findOne(todoFromRequest.getId());
            todo.update(todoFromRequest);
            this.todoNotesRepository.save(todo);

            return ResponseEntity.ok(todo);
        }else{
            return new ResponseEntity<>("Note with given id not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<TODONote> add(@RequestBody TODONote todoFromRequest) {

        TODONote todo = this.todoNotesRepository.save(todoFromRequest);

        return ResponseEntity.ok(todo);
    }

    @GetMapping("/search")
    public List<TODONote> find(@RequestParam("title") String title){
        return this.todoNotesRepository.findAllByTitleContainingIgnoreCase(title);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") String id){

         if(id == null){
             return new ResponseEntity<>("No id provided", HttpStatus.BAD_REQUEST);
         }else{

             TODONote todoToDelete = this.todoNotesRepository.findOne(id);

             if(todoToDelete == null){
                 return new ResponseEntity<>("Note with id "  + id + " not found", HttpStatus.BAD_REQUEST);
             }else{
                 this.todoNotesRepository.delete(todoToDelete);
                 return ResponseEntity.ok("Note with id " + id + " deleted");
             }
         }
    }

    private boolean noteExists(String id){
        if(id != null){
            return this.todoNotesRepository.findOne(id) != null;
        }else{
            return false;
        }
    }

}

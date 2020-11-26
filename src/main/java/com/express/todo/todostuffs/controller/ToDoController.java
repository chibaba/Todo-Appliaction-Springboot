package com.express.todo.todostuffs.controller;

import com.express.todo.todostuffs.domain.ToDo;
import com.express.todo.todostuffs.repository.CommonRepository;
import com.express.todo.todostuffs.validation.ToDoValidationError;
import com.express.todo.todostuffs.validation.ToDoValidationErrorBuilder;
import com.sun.tools.javac.comp.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/api")
public class ToDoController {
    private CommonRepository<Todo> repository;

    @Autowired
    public ToDoController(CommonRepository<ToDo> repository) {
        this.repository = repository;
    }

    @GetMapping("/todo")
    public ResponseEntity<Iterable<ToDo>> getToDos(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable String id) {
        return ResponseEntity.ok(repository.findById(id));
    }

    @PatchMapping("/todo/{id}")
    public ResponseEntity<ToDo> setCompleted(@PathVariable String id) {
        ToDo result = repository.findById(id);
        result.setCompleted(true);
        repository.save(result);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(result.getId()).toUri();
        return  ResponseEntity.ok().header("Location", location.toString()).
                build();
    }
    @RequestMapping(value="/todo", method = {RequestMethod.POST, RequestMethod.PUT})
    public  ResponseEntity <?> createToDo(@Valid @RequestMethod ToDo toDo, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().
                    body(ToDoValidationErrorBuilder.fromBindingErrors(errors));
        }
        ToDo result = repository.save(toDo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id)")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/todo")
    public ResponseEntity<ToDo> deleteToDo(@RequestBody Todo toDo){
        repository.delete(toDo);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ToDoValidationError handleException(Exception exception) {
        return new ToDoValidationError(exception.getMessage());
    }
}

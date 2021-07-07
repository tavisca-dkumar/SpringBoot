package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TodoController {

    @Autowired
    Repository repo;
    @GetMapping("/todo")
    public ResponseEntity<ArrayList<Todo>> Todo() {
        if(repo.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return  new ResponseEntity<ArrayList<Todo>>(repo.todoItems(), HttpStatus.OK) ;
    }
    @GetMapping("/todo/{id}")
    public  ResponseEntity<Todo> getItemById(@PathVariable int id)
    {
        if(repo.isPresent(id))
            return new ResponseEntity<>(repo.getTodoItem(id),HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/todo")
    public  ResponseEntity  addTodo(@RequestBody Todo todoObj){

        if(repo.addTodoItem(todoObj))
            return new ResponseEntity(HttpStatus.CREATED);
        else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/todo")
    public ResponseEntity updateTodo(@RequestBody Todo todoObj){
       if(repo.updateTodoItem(todoObj))
        return new ResponseEntity(HttpStatus.OK) ;
       else
           return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/todo/{id}")
    public ResponseEntity deleteTodo(@PathVariable int id){
        if(repo.deleteTodoItem(id))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
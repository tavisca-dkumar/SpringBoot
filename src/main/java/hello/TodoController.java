package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TodoController {

    @Autowired
    Repository repo;
    @GetMapping("/todo")
    public ResponseEntity<ArrayList<Todo>> Todo() {
        return  new ResponseEntity<ArrayList<Todo>>(repo.todoItems(), HttpStatus.OK) ;
    }
    @GetMapping("/todo/{id}")
    public  ResponseEntity<Todo> getItemById(@PathVariable int id)
    {
        Todo item=repo.getTodoItem(id);
        if(item!=null)
            return new ResponseEntity<>(item,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/todo")
    public  ResponseEntity<ArrayList<Todo>>  addTodo(@RequestBody Todo todoObj){

        if(repo.addTodoItem(todoObj))
            return new ResponseEntity<ArrayList<Todo>>(repo.todoItems(),HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.INSUFFICIENT_STORAGE);
    }
    @PutMapping("/todo")
    public ResponseEntity<ArrayList<Todo>> updateTodo(@RequestBody Todo todoObj){
       if(repo.updateTodoItem(todoObj))
        return new ResponseEntity<ArrayList<Todo>>(repo.todoItems(),HttpStatus.OK) ;
       else
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/todo/{id}")
    public ResponseEntity<ArrayList<Todo>> deleteTodo(@PathVariable int id){
        if(!repo.deleteTodoItem(id))
            return new ResponseEntity<ArrayList<Todo>>(repo.todoItems(),HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
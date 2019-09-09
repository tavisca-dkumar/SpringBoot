package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TodoController {

    @Autowired
    Repository repo;
    @GetMapping("/todo")
    public ArrayList<Todo> Todo() {
        return repo.todoItems();
    }
    @PostMapping("/todo")
    public  ArrayList<Todo> addTodo(@RequestBody Todo todoObj){
        repo.addTodoItem(todoObj);
        return repo.todoItems();
    }
    @PutMapping("/todo")
    public ArrayList<Todo> updateTodo(@RequestBody Todo todoObj){
        repo.updateTodoItem(todoObj);
        return repo.todoItems();
    }
    @DeleteMapping("/todo/{id}")
    public ArrayList<Todo> deleteTodo(@PathVariable int id){
        repo.deleteTodoItem(id);
        return repo.todoItems();
    }

}
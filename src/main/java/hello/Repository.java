package hello;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
@Component
public class Repository {
    private static final HashMap<Integer,Todo> todo = new HashMap<Integer, Todo>();

    public ArrayList<Todo> todoItems()
    {
        ArrayList<Todo> todoList = new ArrayList<>(todo.values());
        return todoList;
    }
    public void addTodoItem(Todo todoObj)
    {
        todo.put(todoObj.getTodoId(),todoObj);
    }
    public void deleteTodoItem(int id)
    {
        todo.remove(id);
    }
    public void updateTodoItem(Todo todoObj)
    {
        todo.put(todoObj.getTodoId(),todoObj);

    }
}

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
    public  Todo getTodoItem(int id)
    {
        return todo.get(id);
    }
    public boolean addTodoItem(Todo todoObj)
    {
        todo.put(todoObj.getTodoId(),todoObj);
        return todo.containsKey(todoObj.getTodoId());
    }
    public boolean deleteTodoItem(int id)
    {
        if(todo.containsKey(id))
        {
            todo.remove(id);
            return todo.containsKey(id);
        }
        else
            return true;
    }
    public boolean updateTodoItem(Todo todoObj)
    {
        if(todo.containsKey(todoObj.getTodoId())) {
            todo.put(todoObj.getTodoId(), todoObj);
            return todo.containsKey(todoObj.getTodoId());
        }
        else
            return false;
    }
}

package hello;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
@Component
public class Repository {
    private static final HashMap<Integer,Todo> todo = new HashMap<Integer, Todo>();

    public boolean isEmpty()
    {
        if (todo.size()>0)
            return false;
        else
            return true;
    }
    public boolean isPresent(int id)
    {
        if(todo.get(id)==null)
            return false;
        else
            return true;
    }
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
            return !todo.containsKey(id);
        }
        else
            return false;
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

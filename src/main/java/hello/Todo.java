package hello;

public class Todo {
    private final String date;
    private final String todo;
    private final int todoId;

    public Todo(String date, String todo,int todoId ) {
        this.date = date;
        this.todo = todo;
        this.todoId= todoId;
    }

    public int getTodoId() {
        return todoId;
    }

    public String getDate() {
        return date;
    }

    public String getTodo() {
        return todo;
    }
}



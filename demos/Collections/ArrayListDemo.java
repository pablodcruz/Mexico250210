import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo {

    private List<Todo> myList;

    public ArrayListDemo() {
        myList = new ArrayList<>();
    }

    public static void main(String[] args) {
        ArrayListDemo demo = new ArrayListDemo();

        Todo myFirstTodo = new Todo();
        myFirstTodo.desc = "Study Java";
        myFirstTodo.completed = true;

        demo.addTodo(myFirstTodo);
        demo.viewTodos();
    }
    
    public void addTodo(Todo myTodo) {
        myList.add(myTodo);
    }

    public void viewTodos() {
        for (Todo todo : myList) {
            System.out.println(todo);
        }
    }
}

class Todo {
    String desc;
    boolean completed;

    @Override
    public String toString() {
        return "desc: " + desc + " status: " + completed;
    }
}

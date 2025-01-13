package hackathon.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hackathon.model.Todo;
import hackathon.service.TodoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoApi {


    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos() {
        System.out.println("Getting all todos");
        return todoService.getAllTodos();
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Integer id) {
        System.out.println("Getting todo by id: "+id);
        Optional<Todo> todo = todoService.findTodoById(id);
        return todo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Integer id, @RequestBody Todo todo) {

        Optional<Todo> updatedTodo = todoService.updateTodo(id, todo);

        return updatedTodo.map(todo1 -> ResponseEntity.ok(todo1))
                .orElseGet(()->ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Integer id) {

        boolean response = todoService.deleteTodo(id);
        if(response) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.noContent().build();
        }
    }
}
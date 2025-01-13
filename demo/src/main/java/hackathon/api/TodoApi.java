package hackathon.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hackathon.entity.TodoEntity;
import hackathon.model.Todo;
import hackathon.repository.TodoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoApi {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public List<Todo> getAllTodos() {
        System.out.println("Getting all todos");
        return todoRepository.findAll().stream().map(TodoEntity::toRecord).toList();
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Integer id) {
        System.out.println("Getting todo by id: "+id);
        Optional<Todo> todo = todoRepository.findById(id).map(TodoEntity::toRecord);
        return todo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoRepository.save(new TodoEntity(todo.name(),todo.description(),todo.dateCreated(),todo.status())).map(TodoEntity::toRecord).get();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Integer id, @RequestBody Todo todo) {

        // Potentially, move this to service layer and call the service from here
        Optional<Todo> updatedTodo = todoRepository.findById(id)
              .map(existingEntity -> {
                existingEntity.setName(todo.name());
                existingEntity.setDescription(todo.description());
               TodoEntity updatedEntity =  todoRepository.save(existingEntity).get();
               return updatedEntity.toRecord();
              });        

        return updatedTodo.map(todo1 -> ResponseEntity.ok(todo1))
                .orElseGet(()->ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Integer id) {

        // Potentially, move this to service layer and call the service from here
        Optional<Todo> todo = todoRepository.findById(id).map(TodoEntity::toRecord);
        if (todo.isPresent()) {
            todoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
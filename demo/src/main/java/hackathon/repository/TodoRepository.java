package hackathon.repository;

import hackathon.entity.TodoEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;


// @Repository
// public interface TodoRepository extends JpaRepository<TodoEntity, Long> 
@Component
public class TodoRepository {

    private List<TodoEntity> todos = new ArrayList<>();
    // Providing a temperory implementation, when JPA is enabled w/ above, remove this. 


    public Optional<TodoEntity> findById(int id) {
        return todos.stream().filter(todo -> todo.getId() == id).findFirst();

    }

    public Optional<TodoEntity> save(TodoEntity todo) {
        todos.add(todo);
        return Optional.of(todo);
    }

    public void deleteById(long id) {
        todos.removeIf(todo -> todo.getId() == id);
    }

    public List<TodoEntity> findAll() {
        return todos;
    }
}
    

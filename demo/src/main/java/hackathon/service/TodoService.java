package hackathon.service;

import hackathon.model.Todo;
import hackathon.repository.TodoEntity;
import hackathon.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll().stream().map(TodoEntity::toRecord).toList();
    }

    public Optional<Todo> findTodoById(String id) {
        return todoRepository.findById(id).map(TodoEntity::toRecord);
    }

    public List<Todo> findTodoByText(String text) {
        List<TodoEntity> todos = todoRepository.findByText(text);
        return todos.stream().map(TodoEntity::toRecord).toList();
    }

    public Todo createTodo(Todo todo) {
        return todoRepository
                .save(new TodoEntity(null, todo.name(), todo.description(), LocalDate.now(), Todo.Status.CREATED))
                .toRecord();
    }

    public Optional<Todo> updateTodo(String id, Todo todo) {
        return todoRepository.findById(id)
                .map(existingEntity -> {
                    if (todo.name() != null)
                        existingEntity.setName(todo.name());
                    if (todo.description() != null)
                        existingEntity.setDescription(todo.description());
                    if (todo.status() != null)
                        existingEntity.setStatus(todo.status());
                    if (todo.dateCreated() != null)
                        existingEntity.setDateCreated(todo.dateCreated());
                    TodoEntity updatedEntity = todoRepository.save(existingEntity);
                    return updatedEntity.toRecord();
                });
    }

    public boolean deleteTodo(String id) {

        Optional<Todo> todo = todoRepository.findById(id).map(TodoEntity::toRecord);
        if (todo.isPresent()) {
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

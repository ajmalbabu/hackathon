package hackathon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hackathon.entity.TodoEntity;
import hackathon.model.Todo;
import hackathon.repository.TodoRepository;

@Component
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;


    public List<Todo> getAllTodos() {
        return todoRepository.findAll().stream().map(TodoEntity::toRecord).toList();        
    }

    public Optional<Todo> findTodoById( Integer id) {
        return todoRepository.findById(id).map(TodoEntity::toRecord);
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(new TodoEntity(todo.name(),todo.description(),todo.dateCreated(),todo.status())).map(TodoEntity::toRecord).get();
    }

    public Optional<Todo> updateTodo(Integer id,Todo todo) {
        return todoRepository.findById(id)
            .map(existingEntity -> {
            existingEntity.setName(todo.name());
            existingEntity.setDescription(todo.description());
            TodoEntity updatedEntity =  todoRepository.save(existingEntity).get();
            return updatedEntity.toRecord();
            });   
    }

    public boolean deleteTodo(Integer id) {

        Optional<Todo> todo = todoRepository.findById(id).map(TodoEntity::toRecord);
        if (todo.isPresent()) {
            todoRepository.deleteById(id);
            return true;
        }        
        return false;
    }

}

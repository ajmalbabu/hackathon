package hackathon.service;

import hackathon.entity.TodoEntity;
import hackathon.model.Todo;
import hackathon.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    void updateExistingTodoReturnsUpdatedTodo() {
        // Given
        Integer todoId = 1;
        TodoEntity existingTodoEntity = new TodoEntity(todoId, "Existing Todo", "Old description", LocalDate.now(), Todo.Status.CREATED);
        TodoEntity updatedTodoEntity = new TodoEntity(todoId, "Updated Todo", "New description", LocalDate.now(), Todo.Status.UPDATED);
        Todo updatedTodo = new Todo(todoId, "Updated Todo", "New description", LocalDate.now(), Todo.Status.UPDATED);

        when(todoRepository.findById(todoId)).thenReturn(Optional.of(existingTodoEntity));
        when(todoRepository.save(any(TodoEntity.class))).thenReturn(updatedTodoEntity);

        // When
        Optional<Todo> result = todoService.updateTodo(todoId, updatedTodo);

        // Assert
        assertTrue(result.isPresent());
        Todo actual = result.get();
        assertEquals(updatedTodo.name(), actual.name());
        assertEquals(updatedTodo.description(), actual.description());
        assertEquals(updatedTodo.status(), actual.status());
        assertEquals(updatedTodo.id(), actual.id());
    }

    @Test
    void updateTodoOnNotExistingTodoReturnsOptionalEmpty() {

        // Given
        Integer todoId = 1;
        Todo updatedTodo = new Todo(todoId, "Updated Todo", "New description", LocalDate.now(), Todo.Status.UPDATED);
        when(todoRepository.findById(todoId)).thenReturn(Optional.empty());

        // When
        Optional<Todo> result = todoService.updateTodo(todoId, updatedTodo);

        // Then
        assertTrue(result.isEmpty());
    }


}

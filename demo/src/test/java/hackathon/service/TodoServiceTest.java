package hackathon.service;

import hackathon.entity.TodoEntity;
import hackathon.model.Todo;
import hackathon.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateExistingTodoReturnsUpdatedTodo() {
        // Given
        Integer todoId = 1;
        Todo existingTodo = new Todo(todoId, "Existing Todo", "Old description", LocalDate.now(), Todo.Status.CREATED);
        Todo updatedTodo = new Todo(todoId, "Updated Todo", "New description", LocalDate.now(), Todo.Status.UPDATED);
         TodoEntity existingTodoEntity = new TodoEntity(todoId, "Existing Todo", "Old description", LocalDate.now(), Todo.Status.CREATED);
          TodoEntity updatedTodoEntity = new TodoEntity(todoId, "Updated Todo", "New description", LocalDate.now(), Todo.Status.UPDATED);

        when(todoRepository.findById(todoId)).thenReturn(Optional.of(existingTodoEntity));
        when(todoRepository.save(any(TodoEntity.class))).thenReturn(Optional.of(updatedTodoEntity));

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
    void updateTodo_NotExistingTodo_ReturnsOptionalEmpty() {

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

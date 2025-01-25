package hackathon.service;

import hackathon.model.Todo;
import hackathon.repository.jpa.TodoJpaEntity;
import hackathon.repository.jpa.TodoJpaRepository;
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
    private TodoJpaRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    void updateExistingTodoReturnsUpdatedTodo() {
        // Given
        String todoId = "1";
        TodoJpaEntity existingTodoJpaEntity = new TodoJpaEntity(todoId, "Existing Todo", "Old description", LocalDate.now(), Todo.Status.CREATED);
        TodoJpaEntity updatedTodoJpaEntity = new TodoJpaEntity(todoId, "Updated Todo", "New description", LocalDate.now(), Todo.Status.UPDATED);
        Todo updatedTodo = new Todo(todoId, "Updated Todo", "New description", LocalDate.now(), Todo.Status.UPDATED);

        when(todoRepository.findById(todoId)).thenReturn(Optional.of(existingTodoJpaEntity));
        when(todoRepository.save(any(TodoJpaEntity.class))).thenReturn(updatedTodoJpaEntity);

        // When
        Optional<Todo> result = todoService.updateTodo(updatedTodo);

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
        String todoId = "1";
        Todo updatedTodo = new Todo(todoId, "Updated Todo", "New description", LocalDate.now(), Todo.Status.UPDATED);
        when(todoRepository.findById(todoId)).thenReturn(Optional.empty());

        // When
        Optional<Todo> result = todoService.updateTodo(updatedTodo);

        // Then
        assertTrue(result.isEmpty());
    }


}

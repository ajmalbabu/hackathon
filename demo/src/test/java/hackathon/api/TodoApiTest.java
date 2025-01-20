package hackathon.api;

import hackathon.entity.TodoEntity;
import hackathon.model.Todo;
import hackathon.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class TodoApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TodoRepository todoRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void updateTodo() throws Exception {

        // Given
        TodoEntity existingTodoEntity = new TodoEntity(1, "Existing Todo", "Existing description", LocalDate.now(), Todo.Status.CREATED);
        Optional<TodoEntity> updatedTodoEntity = Optional.of(new TodoEntity(1, "Updated Todo", "Updated description", LocalDate.now(), Todo.Status.UPDATED));

        when(todoRepository.findById(Integer.valueOf(1))).thenReturn(Optional.of(existingTodoEntity));
        when(todoRepository.save(any(TodoEntity.class))).thenReturn(updatedTodoEntity);

        // When, Then
        mockMvc.perform(put("/api/todos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Updated Todo\", \"description\": \"Updated description\", \"dateCreated\": \"2023-10-01\", \"status\": \"UPDATED\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Todo"))
                .andExpect(jsonPath("$.description").value("Updated description"))
                .andExpect(jsonPath("$.status").value("UPDATED"));
    }    
}
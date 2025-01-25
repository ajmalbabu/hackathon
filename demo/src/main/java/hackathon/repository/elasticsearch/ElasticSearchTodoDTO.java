package hackathon.repository.elasticsearch;

import hackathon.model.Todo;
import hackathon.repository.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

@Getter
@Setter
@Document(indexName = "todo")
@AllArgsConstructor
public class ElasticSearchTodoDTO {
    private String id;
    private String name;
    private String description;
    @Field(type = FieldType.Date, format = DateFormat.date)
    private LocalDate dateCreated;
    private String status;

    public TodoEntity toEntity() {
        return new TodoEntity(id, name, description, dateCreated, status==null?null:Todo.Status.valueOf(status));
    }
    public static ElasticSearchTodoDTO fromTodo(Todo todo) {
        return new ElasticSearchTodoDTO(todo.id(), todo.name(), todo.description(), todo.dateCreated(), todo.status().name());
    }
}

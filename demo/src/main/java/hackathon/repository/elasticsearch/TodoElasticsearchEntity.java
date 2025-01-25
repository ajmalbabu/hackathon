package hackathon.repository.elasticsearch;

import hackathon.model.Todo;
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
public class TodoElasticsearchEntity {
    private String id;
    private String name;
    private String description;
    @Field(type = FieldType.Date, format = DateFormat.date)
    private LocalDate dateCreated;
    private String status;

    public Todo toModel() {
        return new Todo(id, name, description, dateCreated, status==null?null:Todo.Status.valueOf(status));
    }
    public static TodoElasticsearchEntity fromModel(Todo todo) {
        return new TodoElasticsearchEntity(todo.id(), todo.name(), todo.description(), todo.dateCreated(), todo.status().name());
    }
}

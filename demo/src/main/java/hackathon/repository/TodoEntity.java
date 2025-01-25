package hackathon.repository;

import hackathon.model.Todo;
import hackathon.model.Todo.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "todo")
@Getter
@Setter
@NoArgsConstructor
public class TodoEntity {

    public TodoEntity(String id, String name, String description,LocalDate dateCreated, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.status = status;
    }

    @Id
    private String id;

    @PrePersist
    public void onPrePersist() {
        // Automatically set a UUID if ID is null
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    private String name;

    private String description;

    @Field(type = FieldType.Date, format = DateFormat.date_time)
    private LocalDate dateCreated;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Todo toRecord() {
        return new Todo(id, name, description, dateCreated, status);
    }
}

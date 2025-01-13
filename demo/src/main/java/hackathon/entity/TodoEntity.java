package hackathon.entity;

import hackathon.model.Todo;
import hackathon.model.Todo.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "todo")
@Getter
@Setter
@NoArgsConstructor
public class TodoEntity {

    public TodoEntity(String name, String description,LocalDate dateCreated, Status status) {
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private LocalDate dateCreated;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Todo toRecord() {
        return new Todo(id, name, description, dateCreated, status);
    }
}

package hackathon.model;

import java.time.LocalDate;

public record Todo(Integer id, String name, String description, LocalDate dateCreated, Status status) {

    public static enum Status {
        CREATED,
        UPDATED,
        COMPLETED,
        DELETED
    }
    
}

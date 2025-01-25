package hackathon.repository.jpa;

import hackathon.repository.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TodoJpaRepository extends JpaRepository<TodoEntity, String> {}

    

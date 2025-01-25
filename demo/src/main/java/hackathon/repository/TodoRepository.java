package hackathon.repository;

import hackathon.repository.elasticsearch.ElasticSearchTodoDTO;
import hackathon.repository.elasticsearch.TodoElasticsearchRepository;
import hackathon.repository.jpa.TodoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class TodoRepository {
    @Autowired
    private TodoElasticsearchRepository todoElasticsearchRepository;
    @Autowired
    private TodoJpaRepository todoJpaRepository;
    
    public Collection<TodoEntity> findAll() {
        return todoJpaRepository.findAll();    }

    public Optional<TodoEntity> findById(int id) {
        return findById(String.valueOf(id));
    }

    public Optional<TodoEntity> findById(String id) {
        return todoJpaRepository.findById(id);
    }

    public List<TodoEntity> findByText(String text) {
        return todoElasticsearchRepository.findByText(text);
    }

    public TodoEntity save(TodoEntity todoEntity) {
        // TODO implement atomicity so both repos are in sync
        todoElasticsearchRepository.save(ElasticSearchTodoDTO.fromTodo(todoEntity.toRecord()));
        return todoJpaRepository.save(todoEntity);
    }

    public void deleteById(String id) {
        // TODO implement atomicity so both repos are in sync
        todoElasticsearchRepository.deleteById(id);
        todoJpaRepository.deleteById(id);
    }
}

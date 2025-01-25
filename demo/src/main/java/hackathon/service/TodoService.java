package hackathon.service;

import hackathon.model.Todo;
import hackathon.repository.jpa.TodoJpaEntity;
import hackathon.repository.elasticsearch.TodoElasticsearchEntity;
import hackathon.repository.elasticsearch.TodoElasticsearchRepository;
import hackathon.repository.jpa.TodoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TodoService {


    @Autowired
    private TodoElasticsearchRepository todoElasticsearchRepository;
    @Autowired
    private TodoJpaRepository todoJpaRepository;


    public List<Todo> getAllTodos() {
        return todoJpaRepository.findAll().stream().map(TodoJpaEntity::toRecord).toList();
    }

    public Optional<Todo> findTodoById(String id) {
        return todoJpaRepository.findById(id).map(TodoJpaEntity::toRecord);
    }

    public List<Todo> findTodoByText(String text) {
        var searchHits = todoElasticsearchRepository.searchByText(text);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .map(TodoElasticsearchEntity::toModel) // Get actual document
                .collect(Collectors.toList());

    }

    public Todo createTodo(Todo todo) {
        TodoJpaEntity todoJpaEntity = new TodoJpaEntity(null, todo.name(), todo.description(), LocalDate.now(), Todo.Status.CREATED);
        // TODO implement atomicity so both repos are in sync
        todoElasticsearchRepository.save(TodoElasticsearchEntity.fromModel(todoJpaEntity.toRecord()));
        return todoJpaRepository.save(todoJpaEntity).toRecord();
    }

    public Optional<Todo> updateTodo(Todo todo) {
        if (todoJpaRepository.findById(todo.id()).isEmpty()) {
            return Optional.empty();
        }
        // TODO implement atomicity so both repos are in sync
        todoElasticsearchRepository.save(TodoElasticsearchEntity.fromModel(todo));
        TodoJpaEntity updatedEntity = todoJpaRepository.save(TodoJpaEntity.fromModel(todo));
        return Optional.ofNullable(updatedEntity.toRecord());
    }

    public boolean deleteTodo(String id) {

        Optional<Todo> todo = todoJpaRepository.findById(id).map(TodoJpaEntity::toRecord);
        if (todo.isPresent()) {
            // TODO implement atomicity so both repos are in sync
            todoElasticsearchRepository.deleteById(id);
            todoJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

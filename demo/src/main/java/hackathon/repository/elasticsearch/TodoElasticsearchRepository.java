package hackathon.repository.elasticsearch;

import hackathon.repository.TodoEntity;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.stream.Collectors;

public interface TodoElasticsearchRepository extends ElasticsearchRepository<ElasticSearchTodoDTO, String> {
    @Query("{ " +
            "    \"multi_match\": { " +
            "      \"query\": \"?0\", " +
            "      \"fields\": [ \"*\" ] " +
            "    } " +
            "}")
    SearchHits<ElasticSearchTodoDTO> searchByText(String text);

    default List<TodoEntity> findByText(String text) {
        var searchHits = searchByText(text);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .map(ElasticSearchTodoDTO::toEntity) // Get actual document
                .collect(Collectors.toList());

    }

}
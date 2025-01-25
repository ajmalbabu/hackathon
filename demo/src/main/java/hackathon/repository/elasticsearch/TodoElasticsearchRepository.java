package hackathon.repository.elasticsearch;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TodoElasticsearchRepository extends ElasticsearchRepository<TodoElasticsearchEntity, String> {
    @Query("{ " +
            "    \"multi_match\": { " +
            "      \"query\": \"?0\", " +
            "      \"fields\": [ \"*\" ] " +
            "    } " +
            "}")
    SearchHits<TodoElasticsearchEntity> searchByText(String text);

}
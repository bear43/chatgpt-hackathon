package ru.tinkoff.neurosurge.repo;

import java.util.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.tinkoff.neurosurge.model.ClusterizedText;

public interface ClusterizedTextRepo extends MongoRepository<ClusterizedText, String> {

    @Query("{$text: {$search: :#{#tag}}}")
    Collection<ClusterizedText> findAllByTag(@Param("tag") String tag);
}

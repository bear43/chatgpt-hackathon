package ru.tinkoff.neurosurge.model;

import java.util.Collection;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ClusterizedText")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClusterizedText {
    @Id
    @EqualsAndHashCode.Include
    private String id;

    private Collection<String> tags;

    private String text;
}

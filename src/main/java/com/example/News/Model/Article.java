package com.example.News.Model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, visible = true, property = "ArticleEnum")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AudioArticle.class, name = "AUDIO"),
        @JsonSubTypes.Type(value = PhotoArticle.class, name = "PHOTO"),
        @JsonSubTypes.Type(value = TextArticle.class, name = "TEXT"),
        @JsonSubTypes.Type(value = VideoArticle.class, name = "VIDEO")
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Article implements Serializable {

    @Id
    private Integer id;

    @NotNull
    private String title;
    private String description;

    @AccessType(AccessType.Type.PROPERTY)
    public abstract ArticleEnum articleEnum();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_id")
    private Writer writer;
}

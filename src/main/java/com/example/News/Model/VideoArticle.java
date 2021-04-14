package com.example.News.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "VideoArticle")
public class VideoArticle extends Article{

    private String descriptionVideo;
    private String titleVideo;
    private String urlVideo;

    @Override
    public ArticleEnum articleEnum() {
        return ArticleEnum.VIDEO;
    }
}

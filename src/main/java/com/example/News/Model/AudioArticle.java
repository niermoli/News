package com.example.News.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "AudioArticle")
public class AudioArticle extends Article{

    private String descriptionAudio;
    private String titleAudio;
    private String urlAudio;

    @Override
    public ArticleEnum articleEnum() {
        return ArticleEnum.AUDIO;
    }
}

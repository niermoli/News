package com.example.News.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity (name = "TextArticle")
public class TextArticle extends Article{

    private String text;

    @Override
    public ArticleEnum articleEnum(){
        return ArticleEnum.TEXT;
    }
}

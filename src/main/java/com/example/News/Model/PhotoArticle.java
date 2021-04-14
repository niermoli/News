package com.example.News.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "PhotoArticle")
public class PhotoArticle extends Article{
    
    @ElementCollection
    private List<String> photoUrl;
    
    @Override
    public ArticleEnum articleEnum(){
        return ArticleEnum.PHOTO;
    }
}

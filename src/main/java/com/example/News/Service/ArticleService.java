package com.example.News.Service;

import com.example.News.Model.Article;
import com.example.News.Model.PaginationResponse;
import com.example.News.Model.Writer;
import com.example.News.Repository.ArticleRepository;
import com.example.News.Utils.EntityURLBuilder;
import com.example.News.Utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;

@Service
public class ArticleService {

    private static final String ARTICLE_PATH = "article";

    @Autowired
    private ArticleRepository articleRepository;
    private WriterService writerService;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, WriterService writerService) {
        this.articleRepository = articleRepository;
        this.writerService = writerService;
    }

    public Article getArticleById(Integer id) {
        return articleRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public PaginationResponse<Article> getAll(Integer page, Integer size) {
        if (!Objects.isNull(page) && !Objects.isNull(size)) {
            Pageable pageable = PageRequest.of(page, size);
            Page<Article> articlePage = articleRepository.findAll(pageable);
            return new PaginationResponse<>(articlePage.getContent(), articlePage.getTotalPages(), articlePage.getTotalElements());
        }
        List<Article> articleList = articleRepository.findAll();
        return new PaginationResponse<>(articleList, 1, (long) articleList.size());
    }

    public List<Article> getAll(){
        return articleRepository.findAll();
    }

    public PostResponse addArticle(Article article) {
        final Article articleAdded = articleRepository.save(article);
        return PostResponse.builder().status(HttpStatus.CREATED).url(EntityURLBuilder.buildURL(ARTICLE_PATH, articleAdded.getId().toString())).build();
    }

    public PostResponse addWriter(Integer id, Integer writerId) {
        Article article = getArticleById(id);
        Writer writer = writerService.getWriterById(writerId);
        article.setWriter(writer);
        articleRepository.save(article);
        return PostResponse.builder().status(HttpStatus.CREATED).url(EntityURLBuilder.buildURL(ARTICLE_PATH, article.getId().toString())).build();
    }

    public PostResponse deleteArticle(Integer id) {
        articleRepository.deleteById(id);
        return PostResponse.builder().status(HttpStatus.ACCEPTED).url(EntityURLBuilder.buildURL(ARTICLE_PATH, id.toString())).build();

    }
}

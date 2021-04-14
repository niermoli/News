package com.example.News.Utils;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class PostResponse {

    private String url;
    private HttpStatus status;

}

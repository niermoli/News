package com.example.News.Model.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WriterDTO {

    private Integer id;
    private String name;
    private String lastname;
    private String email;
}

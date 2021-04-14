package com.example.News.Model;

public enum ArticleEnum {

    AUDIO ("Article with audio"),
    PHOTO ("Article with photo"),
    TEXT ("Article with text only"),
    VIDEO ("Article with video");

    private String description;

    ArticleEnum(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static ArticleEnum find(String value){
        for (ArticleEnum a : values()) {
            if (a.toString().equalsIgnoreCase(value)){
                return a;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid article type: .%s", value));
    }
}

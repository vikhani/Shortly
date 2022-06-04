package com.vkhani.Shortly.Models;


import lombok.Getter;
import lombok.Setter;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("urlpairs")
public class UrlPair {

    @Transient
    public static final String SEQUENCE_NAME = "urlpairs_sequence";
    @Id
    @Setter Long id;
    @Getter String longURL;
    @Getter String shortenedUrl;

    public UrlPair(){}

    public UrlPair(Long id, String longURL, String shortenedUrl) {
        this.id = id;
        this.longURL = longURL;
        this.shortenedUrl = shortenedUrl;
    }
    public UrlPair(String longURL, String shortenedUrl){
        this.longURL = longURL;
        this.shortenedUrl = shortenedUrl;
    }
//    public void setLongURL(String longURL) {
//        UrlValidator validator = new UrlValidator();
//        if(validator.isValid(longURL))
//            this.longURL = longURL;
//        else
//            throw new RuntimeException("Invalid URL.");
//    }

}

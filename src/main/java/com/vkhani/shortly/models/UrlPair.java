package com.vkhani.shortly.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("urlpairs")
public class UrlPair {

    @Transient
    public static final String SEQUENCE_NAME = "urlpairs_sequence";
    @MongoId
    @Setter
    Long id;
    @Getter
    String longURL;
    @Getter
    String shortcutCode;
    @Getter
    boolean isCustom;

    public UrlPair() {
    }

    public UrlPair(Long id, String longURL, String shortcutCode, boolean isCustom) {
        this.id = id;
        this.longURL = longURL;
        this.shortcutCode = shortcutCode;
        this.isCustom = isCustom;
    }

    public UrlPair(String longURL, String shortcutCode, boolean isCustom) {
        this.longURL = longURL;
        this.shortcutCode = shortcutCode;
        this.isCustom = isCustom;
    }
}

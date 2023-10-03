package com.vkhani.shortly.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Document("urlpairs")
@Setter
@Getter
@AllArgsConstructor
public class UrlPair {

    @MongoId
    private UUID id;
    private String longURL;
    private String shortcutCode;
    private boolean isCustom;
}

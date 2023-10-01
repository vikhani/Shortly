package com.vkhani.shortly.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UrlPairDto {
    String longURL;
    String shortenedUrl;
}

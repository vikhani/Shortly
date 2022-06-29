package com.vkhani.Shortly.dtos;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class UrlPairDto {
    String longURL;
    String shortenedUrl;
}

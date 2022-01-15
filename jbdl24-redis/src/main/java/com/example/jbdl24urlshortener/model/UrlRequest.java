package com.example.jbdl24urlshortener.model;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UrlRequest {
    private String longUrl;
}

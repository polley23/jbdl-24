package com.example.jbdl24urlshortener.services;
import com.example.jbdl24urlshortener.model.UrlRequest;
import com.example.jbdl24urlshortener.model.UrlResponse;

import java.io.UnsupportedEncodingException;

public interface UrlService {
    UrlResponse getShortUrl(UrlRequest request, String clientId) throws Exception;
    UrlResponse getLongUrl(String  encryptedId,  String clientId) throws Exception;
}

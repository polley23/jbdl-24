package com.example.jbdl24urlshortener.controller;
import com.example.jbdl24urlshortener.model.ClientRequest;
import com.example.jbdl24urlshortener.model.UrlRequest;
import com.example.jbdl24urlshortener.model.UrlResponse;
import com.example.jbdl24urlshortener.services.ClientService;
import com.example.jbdl24urlshortener.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {
    @Autowired
    private UrlService urlService;

    @PostMapping("v1/short_url")
    ResponseEntity getShortUrl(@RequestBody(required = true) UrlRequest urlRequest, @RequestHeader(value = "client", required = true) String client){
        try {
            UrlResponse urlResponse = urlService.getShortUrl(urlRequest,client);
            return ResponseEntity.status(HttpStatus.OK).body(urlResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{hash}")
    ResponseEntity getLongUrl(@PathVariable(required = true) String hash, @RequestHeader(value = "client", required = true) String client){
        try {
            UrlResponse urlResponse = urlService.getLongUrl(hash,client);
            return ResponseEntity.status(HttpStatus.OK).body(urlResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

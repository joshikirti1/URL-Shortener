package com.kirti.urlshortener.controller;

import com.kirti.urlshortener.dto.ShortenRequest;
import com.kirti.urlshortener.model.UrlMapping;
import com.kirti.urlshortener.service.ShortCodeGenerator;
import com.kirti.urlshortener.service.UrlMappingService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UrlController {

    private final ShortCodeGenerator shortCodeGenerator;
    private final UrlMappingService urlMappingService;

    public UrlController(
            ShortCodeGenerator shortCodeGenerator,
            UrlMappingService urlMappingService) {
        this.shortCodeGenerator = shortCodeGenerator;
        this.urlMappingService = urlMappingService;
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestBody ShortenRequest request) {

        String originalUrl = request.getOriginalUrl();

        if (originalUrl == null || originalUrl.isBlank()) {
            return "Error: URL cannot be empty!";
        }

        if (!originalUrl.startsWith("http://") &&
                !originalUrl.startsWith("https://")) {
            return "Error: URL must start with http:// or https://";
        }

        String shortCode = shortCodeGenerator.generateCode();

        urlMappingService.saveUrlMapping(shortCode, originalUrl);

        return "Short URL: http://localhost:8080/api/" + shortCode;
    }

    @GetMapping("/lookup/{shortCode}")
    public String lookupUrl(@PathVariable String shortCode) {

        UrlMapping mapping = urlMappingService.getUrlMapping(shortCode);

        if (mapping == null) {
            return "Short URL not found!";
        }

        return "Original URL: " + mapping.getOriginalUrl();
    }

    @GetMapping("/analytics/{shortCode}")
    public String getAnalytics(@PathVariable String shortCode) {

        UrlMapping mapping = urlMappingService.getUrlMapping(shortCode);

        if (mapping == null) {
            return "Short URL not found!";
        }

        return "Short Code: " + mapping.getShortCode()
                + "\nOriginal URL: " + mapping.getOriginalUrl()
                + "\nTotal Clicks: " + mapping.getClickCount();
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectUrl(@PathVariable String shortCode) {

        UrlMapping mapping = urlMappingService.getUrlMapping(shortCode);

        if (mapping == null) {
            return ResponseEntity.notFound().build();
        }

        mapping.setClickCount(mapping.getClickCount() + 1);
        urlMappingService.updateUrlMapping(mapping);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                java.net.URI.create(mapping.getOriginalUrl()));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
package com.kirti.urlshortener.service;

import com.kirti.urlshortener.model.UrlMapping;
import com.kirti.urlshortener.repository.UrlMappingRepository;
import org.springframework.stereotype.Service;

@Service
public class UrlMappingService {

    private final UrlMappingRepository urlMappingRepository;

    public UrlMappingService(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    public void saveUrlMapping(String shortCode, String originalUrl) {
        UrlMapping mapping = new UrlMapping(shortCode, originalUrl);
        urlMappingRepository.save(mapping);
    }

    public UrlMapping getUrlMapping(String shortCode) {
        return urlMappingRepository.findById(shortCode).orElse(null);
    }

    // Update an existing URL mapping
    public void updateUrlMapping(UrlMapping mapping) {
        urlMappingRepository.save(mapping);
    }
}
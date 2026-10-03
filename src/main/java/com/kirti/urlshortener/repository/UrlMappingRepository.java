package com.kirti.urlshortener.repository;

import com.kirti.urlshortener.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, String> {
}
package com.linkshortslinks.URL_SHORTER.controller;

import com.linkshortslinks.URL_SHORTER.entity.Url;
import com.linkshortslinks.URL_SHORTER.repository.UrlRepository;
import com.linkshortslinks.URL_SHORTER.service.UrlShortnerService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/url-shorter")
public class UrlController {
    @Autowired
    private UrlShortnerService urlShortnerService;
    @Autowired
    private UrlRepository urlRepository;

    @PostMapping
    public ResponseEntity<Url> shortenUrl(@RequestBody Url url) {
        return  ResponseEntity.ok(urlShortnerService.createShortUrl(url.getLongUrl()));
    }
    // get Original Url
    @GetMapping("/get/{shortCode}")
    public ResponseEntity<?> getOriginalUrl(@PathVariable String shortCode) {
       Optional<Url> urlOptional = urlShortnerService.getOriginalUrl(shortCode);
        if (urlOptional.isPresent()) {
            return ResponseEntity.ok(urlOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update Short Url
    @PutMapping("/{shortCode}")
    public ResponseEntity<?> updateUrl(@PathVariable String shortCode, @RequestBody Url url) {
        Optional<Url> updateUrl = urlShortnerService.updateShortUrl(url.getLongUrl(), shortCode);
        return updateUrl.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Delete short Url
    @DeleteMapping("/{shortCode}")
    public boolean deleteUrl(@PathVariable String shortCode){
        Optional<Url> url = urlRepository.findByShortCode(shortCode);
        url.ifPresent(urlRepository::delete);
        return url.isPresent();
    }

    // Get Url Stats
    @GetMapping("/{shortCode}/stats")
    public ResponseEntity<?> getUrlStats(@PathVariable String shortCode){
        Optional<Url> stats = urlShortnerService.getUrlStats(shortCode);
        return stats.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }




}

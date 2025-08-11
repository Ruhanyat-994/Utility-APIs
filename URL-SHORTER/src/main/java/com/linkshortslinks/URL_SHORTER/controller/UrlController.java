package com.linkshortslinks.URL_SHORTER.controller;

import com.linkshortslinks.URL_SHORTER.entity.Url;
import com.linkshortslinks.URL_SHORTER.service.UrlShortnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/url-shorter")
public class UrlController {
    @Autowired
    private UrlShortnerService urlShortnerService;

    @PostMapping
    public ResponseEntity<Url> shortenUrl(@RequestBody Url url) {
        return ResponseEntity.ok(urlShortnerService.createShortUrl(url.getLongUrl()));
    }
    @GetMapping("/{shortCode}")
    public ResponseEntity<?> getOriginalUrl(@PathVariable String shortCode) {
       Optional<Url> shortendUrl = urlShortnerService.getOriginalUrl(shortCode);
       return shortendUrl.map(url -> ResponseEntity.ok(url)).
               orElse(ResponseEntity.notFound().build());
    }


}

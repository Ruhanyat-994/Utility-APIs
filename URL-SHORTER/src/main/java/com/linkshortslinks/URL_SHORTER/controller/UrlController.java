package com.linkshortslinks.URL_SHORTER.controller;

import com.linkshortslinks.URL_SHORTER.entity.Url;
import com.linkshortslinks.URL_SHORTER.service.UrlShortnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/url-shorter")
public class UrlController {
    @Autowired
    private UrlShortnerService urlShortnerService;

    @PostMapping
    public ResponseEntity<Url> shortenUrl(@RequestBody Url url) {
        return ResponseEntity.ok(urlShortnerService.createShortUrl(url.getLongUrl()));
    }


}

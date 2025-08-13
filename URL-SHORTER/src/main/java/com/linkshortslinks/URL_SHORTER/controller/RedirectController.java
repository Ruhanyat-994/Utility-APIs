package com.linkshortslinks.URL_SHORTER.controller;

import com.linkshortslinks.URL_SHORTER.entity.Url;
import com.linkshortslinks.URL_SHORTER.service.UrlShortnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class RedirectController {

    @Autowired
    private UrlShortnerService urlShortnerService;

    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortCode) {
        Optional<Url> shortendUrl = urlShortnerService.getOriginalUrl(shortCode);
        if (shortendUrl.isPresent()) {
            urlShortnerService.incrementAccessCount(shortCode);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", shortendUrl.get().getLongUrl())
                    .build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

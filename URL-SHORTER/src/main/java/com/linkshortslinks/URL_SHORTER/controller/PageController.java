package com.linkshortslinks.URL_SHORTER.controller;

import com.linkshortslinks.URL_SHORTER.entity.Url;
import com.linkshortslinks.URL_SHORTER.service.UrlShortnerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

    @Autowired
    private UrlShortnerService urlShortnerService;

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam("longUrl") String longUrl,
                             HttpServletRequest request,
                             Model model) {
        Url createdUrl = urlShortnerService.createShortUrl(longUrl);

        String baseUrl = request.getScheme() + "://" + request.getServerName() +
                (request.getServerPort() == 80 || request.getServerPort() == 443 ? "" : ":" + request.getServerPort());

        String fullShortUrl = baseUrl + "/" + createdUrl.getShortCode();
        model.addAttribute("shortUrl", fullShortUrl);

        return "result";
    }


    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }
}


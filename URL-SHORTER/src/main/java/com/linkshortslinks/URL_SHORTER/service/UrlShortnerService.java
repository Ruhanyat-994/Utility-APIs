package com.linkshortslinks.URL_SHORTER.service;

import com.linkshortslinks.URL_SHORTER.entity.Url;
import com.linkshortslinks.URL_SHORTER.repository.UrlRepository;
import jakarta.servlet.http.PushBuilder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UrlShortnerService {

    private final UrlRepository urlRepository;
    public UrlShortnerService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public static final String CHARACTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static final int CHARACTER_LENGTH = CHARACTER.length();
    public static final int SHORT_CODE_LENGTH = 8;
    public static final SecureRandom random = new SecureRandom();

    // Generate Random Short Code
    public String generateShortCode() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            stringBuilder.append(CHARACTER.charAt(random.nextInt(CHARACTER_LENGTH)));
        }
        return stringBuilder.toString();
    }

    public Url createShortUrl(String longUrl) {
        String shortCode ;
        do {
            shortCode = generateShortCode();
        }while (urlRepository.findByShortCode(shortCode).isPresent());
        Url url = new Url();
        url.setLongUrl(longUrl);
        url.setShortCode(shortCode);

        return urlRepository.save(url);

    }

}

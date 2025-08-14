package com.linkshortslinks.URL_SHORTER.service;

import com.linkshortslinks.URL_SHORTER.entity.ClickHistory;
import com.linkshortslinks.URL_SHORTER.entity.Url;
import com.linkshortslinks.URL_SHORTER.repository.ClickHistoryRepo;
import com.linkshortslinks.URL_SHORTER.repository.UrlRepository;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UrlShortnerService {

    @Autowired
    private ClickHistoryRepo clickHistoryRepo;
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

    // Create short Url
    public Url createShortUrl(String longUrl) {
        String shortCode ;
        do {
            shortCode = generateShortCode();
        }while (urlRepository.findByShortCode(shortCode).isPresent());
        Url url = new Url();
        url.setLongUrl(longUrl);
        url.setShortCode(shortCode);
        url.setAccessCount(0);

        return urlRepository.save(url);

    }

    // Retrieve Original URL
    public Optional<Url> getOriginalUrl(String shortCode) {
        return urlRepository.findByShortCode(shortCode);

    }


    // Update short URL
    public Optional<Url> updateShortUrl(String longUrl, String shortCode) {
        Optional<Url> shortendUrl = urlRepository.findByShortCode(shortCode);
        shortendUrl.ifPresent( url -> {
            url.setLongUrl(longUrl);
            url.setUpdatedAt(Instant.now());
            urlRepository.save(url);
        });
        return shortendUrl;

    }

    // Delete Short Url
    public boolean deleteShortUrl(String shortCode) {
        Optional<Url> shortendUrl = urlRepository.findByShortCode(shortCode);
        shortendUrl.ifPresent(urlRepository::delete);
        return shortendUrl.isPresent();
    }

    // Get URL Statistics
    public Optional<Url> getUrlStats(String shortCode){
        return urlRepository.findByShortCode(shortCode);
    }


    // increment accesscount

    public void incrementAccessCount(String shortCode){
        urlRepository.findByShortCode(shortCode).ifPresent(url -> {
            url.setAccessCount(url.getAccessCount() +1);
            urlRepository.save(url);

            ClickHistory clickHistory = new ClickHistory();
            clickHistory.setUrl(url);
            clickHistoryRepo.save(clickHistory);

        });

    }

    public List<ClickHistory> getClickHistory(String shortCode){
        return urlRepository.findByShortCode(shortCode).map(
                url -> clickHistoryRepo.findByUrlIdOrderByClickedAtAsc(url.getId())).orElse(
                        List.of());
    }

}

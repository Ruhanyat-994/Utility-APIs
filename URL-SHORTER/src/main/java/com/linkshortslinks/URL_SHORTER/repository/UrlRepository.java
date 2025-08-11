package com.linkshortslinks.URL_SHORTER.repository;

import com.linkshortslinks.URL_SHORTER.entity.Url;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, String > {
    Optional<Url> findByShortCode(String shortCode);
}

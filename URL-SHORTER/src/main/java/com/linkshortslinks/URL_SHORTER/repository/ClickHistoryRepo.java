package com.linkshortslinks.URL_SHORTER.repository;

import com.linkshortslinks.URL_SHORTER.entity.ClickHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClickHistoryRepo extends JpaRepository<ClickHistory, Long> {
    List<ClickHistory> findByUrlIdOrderByClickedAtAsc(Long urlId);
}

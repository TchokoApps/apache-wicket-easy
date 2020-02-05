package com.tchokoapps.apache.wicket.repositories;

import com.tchokoapps.apache.wicket.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}

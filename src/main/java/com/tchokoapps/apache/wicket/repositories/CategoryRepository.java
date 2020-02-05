package com.tchokoapps.apache.wicket.repositories;

import com.tchokoapps.apache.wicket.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

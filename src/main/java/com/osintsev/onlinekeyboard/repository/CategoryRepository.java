package com.osintsev.onlinekeyboard.repository;

import com.osintsev.onlinekeyboard.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}

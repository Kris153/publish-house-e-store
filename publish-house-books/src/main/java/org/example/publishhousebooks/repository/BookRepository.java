package org.example.publishhousebooks.repository;

import org.example.publishhousebooks.model.entities.BookEntity;
import org.example.publishhousebooks.model.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity> findAllByCategory(CategoryEntity category);
}

package org.example.publishhousebooks.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, name = "image_url")
    private String imageUrl;
    @Column(nullable = false, name = "short_description")
    private String shortDescription;
    @Column(nullable = false, name = "long_description")
    private String longDescription;
    @Column(nullable = false)
    private Double price;
    @ManyToOne
    private AuthorEntity author;
    @ManyToOne
    private CategoryEntity category;
}

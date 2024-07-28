package org.example.publishhousebooks.model.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "category")
    private List<BookEntity> books;

    public CategoryEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.tchokoapps.apache.wicket.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp = "http[s]://.*\\.(jpg|png)", message = "{category.imageurl.invalid}")
    private String imgUrl;

    private String description;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private List<Article> articles = new ArrayList<>();

    public Category() {
    }

    public Category(String name, String imgUrl, String description) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
    }
}

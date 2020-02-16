package com.tchokoapps.apache.wicket.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private String imgUrl;
    private LocalDate createdAt = LocalDate.now();
    private String description;
    @ManyToOne
    private Category category;

    public Article() {
    }


}

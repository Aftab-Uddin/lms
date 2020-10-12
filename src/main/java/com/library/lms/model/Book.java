package com.library.lms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Book {
    @Id
    @Column(length = 13)
    private String isbn;
    private String bookName;
    private String bookAuthor;
    private String publisher;
    private String edition;
    private int quantity;
    private String category;
}

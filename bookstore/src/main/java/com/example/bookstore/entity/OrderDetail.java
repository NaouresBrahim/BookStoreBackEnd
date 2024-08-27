package com.example.bookstore.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail {


    @EmbeddedId
    @JsonIgnore
    private OrderBookPK pk;

    @Column(nullable = false)
    private Integer quantity;



    @Transient
    public Book getBook() {
        return this.pk.getBook();
    }

    @Transient
    public Double getTotalPrice() {
        return getBook().getPrice() * getQuantity();
    }

}

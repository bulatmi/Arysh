package ru.itpark.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tproduct")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String product_name;
    private Float price;
    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<Cart> carts;


    public Product(String productName, Float price) {
    }

    public Product(String product_name, Float price, LocalDateTime creationTime) {
        this.product_name = product_name;
        this.price = price;
        this.creationTime = creationTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {return product_name; }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public Float getPrice() {return price; }

    public void setPrice(Float price) {this.price = price; }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

}


package ru.itpark.dto;

public class ProductDto {
    private int id;
    private String product_name;
    private Float price;

    public ProductDto() {
    }

    public ProductDto(int id, String product_name, Float price) {
        this.id = id;
        this.product_name = product_name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return product_name;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

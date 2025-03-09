package com.example.order_events_processor.Documents;

import com.example.order_events_processor.DTO.OrderDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Orders")
public class Orders {

    @Id
    private String id;
    @DBRef
    private User user;
    private String product;
    private int quantity;
    private Double total;
    private String status;

    public Orders() {}

    public Orders(OrderDto orderDto, User user) {
        this.user = user;
        this.product = orderDto.product();
        this.quantity = orderDto.quantity();
        this.total = orderDto.total();
        this.status = "Created";
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}

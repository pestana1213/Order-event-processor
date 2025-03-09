package com.example.order_events_processor.Documents;

import com.example.order_events_processor.DTO.OrderDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Orders")
@Data
public class Orders {

    @Id
    private String id;
    @DBRef
    private User user;
    private String product;
    private int quantity;
    private Double total;
    private String status;

    public Orders(OrderDto orderDto, User user) {
        this.user = user;
        this.product = orderDto.product();
        this.quantity = orderDto.quantity();
        this.total = orderDto.total();
        this.status = "Created";
    }
}

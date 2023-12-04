package com.ecommerce.ecommercejpa.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommercejpa.order.dto.Item;
import com.ecommerce.ecommercejpa.order.dto.OrderRequest;
import com.ecommerce.ecommercejpa.utils.ResponseHandler;

@RestController
@RequestMapping("/order")
public class OrderResource {
    
    @Autowired
    private OrderService service;

    @Autowired
    private OrderRepository repository;
    
    @GetMapping("/")
    public ResponseEntity<Object> getOrders(){
        List<Order> orders = repository.findAll();
        return ResponseHandler.response(orders, HttpStatus.OK, null);
    }

    @PostMapping("/")
    public ResponseEntity<Object> CreateOrders(@RequestBody OrderRequest newOrder){
        List<Item> itemsUnavailable = service.createOrder(newOrder.getItems(), newOrder.getCustomer());
        if(!itemsUnavailable.isEmpty()){
            return ResponseHandler.response(itemsUnavailable, HttpStatus.OK, "Alguns itens da sua compra não estão mais disponíveis, você pode tentar comprar produtos semelhantes!");
        }
        return ResponseHandler.response("Sua ordem foi finalizado com sucesso, o pagamento está em processamento!", HttpStatus.OK);
    }
}

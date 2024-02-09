package com.ecommerce.ecommercejpa.order;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommercejpa.customer.Customer;
import com.ecommerce.ecommercejpa.customer.CustomerService;
import com.ecommerce.ecommercejpa.order.dto.Item;
import com.ecommerce.ecommercejpa.order.dto.OrderRequest;
import com.ecommerce.ecommercejpa.order.dto.OrderResponse;
import com.ecommerce.ecommercejpa.utils.ResponseHandler;

@RestController
@RequestMapping("/order")
public class OrderResource {
    
    private final OrderService service;
    private final OrderRepository repository;
    private final CustomerService customerService;

    public OrderResource(OrderService service, OrderRepository repository, CustomerService customerService){
        this.service = service;
        this.repository = repository;
        this.customerService = customerService;
    }
    @GetMapping("/")
    public ResponseEntity<Object> getOrders(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer c = customerService.getCustomerByUsername((String)authentication.getPrincipal());
        List<Order> orders = repository.findAllOrderByCustomerId(c.getId());
        OrderResponse orderResponse = service.mapData(orders);
        return ResponseHandler.response(orderResponse, HttpStatus.OK, null);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createOrders(@RequestBody OrderRequest newOrder){
        List<Item> itemsUnavailable = service.createOrder(newOrder.getItems(), newOrder.getCustomer());
        if(!itemsUnavailable.isEmpty()){
            return ResponseHandler.response(itemsUnavailable, HttpStatus.OK, "Alguns itens da sua compra não estão mais disponíveis, você pode tentar comprar produtos semelhantes!");
        }
        return ResponseHandler.response(null, HttpStatus.OK, "Sua ordem foi finalizado com sucesso, o pagamento está em processamento!");
    }
}

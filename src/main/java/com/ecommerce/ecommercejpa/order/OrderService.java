package com.ecommerce.ecommercejpa.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommercejpa.customer.Customer;
import com.ecommerce.ecommercejpa.customer.dto.CustomerData;
import com.ecommerce.ecommercejpa.order.dto.Item;
import com.ecommerce.ecommercejpa.order.dto.ItemResponse;
import com.ecommerce.ecommercejpa.order.dto.OrderResponse;
import com.ecommerce.ecommercejpa.product.ProductService;
import com.ecommerce.ecommercejpa.product.dto.ProductData;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final ProductService productService;

    public OrderService(OrderRepository repository, ProductService productService){
        this.repository = repository;
        this.productService = productService;
    }

    public boolean isItemAvailable(Item item) {
        Integer quantity = repository.getProductQuantityById(item.getProduct().getId());
        return quantity >= item.getQuantity();
    }

    public List<Item> createOrder(List<Item> items, Customer customer) {
        Order order = new Order();
        List<Item> itemsUnavailable = new ArrayList<>();
        order.setCustomer(customer);
        items.forEach(item -> {
            if (isItemAvailable(item)) {
                productService.updateProductQuantity(item.getProduct().getId(), item.getQuantity());
                order.setPrice(item.getPrice());
                order.setProduct(item.getProduct());
                order.setQuantity(item.getQuantity());
                repository.save(order);
            } else {
                itemsUnavailable.add(item);
            }
        });
        return itemsUnavailable;
    }

    public OrderResponse mapData(List<Order> orders){
        OrderResponse orderR = new OrderResponse();
        List<ItemResponse> items = new ArrayList<>();

        orders.stream().forEach(order->{
            CustomerData customerData = new CustomerData(); 
            ProductData productData = new ProductData();
            ItemResponse item = new ItemResponse();

            customerData.setCpf(order.getCustomer().getCpf());
            customerData.setEmail(order.getCustomer().getEmail());
            customerData.setFullName(order.getCustomer().getFullName());
            customerData.setId(order.getCustomer().getId());
            customerData.setUsername(order.getCustomer().getUsername());

            productData.setCategory(order.getProduct().getCategory());
            productData.setDescription(order.getProduct().getDescription());
            productData.setId(order.getProduct().getId());
            productData.setImg(order.getProduct().getImg());
            productData.setName(order.getProduct().getName());

            orderR.setId(order.getId());
            orderR.setCustomer(customerData);

            item.setProduct(productData);
            item.setPrice(order.getPrice());
            item.setQuantity(order.getQuantity());

            items.add(item);

        });
        orderR.setItems(items);
        return orderR;
    }
}

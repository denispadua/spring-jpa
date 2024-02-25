package com.ecommerce.ecommercejpa.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import java.math.BigDecimal;

import com.ecommerce.ecommercejpa.category.Category;
import com.ecommerce.ecommercejpa.product.dto.ProductRequestDto;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    
    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Test
    public void createProduct(){

        Category category = new Category();
        ProductRequestDto productRequestDto = new ProductRequestDto(
            "null", "Teste", "Teste", new BigDecimal(3000), 20, category);
        category.setName("Teste");

        when(productRepository.save(any())).thenReturn(new Product());
        Product result = productService.createProduct(productRequestDto);

        assertNotNull(result);
    }

    @Test
    public void getProducts(){
        when(productRepository.findAll()).thenReturn(List.of(new Product()));
        List<Product> products = productService.getProducts();
        assertEquals(products.size(),1);
    }

    @Test
    public void getProductById(){
        Product p = new Product();
        p.setId(1L);
        when(productRepository.findById(p.getId())).thenReturn(Optional.of(p));
        Product result = productService.getProductById(1L);

        assertEquals(p.getId(), result.getId());
    }

    @Test
    public void updateProductQuantity(){
        Product p = new Product();
        p.setQuantity(10);
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(p));
        productService.updateProductQuantity(anyLong(), 5);

        assertEquals(p.getQuantity(), 5);
    }
}

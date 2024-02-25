package com.ecommerce.ecommercejpa.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.ecommercejpa.category.dto.CategoryRequestDto;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @InjectMocks
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("Test to insert a category that does not exist - A category needs to be created")
    public void createCategorySucess() {
        CategoryRequestDto requestDto = new CategoryRequestDto("Test Category 2");
        Category c1 = new Category();
        c1.setName(requestDto.name());
        when(categoryRepository.save(any())).thenReturn(c1);
        Category c = categoryService.createCategory(requestDto);
        assertNotNull(c);
        assertEquals(c.getName(), c1.getName());
    }

    @Test
    @DisplayName("Test to insert a category that already exist - An exception must be thrown")
    public void createCategoryFail() {
        CategoryRequestDto requestDto = new CategoryRequestDto("Test Category 2");
        when(categoryRepository.findByName("Test Category 2")).thenReturn(Optional.of(new Category()));
        assertThrows(KeyAlreadyExistsException.class, () -> categoryService.createCategory(requestDto));
    }
}

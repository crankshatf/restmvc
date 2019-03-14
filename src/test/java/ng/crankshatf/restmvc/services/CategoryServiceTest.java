package ng.crankshatf.restmvc.services;

import ng.crankshatf.restmvc.api.v1.mapper.CategoryMapper;
import ng.crankshatf.restmvc.api.v1.model.CategoryDTO;
import ng.crankshatf.restmvc.domain.Category;
import ng.crankshatf.restmvc.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    public static final Long ID = 2L;
    public static final String NAME = "Fruits";

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void getAllCategories() throws Exception {
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
        assertEquals(3, categoryDTOS.size());
    }

    @Test
    public void getCategoryByName() throws Exception {
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);
        when(categoryRepository.findByNameIgnoreCase(anyString())).thenReturn(category);
        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}
package ng.crankshatf.restmvc.api.v1.mapper;

import ng.crankshatf.restmvc.api.v1.model.CategoryDTO;
import ng.crankshatf.restmvc.domain.Category;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryMapperTest {

    public static final String NAME = "Furniture";
    public static final long ID = 1L;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() throws Exception {
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }

}
package ng.crankshatf.restmvc.services;

import ng.crankshatf.restmvc.api.v1.mapper.CategoryMapper;
import ng.crankshatf.restmvc.api.v1.model.CategoryDTO;
import ng.crankshatf.restmvc.domain.Category;
import ng.crankshatf.restmvc.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::categoryToCategoryDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        Category category = categoryRepository.findByNameIgnoreCase(name);
        if (category != null) {
            return categoryMapper.categoryToCategoryDTO(category);
        } else  {
            throw new ResourceNotFoundException();
        }
    }
}

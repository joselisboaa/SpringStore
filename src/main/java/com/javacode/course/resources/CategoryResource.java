package com.javacode.course.resources;

import com.javacode.course.dto.CategoryDTO;
import com.javacode.course.entities.Category;
import com.javacode.course.services.CategoryServiceImpl;
import com.javacode.course.services.ICategoryService;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Resource
@RestController
@RequestMapping("/categories/")
public class CategoryResource {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ICategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<Category> categories = service.getAll();
        List<CategoryDTO> categoryDTOList = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());

        return ResponseEntity.ok().body(categoryDTOList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
        Category category = service.getById(id);
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        return ResponseEntity.ok().body(categoryDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody Category category) {
        Category createdCategory = service.create(category);
        CategoryDTO createdCategoryDTO = modelMapper.map(createdCategory, CategoryDTO.class);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCategory.getId()).toUri();

        return ResponseEntity.created(uri).body(createdCategoryDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody Category newCategoryData) {
        Category updatedCategory = service.update(id, newCategoryData);
        CategoryDTO updatedCategoryDTO = modelMapper.map(updatedCategory, CategoryDTO.class);

        return ResponseEntity.ok().body(updatedCategoryDTO);
    }
}


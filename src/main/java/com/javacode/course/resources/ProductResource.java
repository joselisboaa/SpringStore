package com.javacode.course.resources;

import com.javacode.course.dto.ProductDTO;
import com.javacode.course.entities.Product;
import com.javacode.course.services.IProductService;
import jakarta.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products/")
@Resource
public class ProductResource {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    IProductService service;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        List<Product> products = service.getAll();
        List<ProductDTO> productDTOList = products.stream()
                .map((product) -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());

        return ResponseEntity.ok().body(productDTOList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        Product product = service.getById(id);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

        return ResponseEntity.ok().body(productDTO);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody @Validated Product product) {
        Product createdProduct = service.create(product);
        ProductDTO createdProductDTO = modelMapper.map(createdProduct, ProductDTO.class);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdProduct.getId()).toUri();

        return ResponseEntity.created(uri).body(createdProductDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody @Validated Product product) {
        Product updatedProduct = service.update(id, product);
        ProductDTO updatedProductDTO = modelMapper.map(updatedProduct, ProductDTO.class);

        return ResponseEntity.ok().body(updatedProductDTO);
    }
}

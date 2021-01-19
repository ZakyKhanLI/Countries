package com.task.repository;

import com.task.entity.Products;
import org.springframework.data.repository.CrudRepository;

public interface AppRepository extends CrudRepository<Products, Long> {
}

package com.yunnuy.che.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.yunnuy.che.model.Brand;

public interface BrandRepository extends CrudRepository<Brand, Long> {
	List<Brand> findByName(String name);
}

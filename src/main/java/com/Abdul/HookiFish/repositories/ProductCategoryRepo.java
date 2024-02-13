package com.Abdul.HookiFish.repositories;

import com.Abdul.HookiFish.entities.ProductCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepo  extends JpaRepository<ProductCategories,Integer>
{
}

package com.Abdul.HookiFish.services.CustomerServicesImpl;

import com.Abdul.HookiFish.dtos.ProductCategoryDto;
import com.Abdul.HookiFish.dtos.ProductDtos;
import com.Abdul.HookiFish.entities.ProductCategories;
import com.Abdul.HookiFish.repositories.ProductCategoryRepo;
import com.Abdul.HookiFish.services.ProductCategoryServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryServices
{
    @Autowired
     private ProductCategoryRepo productCategoryRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ProductCategoryDto addProductCategories(ProductCategoryDto productCategoryDto)
    {
        ProductCategories productCat = this.modelMapper.map(productCategoryDto, ProductCategories.class);
        productCategoryRepo.save(productCat);
        return this.modelMapper.map(productCat,ProductCategoryDto.class);
    }

    @Override
    public List<ProductCategoryDto> getAllProductCategoriesList(Integer pageNumber,Integer pageSize,String sortBy)
    {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());

        Page<ProductCategories> allProductCategories = productCategoryRepo.findAll(pageable);
        List<ProductCategories> all = allProductCategories.getContent();
        List<ProductCategoryDto> productCategoryDtos = all.stream().map(e -> this.modelMapper.map(e, ProductCategoryDto.class)).collect(Collectors.toList());
        return  productCategoryDtos;
    }
}

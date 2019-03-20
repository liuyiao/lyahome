package com.ssm.service;

import com.ssm.pojo.Brand;
import com.ssm.pojo.Category;
import com.ssm.pojo.Product;
import com.ssm.vo.ResultVO;

import java.util.List;

public interface ProductService {
    ResultVO showProductsPages(Product product, Integer page, Integer limit);

    List<Category> queryAllFirstCategorys();

    List<Category> queryAllFirstCategorysPatent(Integer id);

    List<Brand> queryAllBrand();

    boolean add(Product product);

    boolean addProductMasterImge(long id, String s);

   Product Presentation(long id);

   //与httpClient有关
   Product queryProductByPid(Long id);
}

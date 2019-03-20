package com.ssm.service.impl;

import com.ssm.mapper.BrandMapper;
import com.ssm.mapper.CategoryMapper;
import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.ProductPictureMapper;
import com.ssm.pojo.*;
import com.ssm.service.ProductService;
import com.ssm.vo.ProductVo;
import com.ssm.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
     private ProductPictureMapper productPictureMapper;

    @Autowired
     private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public ResultVO showProductsPages(Product product, Integer page, Integer limit) {
        List<ProductVo> list= productMapper.showProductsPages(product,(page-1)*limit,limit);
        for (ProductVo p:list){

            List<ProductPicture> productPictures = productPictureMapper.showPicturesById(p.getProductId());
            //可以随机的去去一个
            if(productPictures!=null&&productPictures.size()>0){
                //取出第一个图片的url
                p.setPicUrl(productPictures.get(0).getPicUrl());
            }


        }

        Long count = productMapper.selectCount(product);

        return ResultVO.success(count,list);
    }

    @Override
    public List<Category> queryAllFirstCategorys() {

       List<Category> list= categoryMapper.queryAllFirstCategorys(1);

        return list;
    }

    @Override
    public List<Category> queryAllFirstCategorysPatent(Integer id) {

        List<Category> list=categoryMapper.queryAllFirstCategorysPatent(id);


        return list;
    }

    @Override
    public List<Brand> queryAllBrand() {

        List<Brand> list=   brandMapper.queryAllBrand();

        return list;
    }

    @Override
    public boolean add(Product product) {

        product.setProductionDate(new Date());
        product.setAuditStatus(0);
        product.setModifiedTime(new Date());
        product.setIndate(new Date());
        product.setPublishStatus(0);

        //调用 maper存储

        return  productMapper.insertSelective(product)>0;
    }

    @Override
    public boolean addProductMasterImge(long id, String s) {


        return productPictureMapper.addProductMasterImge(id,s)>0;
    }

    @Override
    public Product Presentation(long id) {

        Product product = productMapper.ProductPicture(id);

        return product;
    }


    //与httpClient有关
    @Override
    public Product queryProductByPid(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }
}

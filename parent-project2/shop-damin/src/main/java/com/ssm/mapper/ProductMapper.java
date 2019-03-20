package com.ssm.mapper;

import com.ssm.pojo.Product;
import com.ssm.pojo.ProductExample;
import java.util.List;

import com.ssm.vo.ProductVo;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    long countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Long productId);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Long productId);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<ProductVo> showProductsPages(@Param("product") Product product, @Param("begin") int i,@Param("end") Integer limit);

    Long selectCount(@Param("product") Product product);

    int add(@Param("product") Product product);

    Product ProductPicture(@Param("id") long id);
}
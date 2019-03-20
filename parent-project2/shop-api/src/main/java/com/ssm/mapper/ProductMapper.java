package com.ssm.mapper;


import com.ssm.pojo.Product;
import com.ssm.vo.ProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {

    List<ProductVo> selectProductPages(@Param("begin") int i,
                                       @Param("end") Integer limit);

    long selectProductPagesCount();
}
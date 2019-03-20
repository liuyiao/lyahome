package com.ssm.api;

import com.ssm.pojo.Product;
import com.ssm.service.ProductService;
import com.ssm.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApiController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/api/products",method = RequestMethod.GET)
@ResponseBody
    public Object getAllProducts(@RequestParam(defaultValue = "1") Integer page,
                @RequestParam(defaultValue = "10") Integer limit){

        ResultVO vo = productService.showProductsPages(new Product(), page, limit);

        return vo;

    }


        @RequestMapping(value="/api/product/{id}",method = RequestMethod.GET)
        @ResponseBody
        public Object getAllProducts(@PathVariable Long id){


            Product p = productService.queryProductByPid(id);


            return p;
        }



    /**
     *
     * 添加商品的接口
     * @param name
     * @param price
     * @param color
     * @return
     */
    @RequestMapping(value="/api/product",method = RequestMethod.POST)
    @ResponseBody
    public Object getAllProducts(String name,Double price,String color){


        System.out.println(name+"--------->"+price+"------------>"+color);



        return ResultVO.success();
    }

    /**
     * 删除的接口

     * @return
     */
    @RequestMapping(value="/api/product/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Object getAllProducts4(@PathVariable Long id){


        System.out.println(id+"--------->");



        return ResultVO.success();
    }


    /**
     * 更新接口
     *
     * @param product
     * @return
     */
    @RequestMapping(value = "/api/product/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public  Object testPut(@PathVariable Long id,Product product){
        System.out.println(id+"------------>"+product);


        return  ResultVO.success();

    }


}

package com.ssm.controller;


import com.ssm.pojo.Brand;
import com.ssm.pojo.Category;
import com.ssm.pojo.Product;
import com.ssm.service.ProductService;
import com.ssm.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/product", method = RequestMethod.GET)
    @ResponseBody
    public Object productList(Product product,
                              @RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer limit) {

        ResultVO vo = productService.showProductsPages(product, page, limit);

        return vo;
    }

    /**
     * 二级联动（1
     */

    @RequestMapping(value = "/categorys", method = RequestMethod.GET)
    @ResponseBody
    public Object getCategs() {
        List<Category> list = productService.queryAllFirstCategorys();

        return list;
    }

    @RequestMapping(value = "/categorys/parent/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getCategsSencond(@PathVariable Integer id) {
        List<Category> list = productService.queryAllFirstCategorysPatent(id);

        return list;
    }

    @RequestMapping(value = "/brand", method = RequestMethod.GET)
    @ResponseBody
    public Object allBrand() {
        List<Brand> list = productService.queryAllBrand();

        return list;

    }


    @RequestMapping(value = "/product/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadProductDescImg(MultipartFile editorFile, HttpServletRequest request) throws IOException {
        Map<String,Object> map=new HashMap<>();
        //吧图片上传到 static/imgs/pdesc
        String realPath = request.getServletContext().getRealPath("static/imgs/pdesc");
        File fileDir = new File(realPath);
        if (!fileDir.isDirectory()) {
            fileDir.delete();
            fileDir.mkdirs();
        }
        if(!editorFile.isEmpty()){

            //截取源文件的后缀名
            String endName = editorFile.getOriginalFilename().substring(editorFile.getOriginalFilename().lastIndexOf('.'));

            System.out.println("endName-====================>"+endName);

            //构建文件的名字
            String fileName= UUID.randomUUID().toString().replace("-","")+endName;

            //保存
            File dest=  new File(realPath+"/"+fileName);

            editorFile.transferTo(dest);
            //返回指定格式的字符串
            map.put("errno",0);
            map.put("data",new String[]{"http://localhost:81/shop-admin/static/imgs/pdesc/"+fileName});
            return map;
        }


        return null;

    }
    @RequestMapping(value="/product",method = RequestMethod.POST)
      public String  addProduct(Product product){
          boolean f=  productService.add(product);
          if(f){
              return "product/productlist";
          }
          return "product/productadd";

      }

@RequestMapping("/product/images/{id}")
      public String toImges(@PathVariable Integer id , Model model){

model.addAttribute("id",id);
        return "/product/productaddimages";
      }


    /**
     * 主图上传
     */
    @RequestMapping(value = "/product/images",method = RequestMethod.POST)
    @ResponseBody
    public Object uploadProductDescImg(@RequestParam("file") MultipartFile file, HttpServletRequest request,long id) throws IOException {

        //吧图片上传到 static/imgs/pdesc
        String realPath = request.getServletContext().getRealPath("static/imgs/p");
        File fileDir = new File(realPath);
        if (!fileDir.isDirectory()) {
            fileDir.delete();
            fileDir.mkdirs();
        }

            //截取源文件的后缀名
            String endName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));

            //构建文件的名字
            String fileName= UUID.randomUUID().toString().replace("-","")+endName;

            //保存
            File dest=  new File(realPath+"/"+fileName);

            if (!file.isEmpty())
                file.transferTo(dest);//上传
        //路径存到数据库

     boolean  f =productService.addProductMasterImge(id,"static/imgs/p/"+fileName);




        return fileName;//可以 不用返回任何东西

    }


    //商品介绍

    @RequestMapping("/product/desc/{id}")
    public Object Presentation(Model model,@PathVariable long id){

        Product p=productService.Presentation(id);
      model.addAttribute("p",p);


        return "/product/productdesc";
    }


}
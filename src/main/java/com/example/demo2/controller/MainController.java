

package com.example.demo2.controller;

import com.example.demo2.bean.*;
import com.example.demo2.entity.RestaurantEntity;
import com.example.demo2.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController  //表明这是一个控制器类
//@RequestMapping("/index")   //处理 /article 路径下的请求
// @CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "http://121.40.208.74:3000" )
public class MainController {
    @Autowired  //将 ArticleService 服务注入到控制器中，用于处理文章相关的逻辑。
    MainService mainService;

//    @RequestMapping(value = "/main")  //将处理POST请求的URL映射到 addNewArticle 方法上。
//    public void test() {
//        System.out.println("yse!!!!!!!!!!!!");
//        mainService.test1();
//
//    }

//    //@RequestMapping(value = "/get", method = RequestMethod.POST)  //将处理POST请求的URL映射到 addNewArticle 方法上。@RequestBody int id
//    @RequestMapping(value = "/get")
//    public @ResponseBody RespBean getDishById() {
////        String id=user.id;
//        int id=1;
//        List<String> value=mainService.getDishById(id);
//        if(value.size()!=0){
//            return new RespBean("success", "1",value);
//        }else{
//            return new RespBean("failure", "0",value);
//        }
//    }


    //@RequestMapping(value = "/Home")
    @RequestMapping(value = "/Home", method = RequestMethod.GET)  //将处理POST请求的URL映射到 addNewArticle 方法上。@RequestBody int id
    public @ResponseBody List<RestaurantInfo> getAllRestaurantInfo() {
        System.out.println("---------------------------------");
        return mainService.getAllRestaurantInfo();
    }


    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)  //将处理POST请求的URL映射到 addNewArticle 方法上。@RequestBody int id
    public @ResponseBody RestaurantInfo_All getRestaurantInfo_All(int RestaurantId, int ReviewsSorted, int DishesSorted) {
        System.out.println("restaurants");
        return mainService.getRestaurantInfo_All(RestaurantId,ReviewsSorted,DishesSorted);
    }

//    @RequestMapping(value = "/restaurants")
//    public @ResponseBody RestaurantInfo_All getRestaurantInfo_All() {
//        return mainService.getRestaurantInfo_All(1,1,1);
//    }


    @RequestMapping(value = "/dishes",method = RequestMethod.GET)
    public @ResponseBody DishInfo getDishInfo(int DishesId, int DishesSorted) {
        return mainService.getDishInfo(DishesId, DishesSorted);
    }

    @RequestMapping(value = "/reviews",method = RequestMethod.GET)
    public @ResponseBody ReviewInfo getReviewInfo(int ReviewId) {
        return mainService.getReviewInfo(ReviewId);
    }




}


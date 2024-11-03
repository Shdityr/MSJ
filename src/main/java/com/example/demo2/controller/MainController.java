

package com.example.demo2.controller;

import com.example.demo2.bean.*;
import com.example.demo2.entity.RestaurantEntity;
import com.example.demo2.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RestController  //表明这是一个控制器类
//@RequestMapping("/index")   //处理 /article 路径下的请求
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
    @RequestMapping("/222")
    public String printImage111()throws IOException {
        // 返回打印信息作为API的响应
        return "Image bytes printed to the console";
    }

    //@RequestMapping(value = "/Home")
    @RequestMapping("/111")
    public String printImageBytes()throws IOException {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        mainService.insertImage();
//        // 使用 ClassPathResource 加载图片文件 (可以放在 resources 文件夹)
//        ClassPathResource imgFile = new ClassPathResource("./drawing-7502248_640.jpg");
//
//        // 将图片文件读为字节数组
//        byte[] imageBytes = Files.readAllBytes(imgFile.getFile().toPath());
//
//        // 打印字节数组到控制台
//        for (byte b : imageBytes) {
//            System.out.print(b);
//        }

        // 返回打印信息作为API的响应
        return "Image bytes printed to the console";
    }
    private static BufferedImage convertBytesToImage(byte[] imageBytes) {
        BufferedImage image = null;
        try (ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes)) {
            image = ImageIO.read(bais);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private static void saveImage(BufferedImage image, String path) {
        try {
            ImageIO.write(image, "png", new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/image")  //将处理POST请求的URL映射到 addNewArticle 方法上。@RequestBody int id
    public @ResponseBody byte[] getImagebyid()throws IOException {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

//        ClassPathResource imgFile = new ClassPathResource("./drawing-7502248_640.jpg");
//        byte[] ret = Files.readAllBytes(imgFile.getFile().toPath());
//        BufferedImage image = convertBytesToImage(ret);
//        saveImage(image, "output.png");
        byte[] ret=mainService.getImagebyid(1);
        BufferedImage image = convertBytesToImage(ret);
        saveImage(image, "output1111.png");
        return ret;
    }
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


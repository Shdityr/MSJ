


package com.example.demo2.service;

import com.example.demo2.bean.DishInfo;
import com.example.demo2.bean.RestaurantInfo;
import com.example.demo2.bean.RestaurantInfo_All;
import com.example.demo2.bean.ReviewInfo;
import com.example.demo2.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo2.repository.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.io.InputStream;
@Service
@Transactional
public class MainService {

    @Autowired
    RestaurantRepository RestaurantRepository;

    @Autowired
    DishRepository DishRepository;

    @Autowired
    ReviewRepository ReviewRepository;

    @Autowired
    ImageRepository ImageRepository;
    public String func2(){
        List<ImageEntity> is=ImageRepository.findAll();
        String s="";
        int n=is.size();
        for(int i=0;i<n;i++){
            s+=String.valueOf(is.get(i).getId())+";";
        }
        return s;
    }
    public void func1(){
        // ImageRepository.deleteAll();
        RestaurantEntity shop = RestaurantRepository.findById(1).get();
        shop.setImages("352");
        RestaurantRepository.save(shop);
    }
    public void test1(){
        int i1=1;
        RestaurantEntity shop = RestaurantRepository.findById(i1).get();
        if (shop != null)
        {
            System.out.println("---------------1、shop信息--------------------");
            System.out.println("餐厅编号：" + shop.getId());
            System.out.println("餐厅名称：" + shop.getName());
            //获取关联的身份证信息信息
            System.out.println("---------------2、food信息信息---------------");
            List<DishEntity> foodList = shop.getDishList();
            int length = foodList.size();
            if(length!=0)
            {

                for( DishEntity food : foodList){
                    System.out.println("食物编号" + food.getId());
                    System.out.println("食物名称" + food.getName());
                    List<Review2Entity> reviewList = food.getReview();
                    for( Review2Entity review : reviewList){
                        System.out.println("评论编号" + review.getId());
                        System.out.println("评论名称" + review.getContent());

                    }
                }

            }else{
                System.out.println("失败");
            }
        }


    }

    public List<String> getDishById(int id){
        List<String> value = new ArrayList<>();
        RestaurantEntity shop = RestaurantRepository.findById(id).get();
        if(shop!=null){
            System.out.println("---------------2、food信息信息---------------");
            List<DishEntity> foodList = shop.getDishList();
            int length = foodList.size();
            if(length!=0)
            {
                for( DishEntity food : foodList){
                    //System.out.println("食物编号" + food.getId());
                    System.out.println("食物名称" + food.getName());
                    value.add(food.getName());
                }
            }else{
                System.out.println("无");
            }
        }
        return value;
    }

    public List<RestaurantInfo> getAllRestaurantInfo(){
        List<RestaurantInfo> ret=new ArrayList<RestaurantInfo>();
        List<RestaurantEntity> restaurants=RestaurantRepository.findAll();
        int n=restaurants.size();
        int id;
        String name;
        float rating;
        float averagePrice;
        String location;
        List<byte[]> images = new ArrayList<>();;

        String businessHours;

        for(int i=0;i<n;i++){
            id= restaurants.get(i).getId();
            name = restaurants.get(i).getName(); // 获取名称
            rating = restaurants.get(i).getRating(); // 获取评分
            averagePrice = restaurants.get(i).getAveragePrice(); // 获取平均价格
            location = restaurants.get(i).getLocation(); // 获取位置
//            images.clear();
//            images.add(restaurants.get(i).getImages()); // 获取图片列表
            images=images_convert(restaurants.get(i).getImages());
            businessHours = restaurants.get(i).getBusinessHours(); // 获取营业时间
            float location_x = restaurants.get(i).getLocation_x();
            float location_y = restaurants.get(i).getLocation_y();

            RestaurantInfo restaurantInfo = new RestaurantInfo(id, name, rating, averagePrice, location, images, businessHours,location_x,location_y);

            ret.add(restaurantInfo);
            System.out.println("111111111111");
        }
        return ret;
    }

    public RestaurantInfo_All getRestaurantInfo_All(int RestaurantId,int ReviewsSorted,int DishesSorted) {
        RestaurantEntity restaurant = RestaurantRepository.findById(RestaurantId).get();
        int id = restaurant.getId();
        String name = restaurant.getName();
        String businessHours = restaurant.getBusinessHours();
        float rating = restaurant.getRating();
        float averagePrice = restaurant.getAveragePrice();
        String location = restaurant.getLocation();
        List<byte[]> images = images_convert(restaurant.getImages());
        String contactInfo = restaurant.getContactinfo();

        List<String> Style = new ArrayList<String>(style_convert(restaurant.getStyle()));
        float location_x = restaurant.getLocation_x();
        float location_y = restaurant.getLocation_y();
//        List<Integer> reviewsId =new ArrayList<>();
//        List<Integer>DishesId = new ArrayList<>();
        List<DishEntity> dishList = restaurant.getDishList();
        List<ReviewEntity> reviewList = restaurant.getReviewList();
        List<Integer> DishesId;
        List<Integer> reviewsId;

        if (DishesSorted == 1) {
            DishesId = dishList.stream()
                    .sorted(( dish2,dish1) -> Float.compare(dish1.getRating(), dish2.getRating())) // 按照 b 排序
                    .map(DishEntity::getId) // 提取 a 属性
                    .collect(Collectors.toList());

        } else {
            DishesId = new ArrayList<>();
            for (DishEntity dish : dishList) {
                //System.out.println("食物编号" + food.getId());
                //System.out.println("食物名称" + food.getName());
                DishesId.add(dish.getId());
            }
        }
        if (ReviewsSorted == 1) {
            reviewsId = reviewList.stream()
                    .sorted((c2, c1) -> Integer.compare(c1.getTime(), c2.getTime()))
                    .map(ReviewEntity::getId) // 提取 a 属性
                    .collect(Collectors.toList());
        } else {
            reviewsId = new ArrayList<>();
            for (ReviewEntity review : reviewList) {
                //System.out.println("食物编号" + food.getId());
                //System.out.println("食物名称" + food.getName());
                reviewsId.add(review.getId());
            }

        }


        RestaurantInfo_All ret = new RestaurantInfo_All(id, name, rating, averagePrice, location, images, businessHours, contactInfo, reviewsId, Style, DishesId,location_x,location_y
        );
//        System.out.println(ret);
        return ret;
    }


    public DishInfo getDishInfo(int DishesId, int ReviewsSorted) {
        System.out.println(DishesId);
        DishEntity dish = DishRepository.findById(DishesId).get();

        int id=dish.getId();
        List<byte[]> images=images_convert(dish.getImages());
        String name=dish.getName();
        String store= dish.getStore();
        float rating=dish.getRating();

        float averagePrice=dish.getAveragePrice();
        List<String> Style=style_convert(dish.getStyle());
        List<Integer> reviewsId;
        List<Review2Entity> reviewList = dish.getReview();

        if(ReviewsSorted==1){
            reviewsId = reviewList.stream()
                    .sorted((c2, c1) -> Integer.compare(c1.getTime(), c2.getTime()))
                    .map(Review2Entity::getId) // 提取 a 属性
                    .collect(Collectors.toList());
        }else{
            reviewsId = new ArrayList<>();
            for (Review2Entity review : reviewList) {
                //System.out.println("食物编号" + food.getId());
                //System.out.println("食物名称" + food.getName());
                reviewsId.add(review.getId());
            }
        }
        DishInfo ret=new DishInfo(id, images, name, store, rating, averagePrice, Style, reviewsId);
        return ret;


    }

    public ReviewInfo getReviewInfo(int ReviewId) {
        ReviewEntity review = ReviewRepository.findById(ReviewId).get();

        int id=review.getId();
        int userID=review.getUserID();
        String contents=review.getContent();
        float averagePrice=review.getAveragePrice();
        List<byte[]> images=images_convert(review.getImages());
        float rating=review.getRating();
        List<String> Style=style_convert(review.getStyle());
        ReviewInfo ret=new ReviewInfo(id,userID,contents,averagePrice,images,rating,Style);
        return ret;


    }


    public byte[] getImagebyid(int Id) {
        ImageEntity image = ImageRepository.findById(Id).get();
        return image.getData();
    }

    public void insertImage() throws IOException {

        ClassPathResource imgFile = new ClassPathResource("drawing-7502248_640.jpg");

        // 使用 InputStream 读取图片文件为字节数组
        InputStream inputStream = imgFile.getInputStream();
        byte[] imageBytes = inputStream.readAllBytes();
        // 将图片文件读为字节数组
        ImageEntity image=new ImageEntity("zsh",imageBytes);
        ImageRepository.save(image);

    }


    private List<byte[]> images_convert(List<Integer> s) {
        List<byte[]> ret=new ArrayList<>();
        int n=s.size();
        for(int i=0;i<n;i++){
            ImageEntity image=ImageRepository.findById(s.get(i)).get();
            ret.add(image.getData());

        }
        return ret;


    }
    private List<String> style_convert(String s) {
        String[] parts = s.split(";;");
        // 将数组转换为 List 并返回
        return Arrays.asList(parts);
    }


}


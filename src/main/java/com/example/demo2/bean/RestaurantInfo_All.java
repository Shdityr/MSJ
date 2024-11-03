package com.example.demo2.bean;

import java.util.List;

public class RestaurantInfo_All {

    public int id;
    public String name;
    public float rating;

    public float averagePrice;
    public String location;
    public List<byte[]> images;

    public String businessHours;

    public String contactInfo;
    public List<Integer> reviewsId;
    public List<String> Style;
    public List<Integer>DishesId;

    public float location_x;
    public float location_y;
    public RestaurantInfo_All(int id,String name,float rating,float averagePrice,String location,List<byte[]> images,String businessHours,String contactInfo,List<Integer> reviewsId,
    List<String> Style,List<Integer>DishesId,float location_x,float location_y){
        this.id=id;
        this.name=name;
        this.rating=rating;
        this.averagePrice=averagePrice;
        this.location=location;
        this.images=images;
        this.businessHours=businessHours;

        this.contactInfo=contactInfo;
        this.reviewsId=reviewsId;
        this.Style=Style;
        this.DishesId=DishesId;
        this.location_x=location_x;
        this.location_y=location_y;
    }
}

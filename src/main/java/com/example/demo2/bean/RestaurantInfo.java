package com.example.demo2.bean;
import java.util.ArrayList;
import java.util.List;
public class RestaurantInfo {
    public int id;
    public String name;
    public float rating;

    public float averagePrice;
    public String location;
    public List<String> images;

    public String businessHours;
    public float location_x;
    public float location_y;
    public RestaurantInfo(int id,String name,float rating,float averagePrice,String location,List<String> images,String businessHours,float location_x,float location_y){
        this.id=id;
        this.name=name;
        this.rating=rating;
        this.averagePrice=averagePrice;
        this.location=location;
        this.images=images;
        this.businessHours=businessHours;
        this.location_x=location_x;
        this.location_y=location_y;
    }

}

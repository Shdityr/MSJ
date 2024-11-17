package com.example.demo2.bean;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;

public class DishInfo {
    public int id;

    public String name;
    public float rating;
    public float averagePrice;
    public List<byte[]> images;
    public List<Integer> reviewsId;




    public List<String> Style;

    public DishInfo(int id,
                    List<byte[]> images,
                      String name,
                      float rating,
                      float averagePrice,
                      List<String> Style,
                      List<Integer> reviewsId
                      ){
        this.id=id;
        this.images = images;
        this.name = name;
//        this.store = store;
        this.rating = rating;
        this.averagePrice = averagePrice;
        this.Style = Style;
        this.reviewsId = reviewsId;

    }
}

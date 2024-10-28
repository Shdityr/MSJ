package com.example.demo2.bean;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;

public class DishInfo {
    public int id;
    public List<String> images;
    public String name;
    public String store;
    public float rating;

    public float averagePrice;
    public List<String> Style;
    public List<Integer> reviewsId;
    public DishInfo(int id,
                    List<String> images,
                      String name,
                      String store,
                      float rating,
                      float averagePrice,
                      List<String> Style,
                      List<Integer> reviewsId
                      ){
        this.id=id;
        this.images = images;
        this.name = name;
        this.store = store;
        this.rating = rating;
        this.averagePrice = averagePrice;
        this.Style = Style;
        this.reviewsId = reviewsId;

    }
}

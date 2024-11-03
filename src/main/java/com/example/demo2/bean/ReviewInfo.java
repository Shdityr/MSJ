package com.example.demo2.bean;
import java.util.ArrayList;
import java.util.List;
public class ReviewInfo {
    public int id;
    public int userID;
    public String contents;
    public float averagePrice;
    public List<byte[]> images;
    public float rating;
    public List<String> Style;

    public ReviewInfo(int id,int userID,String contents,float averagePrice,List<byte[]> images,float rating,List<String> Style){
        this.id=id;
        this.userID=userID;
        this.contents=contents;
        this.averagePrice=averagePrice;
        this.images=images;
        this.rating=rating;
        this.Style=Style;

    }
}

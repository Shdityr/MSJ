package com.example.demo2.bean;
import java.util.ArrayList;
import java.util.List;
public class ReviewInfo {
    public int id;
    public int userID;
    public String contents;
    public float rating;
    public float averagePrice;
    public List<byte[]> images;
    public String time;

    public List<String> Style;

    public ReviewInfo(int id,int userID,String contents,float averagePrice,List<byte[]> images,float rating,List<String> Style,String time){
        this.id=id;
        this.userID=userID;
        this.contents=contents;
        this.averagePrice=averagePrice;
        this.images=images;
        this.rating=rating;
        this.Style=Style;
        this.time=time;

    }
}

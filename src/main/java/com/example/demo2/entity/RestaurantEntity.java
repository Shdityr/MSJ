
package com.example.demo2.entity;
import jakarta.persistence.*;
        import com.example.demo2.entity.DishEntity;
import com.example.demo2.entity.ReviewEntity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;



@Entity
@Table(name = "restaurant")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String images;
    private String name;
    private String location;
    private float rating;
    private float location_x;
    private float location_y;
    private String contactinfo;
    @Column(name = "business_hours")
    private String businessHours;
    private String style;

    @Column(name = "average_price")
    private float averagePrice;


    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<DishEntity> dishList;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<ReviewEntity> reviewList;


    // public void setImages(String a){
    //     images=a;
    // }
            public void setImages(String a){
        images=a;
    }



    public void setId(int a){id=a;}
    public void setName(String a){name=a;}
    public void setLocation(String a){location=a;}
    public void setStyle(String a){style=a;}
    public void setBusinessHours(String a){ businessHours=a;}
    public void setContactinfo(String a){ contactinfo=a;}
    public void setRating(float a){ rating=a;}
    public void setLocation_x(float a){
        location_x=a;
    }
    public void setLocation_y(float a){
        location_y=a;
    }
    public void setAveragePrice(float a){ averagePrice=a;}
    public void setDishList(List<DishEntity> a){ dishList=a;}
    public void setReviewList(List<ReviewEntity> a){ reviewList=a;}

    public int getId(){return id;}
    public String getName(){return name;}
    public String getLocation(){return location;}
    public String getStyle(){return style;}
    public String getBusinessHours(){return businessHours;}

    public String getContactinfo(){return contactinfo;}
    public float getRating(){return rating;}

    public float getLocation_x(){return location_x;}

    public float getLocation_y(){return location_y;}

    public float getAveragePrice(){return averagePrice;}
    public List<Integer> getImages(){
        List<Integer> ret=new ArrayList<>();
        String[] parts = images.split(";;");
        for (String part : parts) {
            if (!part.isEmpty()) { // 检查是否为空字符串
                ret.add(Integer.valueOf(part)); // 将字符串转换为 Integer
            }
        }
        // 将数组转换为 List 并返回
        return ret;
    }

    public List<DishEntity> getDishList(){return dishList;}

    public List<ReviewEntity> getReviewList(){return reviewList;}

    //    public void setID(Long value){this.id = value;}
//    public void setUsername(String value){this.username = value;}
//    public void setPassword(String value){this.password = value;}


}



package com.example.demo2.entity;
import jakarta.persistence.*;
        import com.example.demo2.entity.RestaurantEntity;
import com.example.demo2.entity.DishEntity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;



@Entity
@Table(name = "review")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userID;
    private String content;
    private String images;
    private float rating;
    private String style;
    private int time;

    @Column(name = "average_price")
    public float averagePrice;









    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

//    @ManyToOne
//    @JoinColumn(name = "dish_id")
//    private DishEntity dish;





    public int getId(){return id;}
    public int getTime(){return time;}
    public int getUserID(){return userID;}
    public String getContent(){return content;}
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
    public float getRating(){return rating;}
    public float getAveragePrice(){return averagePrice;}
    public String getStyle(){return style;}

    public RestaurantEntity getRestaurant(){return restaurant;}


//    public List<FoodEntity> geta(){return foodList;}

    //    public void setID(Long value){this.id = value;}
//    public void setUsername(String value){this.username = value;}
//    public void setPassword(String value){this.password = value;}


}



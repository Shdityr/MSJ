
package com.example.demo2.entity;
import jakarta.persistence.*;

import com.example.demo2.entity.DishEntity;
import java.util.List;
import jakarta.persistence.*;



@Entity
@Table(name = "review2")
public class Review2Entity {
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









//    @ManyToOne
//    @JoinColumn(name = "restaurant_id")
//    private RestaurantEntity restaurant;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private DishEntity dish;





    public int getId(){return id;}
    public int getTime(){return time;}
    public int getUserID(){return userID;}
    public String getContent(){return content;}
    public String getImages(){return images;}
    public float getRating(){return rating;}
    public String getStyle(){return style;}
    public float getAveragePrice(){return averagePrice;}
    public DishEntity getDish(){return dish;}


//    public List<FoodEntity> geta(){return foodList;}

    //    public void setID(Long value){this.id = value;}
//    public void setUsername(String value){this.username = value;}
//    public void setPassword(String value){this.password = value;}


}



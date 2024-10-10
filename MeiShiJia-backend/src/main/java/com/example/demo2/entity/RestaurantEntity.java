
package com.example.demo2.entity;
import jakarta.persistence.*;
        import com.example.demo2.entity.DishEntity;
import com.example.demo2.entity.ReviewEntity;
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
    public String getImages(){return images;}

    public List<DishEntity> getDishList(){return dishList;}

    public List<ReviewEntity> getReviewList(){return reviewList;}

    //    public void setID(Long value){this.id = value;}
//    public void setUsername(String value){this.username = value;}
//    public void setPassword(String value){this.password = value;}


}


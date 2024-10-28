
package com.example.demo2.entity;
import jakarta.persistence.*;
        import com.example.demo2.entity.RestaurantEntity;
import com.example.demo2.entity.ReviewEntity;
import java.util.List;
import jakarta.persistence.*;



@Entity
@Table(name = "dish")
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String images;
    private String name;
    private String store;
    private float rating;
    @Column(name = "average_price")
    private float averagePrice;
    private String style;





    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private List<Review2Entity> reviewList;


    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;





    public int getId(){return id;}
    public String getImages(){return images;}
    public String getName(){return name;}
    public String getStore(){return store;}
    public float getRating(){return rating;}
    public float getAveragePrice(){return averagePrice;}
    public String getStyle(){return style;}

    public RestaurantEntity getRestaurant(){return restaurant;}
    public List<Review2Entity> getReview(){return reviewList;}


//    public List<FoodEntity> geta(){return foodList;}

    //    public void setID(Long value){this.id = value;}
//    public void setUsername(String value){this.username = value;}
//    public void setPassword(String value){this.password = value;}


}



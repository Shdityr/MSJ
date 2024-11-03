
package com.example.demo2.entity;
import jakarta.persistence.*;
import com.example.demo2.entity.RestaurantEntity;
import com.example.demo2.entity.ReviewEntity;
import java.util.List;
import jakarta.persistence.*;



@Entity
@Table(name = "image")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public String name;



    // 使用 @Lob 注解表示这是一个大对象（BLOB）
    @Lob
    @Column(name = "data", columnDefinition = "BLOB")
    public byte[] data;

    public ImageEntity(String name,byte[] data){
        this.id=id;
        this.name=name;
        this.data=data;
    }
    public ImageEntity(){}












    public int getId(){return id;}
    public byte[] getData(){return data;}
    public String getName(){return name;}



}



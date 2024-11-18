
package com.example.demo2.repository;



import com.example.demo2.entity.Review2Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Repository;



/**
 * 公民信息数据库访问接口
 * @author pan_junbiao
 **/
@Repository
public interface Review2Repository extends JpaRepository<Review2Entity, Integer>
{

}
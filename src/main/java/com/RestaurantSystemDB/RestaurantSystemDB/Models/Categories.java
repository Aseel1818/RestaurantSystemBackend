package com.RestaurantSystemDB.RestaurantSystemDB.Models;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Categories implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)

    private Long id ;
    private String categoryName;

    private String categoryImageURL;


    @OneToMany(mappedBy = "category")
    private List<Items> item;



    public Categories() {}


    public Categories(String categoryName , String categoryImageURL  )
    {
        this.categoryName=categoryName;
        this.id=id;
        this.categoryImageURL=categoryImageURL;


    }

    public long getCategoryID(){
        return  id;
    }
    public void setCategoryId(Long categoryId){
        this.id=categoryId;
    }

    public String getCategoryName (){
        return categoryName;
    }
    public void setCategoryName(String categoryName){
        this.categoryName=categoryName;
    }

    public String getCategoryImageURL (){
        return categoryImageURL;
    }
    public void setCategoryImageURL(String categoryImageURL){
        this.categoryImageURL=categoryImageURL;
    }









}


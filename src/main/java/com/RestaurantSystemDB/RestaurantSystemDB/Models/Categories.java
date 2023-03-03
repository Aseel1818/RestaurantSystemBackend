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

    @OneToMany(mappedBy = "category")
    private List<Items> items;

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public Categories() {}

    public Categories(String categoryName)
    {
        this.categoryName=categoryName;
        this.id=id;
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

}


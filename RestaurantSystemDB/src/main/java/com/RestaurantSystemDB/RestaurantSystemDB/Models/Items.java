package com.RestaurantSystemDB.RestaurantSystemDB.Models;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Items implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)

    private Long id ;


    private String itemName;

    private String itemImageURL;

    private float price;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Categories category;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private Set<OrderDetails> orderDetails;

    public Items() {}


    public Items(String itemName , float price ,String itemImageURL)
    {
        this.id=id;
        this.itemImageURL=itemImageURL;
        this.price=price;
        this.itemName=itemName;


    }

    public long getItemID(){
        return  id;
    }
    public void setItemId(Long id){
        this.id=id;
    }

    public String getItemName(){
        return  itemName;
    }
    public void setItemName(String itemName){
        this.itemName=itemName;
    }

    public String getItemImageURL(){
        return  itemImageURL;
    }
    public void setItemImageURL(String imageURL){
        this.itemImageURL=imageURL;
    }

    public float getItemPrice(){
        return  price;
    }
    public void setItemPrice(float price){
        this.price=price;
    }



}


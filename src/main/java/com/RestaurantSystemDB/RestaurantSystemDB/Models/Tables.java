package com.RestaurantSystemDB.RestaurantSystemDB.Models;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Tables implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)

    private Long id ;
    private  boolean tableStatus;
    private String tableName;

   @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "order_id", nullable = true)
    private Orders orders;

    public Tables() {}

    public Tables(boolean tableStatus , String tableName)
    {
        this.tableStatus=tableStatus;
        this.tableName=tableName;
        this.id=id;
    }

    public long getTableID(){
        return  id;
    }
    public void setTableId(Long id){
        this.id=id;
    }

    public String getTableName(){
        return  tableName;
    }
    public void setTableName(String tableName){
        this.tableName=tableName;
    }
    public boolean getTableStatus (){
        return tableStatus;
    }
    public void setTableStatus(boolean tableStatus){this.tableStatus=tableStatus;}
}

package com.paysafe.entities;



import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {


    //    TODO CHECK if this is good
    //    @Column
    //    private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public BaseEntity(String name) {
        this.name = name;
        this.id = 0;
    }

    public BaseEntity() {

    }

    public String getName() {
        if(name==null){
            return "";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

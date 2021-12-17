package com.manu.biblioHibernate.Clases;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_community",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "community_id")}

    )
    private List<Community> communities = new ArrayList<>();

    private String username;
    private String email;
    private int age;

    public User(){}
    public User(String username, String email, int age){
        this.username = username;
        this.email = email;
        this.age = age;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public List<Community> getComunities() {
        return communities;
    }

    public void setComunities(List<Community> comunities) {
        this.communities = comunities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

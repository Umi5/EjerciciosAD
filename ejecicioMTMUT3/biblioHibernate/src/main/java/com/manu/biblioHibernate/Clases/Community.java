package com.manu.biblioHibernate.Clases;

import com.manu.biblioHibernate.Clases.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "community")
public class Community implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long community_id;

    @ManyToMany(mappedBy = "communities")
    private List<User> users = new ArrayList<>();

    private String c_name;
    private String c_type;
    private int c_posts;

    public Community(){}
    public Community(String c_name,String c_type){
        this.c_name = c_name;
        this.c_type = c_type;
        this.c_posts = 0;
    }

    public void increasePost(){
        this.c_posts++;
    }
    public Long getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(Long community_id) {
        this.community_id = community_id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_type() {
        return c_type;
    }

    public void setC_type(String c_type) {
        this.c_type = c_type;
    }

    public int getC_posts() {
        return c_posts;
    }

    public void setC_posts(int c_posts) {
        this.c_posts = c_posts;
    }
}

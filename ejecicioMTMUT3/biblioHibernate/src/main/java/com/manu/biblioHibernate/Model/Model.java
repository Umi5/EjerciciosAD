package com.manu.biblioHibernate.Model;

import com.manu.biblioHibernate.Clases.Community;
import com.manu.biblioHibernate.Clases.User;
import com.manu.biblioHibernate.Main;
import com.manu.biblioHibernate.Utils.HibernateUtility;
import org.hibernate.*;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.util.List;

public class Model {

    public static List<Community> getComs(){
        Session s = Main.getS();
        List<Community> coms;
        Query q = s.createQuery("from Community ");
        coms = q.list();
        return coms;
    }

    public static void createCom(List<String> values){
        Session s = Main.getS();
        Transaction t = s.beginTransaction();
        Community com = new Community();
        com.setC_name(values.get(0));
        com.setC_type(values.get(1));
        com.setC_posts(Integer.parseInt(values.get(2)));
        s.save(com);
        t.commit();
    }

    public static Community getCom(int pos){
        Session s = Main.getS();
        long i = pos;
        Community com =  s.get(Community.class, i);
        return com;
    }

    public static List<User> getUsers(){
        Session s = Main.getS();
        List<User> users;
        Query q = s.createQuery("from User ");
        users = q.list();
        return users;
    }

    public static User getUser(String username){
        Session s = Main.getS();
        Query<User> q = s.createQuery("from User where username = :id");
        q.setParameter("id",username);
        User u = q.getSingleResult();
        return u;
    }

    public static void followCom(String username, int idc){
        Session s = Main.getS();
        Transaction t = s.beginTransaction();
        long cid = idc;
        boolean guardar = true;
        Query<User> q = s.createQuery("from User where username = :name ");
        q.setParameter("name",username);
        User u = q.getSingleResult();
        Community c =  s.get(Community.class, cid);
        for(int i = 0; i < u.getComunities().size(); i++){
            if(u.getComunities().get(i) == c) {
                System.out.println("COMUNIDAD YA SEGUIDA");
                guardar = false;
                break;
            }
        }
        if(guardar){
            u.getComunities().add(c);
            s.save(u);
            t.commit();
        }
    }

    public static void createUser(List<String> values){
        Session s = Main.getS();
        Transaction t = s.beginTransaction();
        User u = new User();
        u.setUsername(values.get(0));
        u.setEmail(values.get(1));
        u.setAge(Integer.parseInt(values.get(2)));
        Query<User> q = s.createQuery("from User where username= :user ");
        q.setParameter("user",values.get(0));
        if(q.list().size() == 1){
            System.out.println("Usuario no crado por tener el mismo username");
        }
        else{
            s.save(u);
            t.commit();
        }
    }

    public static boolean userExists(String username){
        Session s = Main.getS();
        Query<User> q = s.createQuery("from User where username= :user ");
        q.setParameter("user",username);
        if(q.list().size() == 0){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean comExists(String name){
        Session s = Main.getS();
        Query<Community> q = s.createQuery("from Community where c_name= :com ");
        q.setParameter("com",name);
        if(q.list().size() == 0){
            return false;
        }
        else{
            return true;
        }
    }

    public static void modUser(String user, List<String> values){
        Session s = Main.getS();
        Transaction t = s.beginTransaction();
        Query<User> q = s.createQuery("update User SET " +
                "username = :name, " +
                "email = :mail," +
                "age = :edad WHERE username = :old");
        q.setParameter("name", values.get(0));
        q.setParameter("mail",values.get(1));
        q.setParameter("edad", Integer.parseInt(values.get(2)));
        q.setParameter("old", user);
        q.executeUpdate();
        t.commit();
    }

    public static void deleteUser(String username){
        Session s = Main.getS();
        Transaction t = s.beginTransaction();
        Query q = s.createQuery("DELETE FROM User Where username = :user");
        q.setParameter("user", username);
        q.executeUpdate();
        t.commit();

    }

}

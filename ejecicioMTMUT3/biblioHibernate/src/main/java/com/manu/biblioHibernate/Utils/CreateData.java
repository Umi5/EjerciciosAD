package com.manu.biblioHibernate.Utils;

import com.manu.biblioHibernate.Clases.Community;
import com.manu.biblioHibernate.Clases.User;
import org.hibernate.Session;

public class CreateData {
    public static void createData(Session s){
        Community c1 = new Community("Sobre Ruedas", "Deporte");
        c1.setC_posts(2); s.save(c1);
        Community c2 = new Community("Delicioso", "Cocina");
        c2.setC_posts(3); s.save(c2);
        Community c3 = new Community("Aire Fresco", "Naturaleza");
        c3.setC_posts(6); s.save(c3);
        Community c4 = new Community("Al Agua", "Deporte");
        c4.setC_posts(9); s.save(c4);
        Community c5 = new Community("Para chuparse los dedos", "Cocina");
        c5.setC_posts(4); s.save(c5);
        User u1 = new User("juan", "juan@gmail.com", 10);
        User u2 = new User("jose", "jose@gmail.com" , 20);
        User u3 = new User("maria", "maria@gmail.com" ,15);
        User u4 = new User("miguel" , "miguel@gmail.com", 22);
        User u5 = new User("manu", "manu@gmail.com", 21);
        User u6 = new User("naiara", "naiara@gmail.com", 18);
        User u7 = new User("ines", "ines@gmail.com", 16);
        User u8 = new User("izan", "izan@gmail.com",40);
        User u9 = new User("alvaro" , "alvaro@gmail.com", 26);
        User u10 = new User("carla", "carla@gmail.com", 35);

        u1.getComunities().add(c1);
        u1.getComunities().add(c2);
        s.save(u1);

        u2.getComunities().add(c3);
        u2.getComunities().add(c4);
        s.save(u2);

        u3.getComunities().add(c5);
        u3.getComunities().add(c1);
        s.save(u3);

        u4.getComunities().add(c2);
        u4.getComunities().add(c3);
        s.save(u4);

        u5.getComunities().add(c4);
        u5.getComunities().add(c5);
        s.save(u5);

        u6.getComunities().add(c1);
        u6.getComunities().add(c2);
        s.save(u6);

        u7.getComunities().add(c3);
        u7.getComunities().add(c4);
        s.save(u7);

        u8.getComunities().add(c5);
        u8.getComunities().add(c1);
        s.save(u8);

        u9.getComunities().add(c3);
        u9.getComunities().add(c2);
        s.save(u9);

        u10.getComunities().add(c4);
        u10.getComunities().add(c5);
        s.save(u10);
    }
}

package com.manu.biblioHibernate.Utils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class HibernateUtility {
    private static SessionFactory sf = null;

    private HibernateUtility() {
    }

    public static SessionFactory getInstance() {
        if (sf == null) {

            Properties props = readProperties();
            Configuration conf = new Configuration().addProperties(props);
            conf.addAnnotatedClass(com.manu.biblioHibernate.Clases.User.class);
            conf.addAnnotatedClass(com.manu.biblioHibernate.Clases.Community.class);
            sf = conf.buildSessionFactory();
        }
        return sf;
    }

    public static Properties readProperties() {
        Properties props_read = new Properties();

        try {
            props_read.load(new FileReader("hibernate.properties"));

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return props_read;
    }
}
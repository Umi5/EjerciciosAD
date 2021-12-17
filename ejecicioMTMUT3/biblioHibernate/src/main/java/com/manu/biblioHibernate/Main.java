package com.manu.biblioHibernate;

import com.manu.biblioHibernate.Controllers.ComController;
import com.manu.biblioHibernate.Controllers.UserController;
import com.manu.biblioHibernate.Utils.*;
import com.manu.biblioHibernate.View.Interfaces;
import org.hibernate.*;

import java.util.Scanner;

public class Main {

    public static Session getS(){
        SessionFactory sf = HibernateUtility.getInstance();
        Session s = sf.openSession();
        return s;
    }
    public static void main(String[] args) {
        Session s = getS();
        Transaction t = s.beginTransaction();
        System.out.println("CONECTADO");
        CreateData.createData(s);
        t.commit();
        start();
        s.close();
    }

    public static void start() {
        Scanner teclado = new Scanner(System.in);
        int opcion;
        do{
            Interfaces.mainPage();
            opcion = teclado.nextInt();
            switch (opcion){
                case 1:
                    ComController.start();  break;
                case 2: UserController.start(); break;
                default:
                    System.out.println("HASTA LA PROXIMA"); break;
            }
        }while(opcion != 0);
    }
}

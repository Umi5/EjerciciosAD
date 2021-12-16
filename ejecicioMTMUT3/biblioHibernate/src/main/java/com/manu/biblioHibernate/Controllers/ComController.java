package com.manu.biblioHibernate.Controllers;

import com.manu.biblioHibernate.Clases.Community;
import com.manu.biblioHibernate.Model.Model;
import com.manu.biblioHibernate.View.Interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ComController {
    public static void start(){
        Scanner teclado = new Scanner(System.in);
        int opcion;
        do{
            Interfaces.comsIndex();
            opcion = teclado.nextInt();

            switch (opcion){
                case 1: printCom(); break;
                case 2: comCreate(); break;
                default:
                    System.out.println("SALIENDO"); break;
            }
        }while(opcion != 0);
    }

    public static void printCom(){
        Scanner teclado = new Scanner(System.in);
        int opcion, cant;
        do{
            cant = Interfaces.printComs();
            opcion = teclado.nextInt();

            if(opcion == 0){
                System.out.println("SALIENDO");
            }
            else if(opcion < 0 || opcion > cant){
                System.out.println("Inserte un valor correcto");
            }
            else{
                showComsAtrb(opcion);
            }
        }while (opcion != 0);
    }

    public static void comCreate(){
        List<String> values = Interfaces.createCom();
        Model.createCom(values);
    }


    public static void showComsAtrb(int pos){
        Community com = Model.getCom(pos);
        Interfaces.printComAtrb(com);
        System.out.print("Pulsa enter...");
        try{
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

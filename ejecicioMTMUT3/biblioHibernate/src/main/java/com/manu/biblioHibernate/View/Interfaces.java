package com.manu.biblioHibernate.View;

import com.manu.biblioHibernate.Clases.Community;
import com.manu.biblioHibernate.Clases.User;
import com.manu.biblioHibernate.Model.Model;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interfaces {
    public static void mainPage(){
        System.out.println("BIEVENIDO A DERRIT");
        System.out.println("1 - Comunidades");
        System.out.println("2 - Usuarios");
        System.out.println("0 - SALIR");
        System.out.print("Opcion: ");
    }
    public static void comsIndex(){
        System.out.println("ZONA COMUNIDADES");
        System.out.println("1 - Ver comunidades");
        System.out.println("2 - Crear comunidades");
        System.out.println("0 - SALIR");
        System.out.print("Opcion: ");
    }
    public static int printComs(){
        List<Community> coms = Model.getComs();
        System.out.println("COMUNIDADES DISPONIBLES");
        System.out.println("ID - NOMBRE");
        for(int i = 0; i < coms.size(); i++){
            if(i < 9)
                System.out.println((i+1) + "  - " + coms.get(i).getC_name());
            else
                System.out.println((i+1) + " - " + coms.get(i).getC_name());
        }
        System.out.println("0 - SALIR");
        System.out.print("Opcion ID: ");
        return coms.size();
    }

    public static void printComAtrb(Community com){
        System.out.println("Nombre: " + com.getC_name());
        System.out.println("Tipo de comunidad: " + com.getC_type());
        System.out.println("Usuarios seguidores: " + com.getUsers().size());
        for(int i = 0; i < com.getUsers().size(); i++){
            System.out.println("  - " + com.getUsers().get(i).getUsername());
        }
        System.out.println("Posts: " + com.getC_posts());
    }

    public static List<String> createCom(){
        List<String> values = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        System.out.print("Dime el nombre de la comunidad: ");
        values.add(teclado.nextLine());
        System.out.print("Dime el tipo de la comunidad: ");
        values.add(teclado.nextLine());
        System.out.print("Cuantos post iniciales va a tener: ");
        values.add(teclado.nextLine());
        return values;
    }
    public static void userIndex(){
        System.out.println("ZONA USUARIOS");
        System.out.println("1 - Usuarios");
        System.out.println("2 - Crear usuarios");
        System.out.println("0 - SALIR");
        System.out.print("Opcion: ");
    }

    public static void printUsername(){
        System.out.println("DIME UN NOMNBRE DE USUARIO");
        System.out.print("Nombre: ");
    }


    public static void userOptions(){
        System.out.println("USUARIO LOGEADO");
        System.out.println("1 - Ver datos usuario");
        System.out.println("2 - Seguir comunidades");
        System.out.println("3 - Modificar usuario");
        System.out.println("4 - Borrar usuario");
        System.out.println("5 - Escribir usuario en archivo");
        System.out.println("0 - SALIR");
        System.out.print("Opciones: ");
    }

    public static void printUser(User u){
        System.out.println("Username: " + u.getUsername());
        System.out.println("Tipo de comunidad: " + u.getEmail());
        System.out.println("Comunidades que sigues: " + u.getComunities().size());
        for(int i = 0; i < u.getComunities().size(); i++){
            System.out.println("  - " + u.getComunities().get(i).getC_name());
        }
        System.out.println("Edad: " + u.getAge());
    }

    public static List<String> createUsers(){
        List<String> values = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        System.out.print("Dime el nombre de usuario: ");
        values.add(teclado.nextLine());
        System.out.print("Dime tu correo: ");
        values.add(teclado.nextLine());
        System.out.print("Cuantos tu edad: ");
        values.add(teclado.nextLine());
        return values;
    }

    public static void deleteUser(){
        System.out.println("Quieres borrar el usuario?");
        System.out.print("Opcion (s/n): ");
    }

}

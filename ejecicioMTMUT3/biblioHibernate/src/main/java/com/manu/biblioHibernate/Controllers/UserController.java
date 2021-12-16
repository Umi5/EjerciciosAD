package com.manu.biblioHibernate.Controllers;

import com.manu.biblioHibernate.Clases.User;
import com.manu.biblioHibernate.Utils.CreateData;
import com.manu.biblioHibernate.Utils.CreateFile;
import com.manu.biblioHibernate.View.Interfaces;
import com.manu.biblioHibernate.Model.Model;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UserController {
    public static void start(){
        int opcion;
        Scanner teclado = new Scanner(System.in);
        do{
            Interfaces.userIndex();
            opcion = teclado.nextInt();

            switch (opcion){
                case 3:
                    System.out.println("SALIR DE ESTE USUARIO"); break;
                case 1: username();  break; //userList(); break;
                case 2: userCreate(); break;
                default:
                    System.out.println("Opcion no valida"); break;
            }
        }while(opcion != 0);
    }

    public static void username(){
        Scanner teclado = new Scanner(System.in);
        String username;
        int cant = 0;
        do{
            Interfaces.printUsername();
            username = teclado.nextLine();
            if(Model.userExists(username)){
                usersOp(username);
                cant = 3;
            }
            else{
                cant++;
                System.out.println("No existe ese usuario, " +
                        "prueba otra vez (intentos restantes " + (3-cant) + ")");
            }
        }while(cant < 3);
    }

    public static void usersOp(String username){
        Scanner teclado = new Scanner(System.in);
        int opcion;
        do{
            Interfaces.userOptions();
            opcion = teclado.nextInt();

            switch (opcion){
                case 1: showAtrb(username); break;
                case 2: showComs(username); break;
                case 3: modUser(username);
                    System.out.println("Saliendo por modificacion"); opcion = 0; break;
                case 4: if(deleteUser(username)){ opcion = 0; } break;
                case 5: exportUser(username); break;
                case 0:
                    System.out.println("Saliendo"); break;
                default:
                    System.out.println("Opcion no valida"); break;
            }
        }while(opcion != 0);

    }

    public static void exportUser(String username){
        User u = Model.getUser(username);
        CreateFile.createFile(u);
    }
    public static boolean deleteUser(String username){
        String opcion;
        Scanner teclado = new Scanner(System.in);
        Interfaces.deleteUser();
        opcion = teclado.nextLine();
        if(opcion.equals("s")){
            Model.deleteUser(username);
            return true;
        }
        else
            return false;
    }
    public static void modUser(String username){
        List<String> values = Interfaces.createUsers();
        Model.modUser(username,values);
    }

    public static void showAtrb(String username){
        User user = Model.getUser(username);
        Interfaces.printUser(user);
        System.out.print("Pulsa enter...");
        try{
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showComs(String id){
        Scanner teclado = new Scanner(System.in);
        int opcion, cant;
        do{
            cant = Interfaces.printComs();
            opcion = teclado.nextInt();
            if(opcion == cant + 1){
                System.out.println("Saliendo");
            }else{
                Model.followCom(id, opcion);
            }

        }while (opcion != 0);
    }

    public static void userCreate(){
        List<String> values = Interfaces.createUsers();
        Model.createUser(values);
    }
}

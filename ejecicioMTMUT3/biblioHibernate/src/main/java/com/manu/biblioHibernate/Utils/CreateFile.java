package com.manu.biblioHibernate.Utils;

import com.manu.biblioHibernate.Clases.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFile {
    public static void createFile(User u){
        //CREAMOS EL PATH DE CREACION DE NUESTRO ARCHIVO MEDIANTE UN STRINGBUILDER
        StringBuilder path = new StringBuilder();
        path.append("src\\main\\resources\\Files\\");
        path.append(u.getUsername());
        path.append(".txt");
        try{
            //CREAMOS UN FILE WRITER Y GUARDAMOS EN EL TODAS LAS PROPIEDADES
            FileWriter file = new FileWriter(path.toString());
            file.write("Nombre de usuario: " + u.getUsername() + "\n");
            file.write("Email: " + u.getEmail() + "\n");
            file.write("Edad: " + u.getAge() + "\n");
            file.write("Comunidades: \n");
            for(int i = 0; i < u.getComunities().size(); i++){
                file.write("    - " + u.getComunities().get(i).getC_name() + "\n");
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

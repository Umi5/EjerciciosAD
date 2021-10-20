import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args)
    {
	    Properties writeProp = new Properties();
	    writeProp.setProperty("version","1.2.3");
	    writeProp.setProperty("lanzamiento","11/08/2021");
	    writeProp.setProperty("standalone", "yes");
	    writeProp.setProperty("port","5858");

	    try
        {
            writeProp.store(new FileOutputStream("  config.prop"),
                    "Fichero de configuracion del programa X");
        } catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

	    Properties readProp = new Properties();
	    try
        {
            readProp.load(new FileInputStream("config.prop"));
            System.out.println("Version: " + readProp.getProperty("version"));
            System.out.println("Lanzamiento: " + readProp.getProperty("lanzamiento"));
            System.out.println("Standalone: " +
                    (readProp.getProperty("standalone").equals("yes") ? "Si" : "No" ));
            System.out.println("Puerto: " + Integer.valueOf(readProp.getProperty("port")));
        } catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

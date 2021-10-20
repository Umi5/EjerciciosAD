import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("User home directory: " + System.getProperty("user.home"));
        System.out.println("User working directory" + System.getProperty("user.dir"));
        System.out.println("User account name: " + System.getProperty("user.name"));
    }
}

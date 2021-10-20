import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebaBase",
                "root","root");
        int opcion;
        do {
            System.out.println("OPCIONES");
            System.out.println("1. Crear usuario");
            System.out.println("2. Logear usuario");
            System.out.println("3. SALIR");
            System.out.print("Opcion: ");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1 -> createUser(c);
                case 2 -> logUser(c);
                case 3 -> System.out.println("SALIENDO");
                default -> System.out.println("Opcion incorrecta");
            }
        }while (opcion != 3);

    }
    public static void createUser(Connection c) {
        Scanner teclado = new Scanner(System.in);
        Usuario usuario = new Usuario();
        int cant = 0;
        System.out.print("Que nombre de usuario quieres: ");
        usuario.setUserName(teclado.nextLine());
        System.out.print("Que contrasea quieres: ");
        usuario.setPassword(teclado.nextLine());

        try {
            PreparedStatement psCant  = c.prepareStatement("SELECT COUNT(*) FROM users");
            ResultSet rsCant = psCant.executeQuery();
            rsCant.next();
            cant = rsCant.getInt(1) + 1;
            PreparedStatement ps = c.prepareStatement("SELECT username FROM users WHERE username=?");
            ps.setString(1,usuario.getUserName());
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                System.out.println("Nombre de usuario existente");
            else
            {
                PreparedStatement ps2 = c.prepareStatement("INSERT INTO users (username, password, id, dinero)" +
                        "VALUES (?,?,?,?)");
                ps2.setString(1,usuario.getUserName());
                ps2.setString(2,usuario.getPassword());
                ps2.setInt(3,cant);
                ps2.setInt(4,usuario.getDinero());
                System.out.println("USUARIO CREADO");
                ps2.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void logUser(Connection c) {
        Scanner teclado = new Scanner(System.in);
        Usuario user = new Usuario();
        System.out.print("Dime tu usuario: ");
        user.setUserName(teclado.nextLine());
        System.out.print("Dime tu contraseña: ");
        user.setPassword(teclado.nextLine());

        try
        {
            PreparedStatement ps = c.prepareStatement("SELECT password,username FROM users WHERE username=?");
            ps.setString(1,user.getUserName());
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                if(rs.getString("password").equals(user.getPassword()) &&
                        rs.getString("username").equals(user.getUserName()))
                {
                    System.out.println("Usuario logeado");
                    opcionesLog(c,user);
                }

                else
                    System.out.println("Usuario o contraseña invalidos");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void opcionesLog(Connection c, Usuario usuario) {
        Scanner teclado = new Scanner(System.in);
        int opcion;
        System.out.println("QUE QUIERES HACER");
        System.out.println("1. Ingresar dinero");
        System.out.println("2. Sacar dinero");
        System.out.println("3. Abonar dinero");
        System.out.println("4. Cambiar contraseña");
        System.out.print("OPCION: ");
        opcion = teclado.nextInt();

        switch (opcion) {
            case 1 -> ingresarDinero(c,usuario);
            case 2 -> sacarDinero(c, usuario);
            case 3 -> abonarDinero(c,usuario);
            case 4 -> cambiarPass(c,usuario);
            default -> System.out.println("Opcion invalida");
        }
    }
    public static void ingresarDinero(Connection c, Usuario usuario){
        Scanner teclado = new Scanner(System.in);
        int dinero;
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE users SET  dinero = dinero + ? WHERE username = ?");

            System.out.print("Cuanto dinero quieres ingresar: ");
            dinero = teclado.nextInt();
            ps.setInt(1,dinero);
            ps.setString(2,usuario.getUserName());
            ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void sacarDinero(Connection c, Usuario usuario){
        Scanner teclado = new Scanner(System.in);
        int dineroCuenta, dinero;
        try {
            PreparedStatement psDinero = c.prepareStatement("SELECT dinero FROM users WHERE username=? ");
            psDinero.setString(1,usuario.getUserName());
            PreparedStatement ps = c.prepareStatement("UPDATE users SET  dinero =? WHERE username = ?");
            ResultSet rs = psDinero.executeQuery();
            if(rs.next())
            {
                dineroCuenta = rs.getInt("dinero");
                System.out.print("Cuando dinero quieres sacar: ");
                dinero = teclado.nextInt();
                if(dinero <= dineroCuenta)
                {
                    ps.setInt(1,(dineroCuenta-dinero));
                    ps.setString(2,usuario.getUserName());
                    ps.executeUpdate();
                    System.out.println("OPERACION REALIZADA");
                }
                else
                    System.out.println("NO HAY TANTO DINERO EN TU CUENTA");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void abonarDinero(Connection c, Usuario usuario){
        Scanner teclado = new Scanner(System.in);
        try {
            String userAbono;
            int dineroAbono, dineroUser;
            PreparedStatement psUser = c.prepareStatement("SELECT username FROM users WHERE username=?");
            teclado.nextLine();
            System.out.print("A quien quieres abonar el dinero: ");
            userAbono = teclado.nextLine();
            psUser.setString(1,userAbono);
            ResultSet rsUser = psUser.executeQuery();
            if(rsUser.next())
            {
                PreparedStatement psDinero = c.prepareStatement("SELECT dinero FROM users WHERE username=? ");
                psDinero.setString(1,usuario.getUserName());
                ResultSet rsDinero = psDinero.executeQuery();
                rsDinero.next();
                dineroUser = rsDinero.getInt("dinero");
                System.out.print("Cuanto dinero le quieres abonar: ");
                dineroAbono = teclado.nextInt();

                if(dineroAbono <= dineroUser)
                {
                    PreparedStatement psAbono1  = c.prepareStatement("UPDATE users SET dinero = dinero + ? WHERE username = ?");
                    psAbono1.setInt(1,dineroAbono);
                    psAbono1.setString(2,userAbono);
                    psAbono1.executeUpdate();
                    PreparedStatement psAbono2  = c.prepareStatement("UPDATE users SET dinero = dinero - ? WHERE username = ?");
                    psAbono2.setInt(1,dineroAbono);
                    psAbono2.setString(2,usuario.getUserName());
                    psAbono2.executeUpdate();
                }
                else
                    System.out.println("NO HAY TANTO DINERO EN LA CUENTA");
            }
            else
                System.out.println("NO EXISTE EL USUARIO MENCIONADO");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void cambiarPass(Connection c, Usuario usuario){
        Scanner teclado = new Scanner(System.in);
        String newPass;
        try{
            c.setAutoCommit(false);
            PreparedStatement psData = c.prepareStatement("SELECT password  FROM users WHERE username=?");
            psData.setString(1,usuario.getUserName());
            ResultSet rsData = psData.executeQuery();
            PreparedStatement psUpdate = c.prepareStatement("UPDATE users SET password=? WHERE username=?");
            while(rsData.next()) {
                if (rsData.getString("password").equals(usuario.getPassword())) {
                    System.out.print("Dime tu nueva contraseña: ");
                    newPass = teclado.nextLine();
                    if(!usuario.getPassword().equals(newPass)) {
                        psUpdate.setString(1,newPass);
                        psUpdate.setString(2,usuario.getUserName());
                        psUpdate.executeUpdate();
                        c.commit();
                    }
                    else
                        System.out.print("La contraseña es igual que la anterior");
                }
            }
        }catch (SQLException exx){
            exx.printStackTrace();
        }
    }
}

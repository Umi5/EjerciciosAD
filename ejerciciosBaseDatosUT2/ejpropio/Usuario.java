public class Usuario {
    private String userName, password;
    private int id, dinero;

    public Usuario(){
        this.dinero = 0;
    }
    public Usuario(String userName, String password, int id)
    {
        this.userName = userName;
        this.password = password;
        this.id = 0;
        this.dinero = 0;
    }
    //GETTERS
    public int getDinero() {
        return this.dinero;
    }
    public int getId() {
        return this.id;
    }
    public String getPassword() {
        return this.password;
    }
    public String getUserName() {
        return this.userName;
    }
    //SETTERS
    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
    public void setId(int  id) {
        this.id = id;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}

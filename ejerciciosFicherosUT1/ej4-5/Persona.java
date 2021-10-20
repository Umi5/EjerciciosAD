public class Persona {
    private String nombre;
    private int id, edad;
    //DEFAULT
    public Persona(int id, int edad, String nombre)
    {
        this.edad = edad;
        this.id = id;
        this.nombre = nombre;
    }
    //GETTERS
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public void setEdad(int edad)
    {
        this.edad = edad;
    }
    //SETTERS
    public String getNombre()
    {
        return this.nombre;
    }
    public int getId()
    {
        return this.id;
    }
    public int getEdad()
    {
        return this.edad;
    }
}

package server;

public class Tarea {
    private String descripcion;
    private String estado;
    //Constructor sin parametros
    public Tarea(){

    }
    //Constructor con parámetros
    public Tarea (String descripcion, String estado){
        this.descripcion = descripcion;
        this.estado = estado;
    }
    //Creación de geters y setters
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    public String getEstado(){
        return estado;
    }
    public void setEstado(String estado){
        this.estado = estado;
    }
    //Metodo toString para imprimir por pantalla
    @Override
    public String toString() {
        return "Tarea: " + descripcion + ", con estado " + estado;
    }

}



package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private final int puerto = 9876; //Puerto utilizado para la conexión servidor-cliente
    private ServerSocket serverSocket; //Server Socket
    private Socket socket; //Socker cliente
    String mensajeDeCliente; //Variable String para guardar mensaje del cliente
    String nombre; //Variable para guardar el nombre del cliente
    int tareas;
    ArrayList <Tarea> listadeTareas = new ArrayList<>();
    //Creamos constructor (lanzamos el servidor)
    public Server() throws IOException {
        serverSocket = new ServerSocket(puerto);
        socket = new Socket();
    }

    public void iniciarServer() throws IOException {

        //Aceptamos la entrada de datos
        while (true) { //While true para mantener el servidor iniciado mientras haya peticiones del cliente
            System.out.println("Servidor Iniciado.");
            System.out.println("Esperando conexión del cliente");
            socket = serverSocket.accept(); //Aceptamos la conexión del cliente
            System.out.println("Cliente conectado");
            //Mandamos un mensaje al cliente desde el servidor
            DataOutputStream salidaServer = new DataOutputStream(socket.getOutputStream());
            //Escribimos el mensaje
            salidaServer.writeUTF(" Hola! Cual es tu nombre?");
            //Aceptamos entrada de mensajes por parte del Cliente.
            DataInputStream entradaServer = new DataInputStream(socket.getInputStream());
            //Guardamos el nombre del cliente
            nombre = entradaServer.readUTF();
            //Saludamos cliente y preguntamos numero de tareas a realizar
            salidaServer.writeUTF("Bienvenido "+nombre +" ¿Cuantas tareas deseas realizar?");
            tareas = entradaServer.read();
            System.out.println("Se han recibido: " +tareas +" tareas");
            //Bucle for segun numero de tareas
            for(int i=0; i< tareas; i++){
                //Envia datos al cliente
                salidaServer.writeUTF("Introduccion de la tarea: " + (i+1));
                salidaServer.writeUTF("Define la descripción de la tarea: ");
                String descripcion;
                descripcion = entradaServer.readUTF();
                //Imprimimos descripción recibida en el servidor
                System.out.println("Descripción recibida: " + descripcion);
                //Se envian datos al cliente de nuevo
                salidaServer.writeUTF("Indica el estado: ");
                String estado;
                estado = entradaServer.readUTF();
                //Imprimimos en el servidor el estado recibido
                System.out.println("Estado recibido: " + estado);
                //Añadimos tarea a la lista de tareas. (ArrayList)
                listadeTareas.add(new Tarea(descripcion,  estado));
            }
            //Imprimimos y enviamos el listado de tareas al cliente
            System.out.println("Listado de tareas: ");
            salidaServer.writeUTF("Listado de tareas");
            //Imprimimos la lista de Tarea
            for(Tarea tarea : listadeTareas){
                System.out.println(tarea);
            }
            //Enviamos el listado de tareas al cliente
            for(Tarea tarea : listadeTareas){
                salidaServer.writeUTF(tarea.toString());
            }
            socket.close();

        }
    }

    public void finalizarServer() throws IOException {
        serverSocket.close();
        System.out.println("Conexión finalizada");
    }

}

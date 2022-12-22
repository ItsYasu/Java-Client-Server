package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final int puerto = 9876;
    private final String host = "localhost";
    private Socket socket;

    String nombre;
    int tareas;
    String descripcion;
    String estado;

    public Client() throws IOException{
        //Iniciamos la conexión con el servidor
        socket = new Socket(host,puerto);
    }
    public void iniciarCliente() throws IOException{
        //Iniciamos la entrada de datos del servidor.
        DataInputStream entradaCliente = new DataInputStream(socket.getInputStream());
        //Leemos el mensaje del servidor
        System.out.println(entradaCliente.readUTF()); //Mostramos el mensaje del servidor por pantalla
        //Petición para enviar un mensaje al servidor
        DataOutputStream salidaCliente = new DataOutputStream(socket.getOutputStream());
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        //Mandamos nuestro nombre al servidor
        salidaCliente.writeUTF(sc.next());
        //A la espera de mensaje por parte del servidor (numero tareas)
        System.out.println(entradaCliente.readUTF());
        tareas = sc.nextInt();
        System.out.println(tareas);
        //Guardamos el número de tareas
        salidaCliente.write(tareas);
        //Creamos un bucle dependiendo del número de tareas
        for(int i = 0; i<tareas ; i++){
            //Imprimimos la información del servidor
            System.out.println(entradaCliente.readUTF());
            //Imprimimos la información del servidor
            System.out.println(entradaCliente.readUTF());
            //Guardamos la descripción del cliente en la variable.
            descripcion = sc2.nextLine();
            //Mostramos por pantalla la descripción añadida
            System.out.println("Descripcion recibida: " +descripcion);
            //Mandamos información al servidor. Descripción
            salidaCliente.writeUTF(descripcion);
            //Imprimimos la información del servidor
            System.out.println(entradaCliente.readUTF());
            //Guardamos el estado en la variable
            estado = sc2.nextLine() ;
            //Mostramos por pantalla el estado añadido
            System.out.println("Estado recibido" + estado);
            //Mandamos el estado al servidor.
            salidaCliente.writeUTF(estado);

        }
        System.out.println("Leyendo mensajes del servidor");
        //Bucle for para recibir mensajes del servidor, segun tareas haya
        for(int i = 0; i<=tareas; i++){
            System.out.println(entradaCliente.readUTF());
        }
        //Cerramos scanner
        sc.close();





        entradaCliente.close();
        salidaCliente.close();
        socket.close();
    }


}

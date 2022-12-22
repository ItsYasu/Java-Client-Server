package server;

import java.io.IOException;

public class MainServer {

    public static void main(String[] args) throws IOException {
        // Definimos objeto
        Server server = new Server();
        System.out.println("Iniciando servidor . . .");

        // Iniciamos el servidor
        server.iniciarServer();

        // finalizamos el servidor
        server.finalizarServer();
    }
}

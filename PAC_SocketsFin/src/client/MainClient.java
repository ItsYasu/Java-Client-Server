package client;

import java.io.IOException;

public class MainClient {

    public static void main(String[] args) throws IOException {

        // Creamos objeto de Cliente
        Client cliente = new Client();
        System.out.println("Iniciando cliente...");

        // Iniciamos la conexión
        cliente.iniciarCliente();
    }
}

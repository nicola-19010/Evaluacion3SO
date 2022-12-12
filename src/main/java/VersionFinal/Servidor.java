package VersionFinal;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {

        //Defino el puerto del servidor y el tamaño del buffer (donde se almacenaran los mensajes)
        int PUERTO = 6666;
        byte[] buffer = new byte[1024];


        try {
            System.out.println("---SERVIDOR UDP ENCENDIDO---");

            //Creo el socket
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);

            //Listo para atender constantemente peticiones
            while (true) {


                //Preparo la peticion y la recibo
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);

                System.out.println("Peticion recibida");

                //tomo los datos de la peticion y los muestro por pantalla
                String mensaje = new String(peticion.getData());
                System.out.println(mensaje);


                //Obtengo el puerto y la direccion de origen (en base a los datos de la peticion)
                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();

                //Creo un mensaje y se lo entrego buffer en forma de bytes
                //Este mensaje son los colores del Pixel
                mensaje = Herramienta.obtenerColorDelPixel(300,300);
                buffer = mensaje.getBytes();

                //Creo un datagrama y lo envio
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
                socketUDP.send(respuesta);

            }

        } catch (SocketException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
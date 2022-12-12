package VersionFinal;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    public static void main(String[] args) throws InterruptedException {

        //Defino el puerto del servidor y el tamaño del buffer (donde se almacenaran los mensajes)
        int PUERTO_SERVIDOR = 6666;
        byte[] buffer = new byte[1024];


        while (true){
            TimeUnit.SECONDS.sleep(1);
            try {
                //Obtengo la direccion del localhost
                InetAddress DIRECCION_SERVIDOR = InetAddress.getByName("localhost");

                //Creo el socket de UDP
                DatagramSocket socketUDP = new DatagramSocket();


                //Creo un mensaje y se lo entrego buffer en forma de bytes
                String mensaje = "---CLIENTE UDP CONECTADO---";
                buffer = mensaje.getBytes();

                //creo un datagrama y lo envio
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length, DIRECCION_SERVIDOR, PUERTO_SERVIDOR);
                socketUDP.send(peticion);

                //preparo la respuesta y recibo la respuesta
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(respuesta);

                //tomo los datos y lo muestro
                mensaje = new String(respuesta.getData());
                System.out.println(mensaje);

                //cierro el socket
                socketUDP.close();

            } catch (SocketException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }



    }

}
package org.learning;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main {
    /**
     * Method to create a connection with client, read the input, write out the output and then close the connection
     * @param socket Socket
     */
    public static void handleRequest(Socket socket) throws IOException {


        // This input stream can be used to determine the type of request, the body etc.
        // socket.getInputStream();

        try {
            Thread.sleep(5000); // simulating some heavy computations
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String responseString = "Response from TCP server";

        // response has to be formatted
        String response = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/plain\r\n" +
                "Content-Length: "+responseString.length()+"\r\n" +
                "Connection: close\r\n" +
                "\r\n" + responseString;
        socket.getOutputStream().write(response.getBytes(StandardCharsets.UTF_8));
        socket.close();
        System.out.println("closing the socket");
    }

    /** Main driver method */
    public static void main(String[] args) {
        try(ServerSocket ss = new ServerSocket(8085)){
            while(true){
                System.out.println("Waiting for Client.");
                // accept() method opens the port and waits for a client to connect with the open port
                // we want the main thread to accept and create a new connection as soon as possible
                // so we assign the task of processing the I/P and O/P of connection to a separate thread and keep the main thread free
                Socket socket = ss.accept();
                System.out.println("Client connected.");
                Thread t1 = new Thread(()-> {
                    try {
                        handleRequest(socket);
                    } catch (IOException e) { throw new RuntimeException(e); }
                });
                t1.start();
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
package edu.eci.arem;
import edu.eci.arem.TypeFile.Typefiles;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class HttpServer {

    Typefiles file;

    public HttpServer() {
        file = new Typefiles();
            runServer();

    }

    public void runServer() {

        int port = getPort();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);

            boolean running = true;
            while (running) {
                Socket clientSocket = null;
                OutputStream outputStream = null;
                try {
                    System.out.println("Listo para recibir ...");
                    clientSocket = serverSocket.accept();
                    outputStream = clientSocket.getOutputStream();
                } catch (IOException e) {
                    System.err.println("Accept failed.");
                    System.exit(1);
                }

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine, outputLine;

                ArrayList<String> req = new ArrayList<>();

                while ((inputLine = in.readLine()) != null) {

                    req.add(inputLine);

                    if (!in.ready()) {
                        break;
                    }
                }

                outputLine = MysparkServer.get(req, outputStream);
                out.println(outputLine);

                out.close();
                in.close();
                clientSocket.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

    }

    private int getPort() {
        int port;
        if (System.getenv("PORT") != null){
            port = Integer.parseInt(System.getenv("PORT"));
        } else {
            port = 1234;
        }

        return port;
    }

    public static void main(String[] args)  {
        HttpServer httpServer = new HttpServer();
    }
}
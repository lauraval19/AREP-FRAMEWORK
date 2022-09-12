package edu.eci.arem;

import edu.eci.arem.Reponse.Response;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class MysparkServer {
    private static MysparkServer instance;
    private HttpServer httpServer = new HttpServer();


    public static void main(String... args) throws IOException {
        MysparkServer mysparkServer = MysparkServer.getInstance();
        mysparkServer.callServer();
    }

    public static MysparkServer getInstance() {
        return instance;
    }

    public void callServer() throws IOException {
        httpServer.runServer();
    }

    public static String get(ArrayList<String> req, OutputStream outputStream){
        try{
            String contentType = req.get(0).split(" ")[1];

            System.out.println("-----------------------Content type: ------------");
            System.out.println(contentType);

            URI path = new URI(contentType);
            System.out.println(path.getPath());
            if(path.getPath().startsWith("/public")){
                return Response.sendResponse(path,outputStream);
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "error";
    }
}

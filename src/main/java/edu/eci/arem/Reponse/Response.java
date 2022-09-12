package edu.eci.arem.Reponse;


import edu.eci.arem.TypeFile.Typefiles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;

public class Response {

    private static String filesPath = "src/main/";

    public static String getResponse(){
        String res = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Respuesta defecto</title>\n"
                + "</head>"
                + "<body>"
                + "<h2>Esta es una respuesta por defecto</h2>"
                + "</body>"
                + "</html>";
        return res;
    }

    private static boolean isBinary(String ex){
        return ex.endsWith(".jpg") || ex.endsWith(".png");
    }

    private static String loadImage(URI uri, OutputStream outputStream) {
        String outputline;
        String extension = uri.toString().substring(uri.getPath().lastIndexOf(".") + 1);

        System.out.println("----------------------RESPUESTA DESDE RESPONSE----------------");

        System.out.println(Typefiles.tipos.get(extension.toUpperCase()));

        String contentType = "application/json";
        System.out.println(Typefiles.tipos.get(contentType.toUpperCase()) );


        outputline = "HTTP/1.1 200 OK \r\n"
                + "Content-Type: " + Typefiles.tipos.get(contentType.toUpperCase()) + "\r\n"
                + "\r\n";
        System.out.println("-------------"+uri.getPath());
        File file = new File(filesPath + uri.getPath());

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            ImageIO.write(bufferedImage, extension, byteArrayOutputStream);
            dataOutputStream.writeBytes(outputline);
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputline;
    }


    public static String sendResponse(URI path, OutputStream outputStream) {
        StringBuilder outputLine;

        String extension = path.toString();



        if (isBinary(extension)) {
            return loadImage(path, outputStream);
        }

        outputLine = new StringBuilder("HTTP/1.1 200 OK \r\n"
                + "Content-Type: " + Typefiles.tipos.get(extension.toUpperCase()) + "\r\n"
                + "\r\n");

        File file = new File(filesPath + path.getPath());

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String fileLine;

            while ((fileLine = bufferedReader.readLine()) != null) {
                outputLine.append(fileLine);
            }

        } catch (FileNotFoundException e) {
            return "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset=\"UTF-8\">"
                    + "<title>Respuesta de error </title>\n"
                    + "</head>"
                    + "<body>"
                    + "<h2>errorrr</h2>"
                    + "</body>"
                    + "</html>";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputLine.toString();
    }
}

import java.io.*;
import java.net.*;

public class EchoClient {
public static void main(String[] args) throws IOException {
        Socket echoSocket = null;
        //flujo entrada
        PrintWriter out = null;
        //fluja salida
        BufferedReader in = null;
         try {
            echoSocket = new Socket("127.0.0.1", 35000);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
             System.err.println("Don’t know about host!.");
            System.exit(1);
         } catch (IOException e) {
            System.err.println("Couldn’t get I/O for " + "the connection to: localhost.");
            System.exit(1);
         }
         System.out.println("ingrese algo: ");
         BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
         String userInput;
         while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            String inputLine = in.readLine();
             System.out.println("echo: " + inputLine);
             if(inputLine.equals("Respuesta: Bye.")){
                 break;
             };
        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}

package org.example;

import java.net.MalformedURLException;
import java.net.*;
import java.io.*;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        try{
            URL mysite  = new URL("https://www.ibm.com:80/docs/en/cics-ts/5.2?topic=structure-urls-cics-web-support#enlaceinterno");
            System.out.println("protocol: " + mysite.getProtocol());
            System.out.println("Authority: " + mysite.getAuthority());
            System.out.println("Host: " + mysite.getHost());
            System.out.println("Port: " + mysite.getPort());
            System.out.println("Path: " + mysite.getPath());
            System.out.println("Query: " + mysite.getQuery());
            System.out.println("File: " + mysite.getFile());
            System.out.println("Ref: " + mysite.getRef());
        }catch(MalformedURLException ex){
            //Logger.getLogger(App.class.getName().log(level.SERVERE,null,ex));
        }
}
}

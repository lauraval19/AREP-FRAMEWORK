package edu.eci.arem.TypeFile;

import java.util.HashMap;
import java.util.Map;

public class Typefiles {
    public static final Map<String,String> tipos = new HashMap<>();

    public Typefiles(){
        tipos.put("HTML","text/html");
        tipos.put("JS","text/javascript");
        tipos.put("PNG","image/png");
        tipos.put("JPEG","image/jpeg");
        tipos.put("JPG","image/jpg");
        tipos.put("CSS","text/css");
    }
}

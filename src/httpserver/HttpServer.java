package httpserver;

import java.io.File;
import java.net.*;

class HttpServer
{
    public static void main(String args[]) throws Exception {
        ServerSocket soc = new ServerSocket(5217);
        File f = new File("./www-data");
        f.mkdir(); // Si www-data n'existe pas, on le créé !
        
        while(true) {
            Socket inSoc = soc.accept();
            HttpRequest request = new HttpRequest(inSoc);
            request.process();
        }
    }
}
package httpserver;

import java.io.File;
import java.net.*;

class HttpServer 
{
    public static void main(String args[]) throws Exception {
        
        Integer port;
        if(args.length<=0) { // SI pas d'argument, port 5217
            port=new Integer("5217");
        } else {
            port = new Integer(args[0]);
        } 

        ServerSocket soc = new ServerSocket(port.intValue());
        File f = new File("./www-data");
        f.mkdir(); // Si www-data n'existe pas, on le créé !
        
        while(true) {
           new HttpServerThread(soc.accept()); // On lance un nouveau thread
        }
    }
}
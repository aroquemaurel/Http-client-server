/*
 * Classe possédant un thread associé à chaque client
*/

package httpserver;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aroquemaurel
*/
public class HttpServerThread implements Runnable {
    private final Thread _thread;
    private final Socket _socket; 

    HttpServerThread(Socket socket) {
        _socket = socket; 
        _thread = new Thread(this);
        _thread.start(); 
    }

    @Override
    public void run() {
        HttpRequest request;
        try {
            request = new HttpRequest(_socket);
            request.process();
        } catch (Exception ex) {
            Logger.getLogger(HttpServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}

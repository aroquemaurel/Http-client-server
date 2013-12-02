package httpserver;

import java.net.*;

class HttpServer
{
    public static void main(String args[]) throws Exception {
        ServerSocket soc = new ServerSocket(5217);
        while(true) {
            Socket inSoc = soc.accept();
            HttpRequest request = new HttpRequest(inSoc);
            request.process();
        }
    }
}
package httpserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

class HttpRequest {
    private final Socket _clientConn;
    
    public HttpRequest(Socket ClientConn) throws Exception {
        _clientConn = ClientConn;
        
    }
    
    public void process() throws Exception {
        BufferedReader din = new BufferedReader(new InputStreamReader(_clientConn.getInputStream()));
        HttpQuery query = null;
        String request = "";
        String buff;
        
        // On récupère l'entête
        while(((buff = din.readLine()) != null)) {
            if(buff.equals("")) {
                break;
            } else {
                request += buff + "\r\n";
            }
        }
       
        StringTokenizer st = new StringTokenizer(request);
        String header = st.nextToken();
        
        switch (header) {
            case "GET":
                query = new HttpGetQuery(_clientConn, request);
                break;
            case "PUT":
                query = new HttpPutQuery(_clientConn, request);
                break;
            case "HEAD":
                query = new HttpHeadQuery(_clientConn, request);
                break;
        }
        
        query.process();
        query.close();
        _clientConn.close();
    }
}
package httpserver;

import java.io.BufferedOutputStream;
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
        
        String request = din.readLine().trim();
        System.out.println(request);
        StringTokenizer st = new StringTokenizer(request);
        String header = st.nextToken();

        if(header.equals("GET")) {
            query = new HttpGetQuery(_clientConn.getOutputStream(), request);
        } else {
            //query = new Htt; // TODO HttpUnknown
        }
        
        query.sendQuery();

        query.close();
        _clientConn.close();
    }
}
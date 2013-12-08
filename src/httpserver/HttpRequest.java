package httpserver;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
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
        byte[] buffer = new byte[1024];
        boolean endQuery = false;
        
        ByteArrayOutputStream s;
        while(((buff = din.readLine()) != null) && !endQuery) {
            if(buff.equals("")) {
                endQuery = true;
            } else {
                request += buff + "\r\n";
            }
        }
        while((_clientConn.getInputStream().read(buffer)) > 0) {
            
        }
            
        StringTokenizer st = new StringTokenizer(request);
        String header = st.nextToken();

        if(header.equals("GET")) {
            query = new HttpGetQuery(_clientConn.getOutputStream(), request);
        } else {
            query = new HttpPutQuery(_clientConn.getOutputStream(), request);
        }
        
        query.process();

        query.close();
        _clientConn.close();
    }
}
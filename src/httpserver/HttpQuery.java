/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package httpserver;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 *
 * @author aroquemaurel
 */
abstract public class HttpQuery {
    protected String _request;
    protected HttpFile _file;
    protected StringTokenizer _st; 
    protected String _serverLine;
    protected BufferedOutputStream _out;
    protected Socket _clientConn;
    protected int _contentsSize;

    public int getContentsSize() {
        return _contentsSize;
    }
    public HttpQuery(Socket clientConn, String request) throws IOException {
        _request = request;
        _st = new StringTokenizer(request);
        
        _st.nextToken(); // Correspond au get ou put, déjà récupéré
        _file = new HttpFile(_st.nextToken(), clientConn.getInputStream(), _contentsSize);
        _serverLine = "Simple HTTP Server\r\n";
        _out = new BufferedOutputStream(clientConn.getOutputStream());
        _clientConn = clientConn;
    }
    
    public String getContentLengthLine() throws IOException {
        return "Content-Length: "+ _file.getFileSize().toString() + "\r\n\r\n";
    }
    
    abstract protected String getStatusLine();
    abstract protected String getContentTypeLine();
    abstract protected String getQuery()throws Exception;
    
    protected void process() throws IOException, Exception {
        _out.write(getQuery().getBytes());
    }
    
    protected void close() throws IOException {
        _out.close();
    }
}

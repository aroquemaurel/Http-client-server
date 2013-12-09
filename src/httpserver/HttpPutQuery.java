/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package httpserver;

import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 *
 * @author aroquemaurel
 */
public class HttpPutQuery extends HttpQuery {
    public HttpPutQuery(Socket clientConn, String request) throws IOException {
        super(clientConn, request);
        String[] splits = request.split("\n");
        StringTokenizer st;
        String header;
        
        for(int i = 0 ; i < splits.length ; ++i) {
            st = new StringTokenizer(splits[i]);
            header = st.nextToken();
            System.out.println(header);
            if(header.equals("Contents-length:")) {
                _contentsSize = Integer.parseInt(st.nextToken());
            }
        }
    }   
    
    @Override
    protected String getStatusLine() {
        String status = "HTTP/1.1 ";
        
        if(!_file.isFileCreated()) {
            status += "201 Created"; 
        } else if(!_file.permssions()) {
            status += "403 forbidden"; 
        } else {
            status += "200 OK";
        }
        
        return status;
    }

    @Override
    protected String getQuery() throws Exception {
        String ret = getStatusLine() + _serverLine + getContentTypeLine() + getContentLengthLine();
        return ret;
    }

    @Override
    protected String getContentTypeLine() {
        return "truc\r\n";//Content-type: text/html\r\n"; // TODO
    }

    @Override
    protected void process() throws Exception {
        super.process();
        _file.saveFile(_clientConn.getInputStream());
        
        _out.close();
    }
    
}

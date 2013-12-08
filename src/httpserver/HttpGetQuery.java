/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package httpserver;

import java.io.OutputStream;

/**
 *
 * @author aroquemaurel
 */
public class HttpGetQuery extends HttpQuery {
    public HttpGetQuery(OutputStream output, String request) {
        super(output, request);
        
        if(!_file.exist()) {
            _file = new File("/404.html");
        }
    }

    @Override
    protected String getStatusLine() {
        String status;
        if(!_file.exist()) {
            status = "HTTP/1.0 404 File not found\r\n";
        } else if(!_file.permssions()) {
            status = "HTTP/1.0 403 Forbidden\r\n";
        } else {
            status = "HTTP/1.0 200 OK\r\n";
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
        return "Content-type: text/html\r\n"; // TODO
    }

    @Override
    protected void process() throws Exception {
        super.process();
        _file.sendFile(_out);
    }
    
}

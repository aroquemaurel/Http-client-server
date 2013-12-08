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
public class HttpPutQuery extends HttpQuery {
    private String _data;
    public HttpPutQuery(OutputStream output, String request) {
        super(output, request);
        String[] split = request.split("\r\n");
        _data = split[2]; // Dès qu'on trouve \r\n\r\n
        
    }   
    
    @Override
    protected String getStatusLine() {
        String status = "";
        
        // TODO
        return status;
    }

    @Override
    protected String getQuery() throws Exception {
     //   String ret = getStatusLine() + _serverLine + getContentTypeLine() + getContentLengthLine();
            String ret = "couc";
        return ret;
    }

    @Override
    protected String getContentTypeLine() {
        return "";//Content-type: text/html\r\n"; // TODO
    }

    @Override
    protected void process() throws Exception {
        super.process();
        //_file.saveFile(_data.getBytes());
    }
    
}

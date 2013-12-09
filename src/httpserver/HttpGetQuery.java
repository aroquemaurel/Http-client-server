/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package httpserver;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author aroquemaurel
 */
public class HttpGetQuery extends HttpHead {
    public HttpGetQuery(Socket clientConn, String request) throws IOException {
        super(clientConn, request);
    }

    @Override
    protected void process() throws Exception {
        super.process();
        _file.sendFile(_out);
    }
    
}

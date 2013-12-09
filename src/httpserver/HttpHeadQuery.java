package httpserver;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author aroquemaurel
 */
public class HttpHeadQuery extends HttpQuery {    
    public HttpHeadQuery(Socket clientConn, String request) throws IOException {
        super(clientConn, request);
        
        if(!_file.isFile()) {
            _file = new HttpFile("www-data/404.html", clientConn.getInputStream());
        }
    }

    @Override
    protected String getStatusLine() {
        String status;
        if(!_file.isFile()) {
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
    }

}

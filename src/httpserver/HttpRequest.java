package httpserver;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

class HttpRequest
{
    private final Socket ClientConn;
    
    public HttpRequest(Socket ClientConn) throws Exception {
        this.ClientConn = ClientConn;
        
    }
    
    public void process() throws Exception {
        BufferedReader din = new BufferedReader(new InputStreamReader(ClientConn.getInputStream()));

        OutputStream ot = ClientConn.getOutputStream();
        BufferedOutputStream out = new BufferedOutputStream(ot);

        String request=din.readLine().trim();
        System.out.println(request);
        StringTokenizer st = new StringTokenizer(request);
        
        String header = st.nextToken();
        
        if(header.equals("GET")) {
            String fileName = st.nextToken();
            FileInputStream fin = null;
            boolean fileExist = true;
            try {
                fin = new FileInputStream(fileName);
            } catch(FileNotFoundException ex) {
                fileExist =false;
            }
            
            String ServerLine = "Simple HTTP Server";
            String StatusLine;
            String ContentTypeLine;
            String ContentLengthLine;
            String ContentBody = null;
            
            if(fileExist) {
                StatusLine="HTTP/1.0 200 OK";
                ContentTypeLine="Content-type: text/html";
                ContentLengthLine="Content-Length: "+ (new Integer(fin.available()).toString());                
            } else {
                StatusLine = "HTTP/1.0 200 OK";
                ContentTypeLine="Content-type: text/html";
                ContentBody = "<HTML>" + 
                                "<HEAD><TITLE>404 Not Found</TITLE></HEAD>" +
                            "<BODY>404 Not Found" +                                               
                                "</BODY></HTML>" ;
                ContentLengthLine = (new Integer(ContentBody.length()).toString());
            }
            
            out.write(StatusLine.getBytes());
            out.write( ServerLine.getBytes());
            out.write(ContentTypeLine.getBytes());
            out.write( ContentLengthLine.getBytes());

            if(fileExist) {
                byte[] buffer = new byte[1024] ;
                int bytes = 0 ;
                while ((bytes = fin.read(buffer)) != -1 ) {
                    out.write(buffer, 0, bytes);
                    for(int iCount = 0 ; iCount < bytes ; ++iCount) {
                        int temp=buffer[iCount];
                        System.out.print((char)temp);
                    }
                }    
                
                fin.close();
            } else {
                out.write(ContentBody.getBytes());
            }

            out.close();
            ClientConn.close();
        }
    }
}
/**
 * Fichier http
 */

package httpserver;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author aroquemaurel
 */
public class HttpFile extends File {
    private final InputStream _inputStream;
    private boolean _fileCreated;
    private int _size;
    
    public HttpFile(String path, InputStream input, final int size) {
        super("www-data/"+path);
        _inputStream = input;
    }
    
    public HttpFile(String path, InputStream input) {
        super("www-data/"+path);
        _inputStream = input;
    }

    public void sendFile(BufferedOutputStream out) throws IOException {
        byte[] buffer = new byte[1024] ;
        int bytes;
        FileInputStream input;
                
        try {
            input = new FileInputStream(this);
        } catch(java.io.FileNotFoundException ex) {
            input = new FileInputStream("www-data/404.html");
        }
        
        while ((bytes = input.read(buffer)) > 0 ) {
            out.write(buffer, 0, bytes);
        }    
        input.close();
    }
    
    public void saveFile(InputStream input) throws IOException {
        int read;
        byte[] bytes = new byte[1024];
        try (FileOutputStream output = new FileOutputStream(this)) {
            if(!isFile()) {
                createNewFile();
            } else if(!canWrite()) {
                return;
                // TODO erreur droits → forbidden
            }
            while ((read = input.read(bytes)) > 0) {
                output.write(bytes, 0, read);
            }
            output.flush();
        }
        input.close();
    }
    
    public Integer getFileSize() throws IOException {
        return new Integer(_inputStream.available());
    }
    
    public boolean permssions() {
        return (canWrite()); 
    }
    
    public void close() throws IOException {
        _inputStream.close();
    }
    
    public boolean isFileCreated() {
        return _fileCreated;
    }
    
}

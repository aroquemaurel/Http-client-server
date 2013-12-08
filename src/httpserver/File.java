package httpserver;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aroquemaurel
 */
public class File {
    private final String _path;
    private boolean _fileExist;
    private FileInputStream _inputStream;
    private FileOutputStream _outputStream;
    
    public File(String path) {
        _path = "www-data/"+path;
        _fileExist = true;
        try {
            _outputStream = new FileOutputStream(_path, true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            _inputStream = new FileInputStream(_path);
        } catch(FileNotFoundException ex) {
            _fileExist = false;
        }
    }
    
    public void sendFile(BufferedOutputStream out) throws IOException {
        byte[] buffer = new byte[1024] ;
        int bytes;
        
        while ((bytes = _inputStream.read(buffer)) != -1 ) {
            out.write(buffer, 0, bytes);
            for(int iCount = 0 ; iCount < bytes ; ++iCount) {
                int temp = buffer[iCount];
                System.out.print((char)temp);
            }
        }    
    }
    
    public void saveFile(byte[] str) throws IOException {
        if(!_fileExist) {
            java.io.File f = new java.io.File(_path);
            f.createNewFile();
             // TODO erreur droits → forbidden
        } 
        
        _outputStream.write(str);
        _outputStream.flush();
    }
    
    public Integer getFileSize() throws IOException {
        return new Integer(_inputStream.available());
    }
    
    public boolean permssions() {
        return true; // TODO
    }
    
    public boolean exist() {
        java.io.File f = new java.io.File(_path);
        return f.exists();
    }
    
    public void close() throws IOException {
        //_inputStream.close();
        _outputStream.close();
    }
}

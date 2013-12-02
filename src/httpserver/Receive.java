/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package httpserver;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aroquemaurel
 */
public class Receive {
    protected String _query;
    
    public Receive() {
        _query = "";
    }

    public boolean addData(String data) {
        _query += data;
        return true;
    }
    
    public Query interpreter() {
        String[] splits = _query.split(" ");
        System.out.println(splits[0] + "="+splits[1] +"="+ splits[2]);
        
        return new Query(splits[1],splits[2]);
    }

}

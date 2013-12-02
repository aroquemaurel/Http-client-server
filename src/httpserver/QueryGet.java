/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package httpserver;

/**
 *
 * @author aroquemaurel
 */
public class QueryGet extends Query {

    public QueryGet(String pHttpVersion, String pFileUrl) {
        super(pHttpVersion, pFileUrl);
    }

    @Override
    public void treatment() {
    }
    
}

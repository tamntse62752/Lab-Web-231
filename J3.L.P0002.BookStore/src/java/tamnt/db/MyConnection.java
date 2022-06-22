/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.db;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


/**
 *
 * @author SIMON
 */
public class MyConnection {
    
    public MyConnection() {
    }
    
    public static Connection getMyConnection() throws Exception {
        Context context=new InitialContext();
        Context tomcatContext= (Context)context.lookup("java:comp/env");
        DataSource ds=(DataSource)tomcatContext.lookup("LPD3");
        Connection con=ds.getConnection(); 
        return con;
    }
    
}

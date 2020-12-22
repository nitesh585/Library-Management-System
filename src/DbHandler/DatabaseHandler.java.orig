package DbHandler;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

public final class DatabaseHandler {
    private static DatabaseHandler dbHandler = null;
    private static final String db_url = "jdbc:derby:Database;create = true";
    private static Connection conn = null;
    private static Statement smt = null;
   
   private DatabaseHandler(){
        createDatabase();
        createTable();
    }
   
   public static DatabaseHandler getInstance(){
       if(dbHandler==null){
           dbHandler = new DatabaseHandler();
       }
       return dbHandler;
   }
    
    public void createDatabase(){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn  =  DriverManager.getConnection(db_url);
        } catch (Exception ex) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText("Database is not created");
            a.show();
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createTable(){
        try {
            String Table_name = "Books";
            smt = conn.createStatement();
            DatabaseMetaData metadata = conn.getMetaData();
            ResultSet Tables = metadata.getTables(null,null, Table_name.toUpperCase(), null);
            if(Tables.next()){        
                System.out.println("Table exits");
            }else{
                     String query = "CREATE TABLE "+Table_name.toUpperCase() + " "
                    + "(BookId varchar(30) primary key,"
                    + "BookName varchar(200),"
                    + "AuthorName varchar(200),"
                    + "PublisherName varchar(200))";
                 smt.execute(query);    
            }
        } catch (SQLException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText("Table is not created");
            a.show();
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet execQuery(String q){
        ResultSet  res = null;
        try {
            smt =  conn.createStatement();
            res = smt.executeQuery(q);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public Boolean execAction(String q){
        try {
            smt = conn.createStatement();
            smt.execute(q);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
  
    public Boolean deleteRow(String q){
        try {
            smt = conn.createStatement();
            smt.executeUpdate(q);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}

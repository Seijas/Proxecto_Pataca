package proxectopataca05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * @version v0.5
 * 
 * @author Daniel Seijas
 * @author Micael Remi
 */
class ConnectSQLite {
    
    private boolean connected = false;
    private Connection connect;
    private final String url = "SQLiteScores.db";
    
    protected ConnectSQLite(){
        
        try{
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection("jdbc:sqlite:" + url);
            this.setConnected(true);
            
        }catch(SQLException e){
            
            this.setConnected(false);
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setConnected(boolean connected){
        this.connected = connected;
    }
    
    protected boolean getConnected(){
        return connected;
    }
    
    protected boolean close(){
        
        try{
            
            connect.close();
            return true;
            
        }catch(SQLException e){
            
            Logger.getLogger(ConnectSQLite.class.getName()).log(Level.SEVERE, null, e);
            return false;
            
        }
    }
    
    protected void searchScores(String sql, DefaultTableModel tabla){
        
        try{
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            String fila [] = new String[4];
            
            int contador = 0;
            
            while(rs.next() && contador<10){  //position, nick, score
                contador++;
                fila [0] = String.valueOf(contador);
                fila [1] = rs.getString(2);
                fila [2] = String.valueOf(rs.getInt(1));
                
                tabla.addRow(fila);
            }
            
            rs.close();
            st.close();
            
        }catch(SQLException ex){
            System.err.printf(ex.getMessage());
        }
    }
    
    protected void saveScore(String sql){
        
        try{
            
            Statement st = connect.createStatement();
            
            st.executeUpdate(sql);
            
            st.close();
            
        }catch(SQLException ex){
            System.err.printf(ex.getMessage());
        }
    }
    
}

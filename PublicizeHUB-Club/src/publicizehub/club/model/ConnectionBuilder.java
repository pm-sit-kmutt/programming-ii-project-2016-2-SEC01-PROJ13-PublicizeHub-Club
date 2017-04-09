/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.model;

import java.sql.*;

/**
 *
 * @author Imagine
 */
public class ConnectionBuilder {
    /* SQL Class */
    private Connection connect = null;
    private Statement s = null;
    private PreparedStatement ps = null;
    private ResultSet result;
    
    /* Account and Host */
//    private String host = "sql12.freemysqlhosting.net";
//    private String dbName = "sql12165289";
//    private String user = "sql12165289";
//    private String password = "7gERPRxZSP";
    /* Account in KMUTT Host */
    private String host = "10.4.56.11";
    private String dbName = "publicClub";
    private String user = "public-1";
    private String password = "public";
    private String account = "?user="+user+"&password="+password+"&characterEncoding=UTF-8";
    private String table = "";
    private String sql = "";
    
    /** #howto
     * How to Use Method
     * 1.connect by choose method statement
     * 2.logout at end program
     */
    
    public void connecting(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://"+host+"/"+ dbName + account);
//            connect = DriverManager.getConnection("jdbc:mysql://10.4.56.8/publicClub","public-1","public");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConnect() {
        return connect;
    }

    public void setConnect(Connection connect) {
        this.connect = connect;
    }
    
    
    
    public void connectWithStatement(String command,int mode){
        connecting();
        try{
            s = connect.createStatement();
            sql = command;
            if(mode==2){
                s.executeQuery(sql);
            }   
            else s.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void connectWithPrepared(String command,String table){
        connecting();
        try{
            ps = connect.prepareStatement(command+" "+table);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void logout(){
        try {
            if(s!=null){
                s.close();
            }
            if(ps!=null){
                ps.close();
            }
            if(connect!=null){
                connect.close();  
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
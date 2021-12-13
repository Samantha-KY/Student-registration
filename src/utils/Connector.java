package utils;
import java.sql.*;
import java.sql.SQLException;
public class Connector {
    private String url = "jdbc:postgresql://localhost:5432/FM";
    private String username = "postgres";
    private String password = "qwerty";
    
    protected Connection conn = null;
    protected Statement st = null;
    protected PreparedStatement pst = null;
    protected ResultSet rs = null;
    
    public void connect(){
        try{
            conn = DriverManager.getConnection(url, username, password);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getSt() {
        return st;
    }

    public void setSt(Statement st) {
        this.st = st;
    }

    public PreparedStatement getPst() {
        return pst;
    }

    public void setPst(PreparedStatement pst) {
        this.pst = pst;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
    
    public void disconnect(){
        try{
            if(st != null){
                st.close();
            }
            if(pst != null){
                pst.close();
            }
            if(rs != null){
                rs.close();
            }
            if(conn != null){
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

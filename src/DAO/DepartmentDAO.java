package DAO;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Department;
import models.Student;
import utils.Connector;
public class DepartmentDAO  extends Connector{
    public DepartmentDAO(){
        connect();
        try{
            st = conn.createStatement();
            final String query = "create table if not exists department"
                    + "("
                    + "id varchar(5) primary key,"
                    + "deptname varchar(30),"
                    + "depthead varchar(30)"
                    + ");";
            st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            disconnect();
        }
    }
    public void createDepartment(Department d){
           connect();
           try{
               pst = conn.prepareStatement("insert into department values(?,?,?);");
               pst.setString(1, d.getDeptID());
               pst.setString(2, d.getDeptName());
               pst.setString(3, d.getDeptHead());
               pst.executeUpdate();
           } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
               disconnect();
           }
    }
    public void updateDepartment(Department d){
        connect();
        try{
            pst = conn.prepareStatement("update department set deptname = ?, depthead = ? where id = ?;");
            pst.setString(1, d.getDeptName());
            pst.setString(2, d.getDeptHead());
            pst.setString(3, d.getDeptID());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            disconnect();
        }
    }
    public void deleteDepartment(String deptId){
           connect();
           try{
               pst = conn.prepareStatement("delete from department where id = ?;");
               pst.setString(1, deptId);
               pst.executeUpdate();
           }catch(SQLException e){
               e.printStackTrace();
           }finally{
               disconnect();
           }
    }
    public ResultSet listDepartment(){
        connect();
        List<Department> listofDepartments = new ArrayList<>();
        try{
            st = conn.createStatement();
            final String query = "select * from department;";
            rs = st.executeQuery(query);

                return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }finally{

        }}
        
        public ResultSet searchById(String deptID){
            connect();
            try{
                pst = conn.prepareStatement("select * from department where id = ?;");
                pst.setString(1, deptID);
                rs = pst.executeQuery();
                
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
            
            return rs;
        }
       
    
}

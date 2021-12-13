package DAO;
import utils.Connector;
import models.Student;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class StudentDAO extends Connector{
    public StudentDAO(){
        connect();
        try{
            st = conn.createStatement();
            final String query = "create table if not exists students("
                    + "stId varchar(5) primary key,"
                    + "stName varchar(50) not null,"
                    + "deptId varchar(5),"
                    + "dob date,"
                    + "foreign key(deptId) references department(id) on delete restrict on update cascade"
                    + ");";
            st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            disconnect();
        }
    }
    public void createStudent(Student s){
          connect();
          try{
              pst = conn.prepareStatement("insert into students values(?,?,?,?);");
              pst.setString(1, s.getStId());
              pst.setString(2, s.getStName());
              pst.setString(3, s.getDeptID());
              pst.setDate(4, Date.valueOf(s.getDob()));
              pst.executeUpdate();
          } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
              disconnect();
          }
    }
    public void updateStudent(Student s){
        connect();
        try{
            pst = conn.prepareStatement("update students set stName = ?, deptId = ?, dob = ? where stId = ?;");
            pst.setString(1, s.getStName());
            pst.setString(2, s.getDeptID());
            pst.setDate(3, Date.valueOf(s.getDob()));
            pst.setString(4, s.getStId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            disconnect();
        }
    }
    public void deleteStudent(String studentId){
        connect();
        try{
            pst =conn.prepareStatement("delete from students where stId = ?;");
            pst.setString(1, studentId);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            disconnect();
        }
    }
    public ResultSet listStudents(){
        connect();
        try{
            st = conn.createStatement();
            rs = st.executeQuery("select * from students;");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }
    public ResultSet searchStudent(String studentId){
        connect();
        try{
            pst = conn.prepareStatement("select * from students where stId = ?;");
            pst.setString(1, studentId);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return rs;
    }
    
}

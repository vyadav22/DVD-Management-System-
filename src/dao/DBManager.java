/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.AppContext;
import util.DVD;
import util.User;
import static util.AppContext.* ;


public class DBManager {

  private Connection connect = null;
  private Statement statement = null;
  SimpleDateFormat mySqlSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public DBManager(){
      try {
        Class.forName("com.mysql.jdbc.Driver");
          String dbip = (String) AppContext.configMap.get("DB_IP");
          String dbPort = (String) AppContext.configMap.get("DB_PORT");
          String dbName = (String) AppContext.configMap.get("DB_NAME");
          String userName = (String) AppContext.configMap.get("DB_USER");
          String userPasswd = (String) AppContext.configMap.get("DB_PASSWORD");

          
          connect = DriverManager.getConnection("jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "dvdrentalmgmtsystem" ,"root","Samuli11");

          // statements allow to issue SQL queries to the database
          statement = connect.createStatement();
          connect.setAutoCommit(false);
          
      }catch(Exception e){
          JOptionPane.showMessageDialog(null, " Exception occured in database connection. \n" + e.getMessage());
          System.exit(0);
      }// end try-catch
  }// end constructor

  public String saveDVD(DVD d){
      PreparedStatement preparedStatement = null ;
      try {
          long formId = -1 ;
          preparedStatement = connect.prepareStatement("insert into  DVD(DVD_ID, DVD_NAME, DVD_CATEGORY, DVD_DIRECTOR, DVD_YEAR, DVD_Price) values (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS );
          preparedStatement.setString(1, d.getId());
          preparedStatement.setString(2, d.getName());
          preparedStatement.setString(3, d.getCategory());
          preparedStatement.setString(4, d.getDirector());
          preparedStatement.setInt(5, d.getYear());
          preparedStatement.setInt(6, d.getPrice());
          
          preparedStatement.executeUpdate();
          ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                formId = rs.getLong(1);
                //System.out.println(" Form Id : " + formId) ;
            }
          //System.out.println(" DVD ADDED.");
          connect.commit();
          return null ;
      }catch(SQLException se){
            try {
                connect.rollback();
                System.out.println("Error occured in saving DVD data. " + se.getMessage());
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return se.getMessage();
      }catch(Exception e){
            System.out.println("Error occured while saving DVD Data. " + e.getMessage());
            return e.getMessage();
      }finally{
              try {
                    if(preparedStatement != null)
                        preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
      }// end try-catch-finally
  }// end method saveForm

  public ArrayList getAllDVDIds(){
      ArrayList returnList = new ArrayList();
        ResultSet rs = null ;
        PreparedStatement ps = null ;
        try {
            String sql = " SELECT distinct DVD_ID FROM DVD " ;
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                returnList.add(rs.getString("DVD_ID"));
            } // end while
            // end while
            return returnList;
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO, null, e);
            return null;
        }finally{
              try {
                    if(ps != null)
                        ps.close();
                    if(rs != null)
                        rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
      }// end try-catch-finally
    }

  public ArrayList getAvailableDVDIds(){
      ArrayList returnList = new ArrayList();
        ResultSet rs = null ;
        PreparedStatement ps = null ;
        try {
            String sql = " SELECT DVD_ID FROM DVD WHERE DVD_AVAILABLE = " + AppContext.DVD_STATUS_AVAILABLE ;
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                returnList.add(rs.getString("DVD_ID"));
            } // end while
            // end while
            return returnList;
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO, null, e);
            return null;
        }finally{
              try {
                    if(ps != null)
                        ps.close();
                    if(rs != null)
                        rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
      }// end try-catch-finally
    }
  public DVD getDVDbyId(String Id){
        ResultSet rs = null ;
        PreparedStatement ps = null ;
        try {
            String sql = "SELECT DVD_NAME, DVD_CATEGORY, DVD_DIRECTOR, DVD_YEAR, DVD_PRICE FROM DVD WHERE DVD_ID = '" + Id + "' ";
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()) {
                DVD d = new DVD();
                d.setId(Id);
                d.setName(rs.getString(1));
                d.setCategory(rs.getString(2));
                d.setDirector( rs.getString(3));
                d.setYear(rs.getInt(4));
                d.setPrice(rs.getInt(5));
                return d;
            } // end while %>
            // end while %>
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO, null, ex);
            return null;
        }catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO, null, e);
            return null;
        }finally{
              try {
                    if(ps != null)
                        ps.close();
                    if(rs != null)
                        rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
      }// end try-catch-finally
  }// end method

public ArrayList getDVDSearchByName(String name){
        ArrayList returnList = new ArrayList();
        ResultSet rs = null ;
        PreparedStatement ps = null ;
        try {
            String sql = " SELECT DVD_ID, DVD_NAME, DVD_CATEGORY, DVD_DIRECTOR, DVD_YEAR, DVD_PRICE FROM DVD WHERE DVD_NAME LIKE '%" + name + "%' " ;
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap map = new HashMap();
                map.put(DVD_ID, rs.getString(1));
                map.put(DVD_NAME, rs.getString(2));
                map.put(DVD_CATEGORY, rs.getString(3));
                map.put(DVD_DIRECTOR, rs.getString(4));
                map.put(DVD_YEAR, rs.getInt(5));
                map.put(DVD_RENT, rs.getInt(6));
                returnList.add(map);
            } // end while %>
            // end while %>
            return returnList;
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO, null, ex);
            return null;
        }catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO, null, e);
            return null;
        }finally{
              try {
                    if(ps != null)
                        ps.close();
                    if(rs != null)
                        rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
      }// end try-catch-finally
  }// end method

public ArrayList getDVDSearchByCategory(String category){
        ArrayList returnList = new ArrayList();
        ResultSet rs = null ;
        PreparedStatement ps = null ;
        try {
            String sql = " SELECT DVD_ID, DVD_NAME, DVD_CATEGORY, DVD_DIRECTOR, DVD_YEAR, DVD_PRICE FROM DVD WHERE DVD_CATEGORY LIKE '%" + category + "%' " ;
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap map = new HashMap();
                map.put(DVD_ID, rs.getString(1));
                map.put(DVD_NAME, rs.getString(2));
                map.put(DVD_CATEGORY, rs.getString(3));
                map.put(DVD_DIRECTOR, rs.getString(4));
                map.put(DVD_YEAR, rs.getInt(5));
                map.put(DVD_RENT, rs.getInt(6));
                returnList.add(map);
            } // end while %>
            // end while %>
            return returnList;
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO, null, ex);
            return null;
        }catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO, null, e);
            return null;
        }finally{
              try {
                    if(ps != null)
                        ps.close();
                    if(rs != null)
                        rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
      }// end try-catch-finally
  }// end method

public ArrayList getDVDSearchByYear(int year){
        ArrayList returnList = new ArrayList();
        ResultSet rs = null ;
        PreparedStatement ps = null ;
        try {
            String sql = " SELECT DVD_ID, DVD_NAME, DVD_CATEGORY, DVD_DIRECTOR, DVD_YEAR, DVD_PRICE FROM DVD WHERE DVD_YEAR > " + year ;
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap map = new HashMap();
                map.put(DVD_ID, rs.getString(1));
                map.put(DVD_NAME, rs.getString(2));
                map.put(DVD_CATEGORY, rs.getString(3));
                map.put(DVD_DIRECTOR, rs.getString(4));
                map.put(DVD_YEAR, rs.getInt(5));
                map.put(DVD_RENT, rs.getInt(6));
                returnList.add(map);
            } // end while %>
            // end while %>
            return returnList;
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO, null, ex);
            return null;
        }catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO, null, e);
            return null;
        }finally{
              try {
                    if(ps != null)
                        ps.close();
                    if(rs != null)
                        rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
      }// end try-catch-finally
  }// end method

  public String updateDVD(DVD d){

      PreparedStatement preparedStatement = null ;
      try {
          preparedStatement = connect.prepareStatement("UPDATE DVD SET DVD_NAME = ?, DVD_CATEGORY = ?, DVD_DIRECTOR = ? , DVD_YEAR = ?, DVD_PRICE = ? WHERE DVD_ID = ? ");
          preparedStatement.setString(1, d.getName());
          preparedStatement.setString(2, d.getCategory());
          preparedStatement.setString(3, d.getDirector());
          preparedStatement.setInt(4, d.getYear());
          preparedStatement.setInt(5, d.getPrice());
          preparedStatement.setString(6, d.getId());
          preparedStatement.executeUpdate();
          //System.out.println(" DVD Updated.");
          connect.commit();
          return null ;
      }catch(SQLException se){
            try {
                connect.rollback();
                System.out.println("Error occured in updating Form data. " + se.getMessage());
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return se.getMessage();
      }catch(Exception e){
            System.out.println("Error occured while updating Form data. " + e.getMessage());
            return e.getMessage();
      }finally{
              try {
                    if(preparedStatement != null)
                        preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
      }// end try-catch-finally
  }// end method saveForm

  public String deleteDVD(String dvdId){

      PreparedStatement preparedStatement = null ;
      try {
          preparedStatement = connect.prepareStatement("DELETE FROM DVD WHERE DVD_ID = '" + dvdId + "' ");
          preparedStatement.executeUpdate();
          connect.commit();
          return null ;
      }catch(SQLException se){
            try {
                connect.rollback();
                System.out.println("Error occured in deleting Form data. " + se.getMessage());
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return se.getMessage();
      }catch(Exception e){
            System.out.println("Error occured while deleting Form data. " + e.getMessage());
            return e.getMessage();
      }finally{
              try {
                    if(preparedStatement != null)
                        preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
      }// end try-catch-finally
  }// end method saveForm

  public String saveUser(User u){
      PreparedStatement preparedStatement = null ;
      try {
          preparedStatement = connect.prepareStatement("insert into  MyUser (USER_NAME, ADDRESS, Telephone, EMAIL, LOGIN, PASSWD, USER_TYPE) values (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS );
          preparedStatement.setString(1, u.getName());
          preparedStatement.setString(2, u.getAddress());
          preparedStatement.setString(3, u.getTelephone());
          preparedStatement.setString(4, u.getEmail());
          preparedStatement.setString(5, u.getLogin());
          preparedStatement.setString(6, u.getPassword());
          preparedStatement.setInt(7, u.getType());
          preparedStatement.executeUpdate();  
          connect.commit();
          return null ;
      }catch(SQLException se){
            try {
                connect.rollback();
                System.out.println("Error occured in saving User data. " + se.getMessage());
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return se.getMessage();
      }catch(Exception e){
            System.out.println("Error occured while saving attachments. " + e.getMessage());
            return e.getMessage();
      }finally{
              try {
                    if(preparedStatement != null)
                        preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
      }// end try-catch-finally
  }// end method saveForm

  public ArrayList getAllUserIds(){
      ArrayList returnList = new ArrayList();
        ResultSet rs = null ;
        PreparedStatement ps = null ;
        try {
            String sql = " SELECT USER_ID FROM MyUser " ;
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                returnList.add(rs.getString("USER_ID"));
            } // end while
            // end while
            return returnList;
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO, null, e);
            return null;
        }finally{
              try {
                    if(ps != null)
                        ps.close();
                    if(rs != null)
                        rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
      }// end try-catch-finally
    }
  public ArrayList getAllCustomerIds(){
      ArrayList returnList = new ArrayList();
        ResultSet rs = null ;
        PreparedStatement ps = null ;
        try {
            String sql = " SELECT USER_ID FROM MyUser WHERE USER_TYPE = " + AppContext.USER_TYPE_CUSTOMER ;
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                returnList.add(rs.getString("USER_ID"));
            } // end while
            // end while
            return returnList;
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO, null, e);
            return null;
        }finally{
              try {
                    if(ps != null)
                        ps.close();
                    if(rs != null)
                        rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
      }// end try-catch-finally
    }

  public User getUserbyId(int Id){
        ResultSet rs = null ;
        PreparedStatement ps = null ;
        try {
            String sql = "SELECT USER_NAME, ADDRESS, Telephone, EMAIL, LOGIN, PASSWD, USER_TYPE FROM MyUser WHERE USER_ID = " + Id ;
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()) {
                User u = new User();
                u.setID(Id);
                u.setName(rs.getString(1));
                u.setAddress(rs.getString(2));
                u.setTelephone( rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setLogin(rs.getString(5));
                u.setPassword(rs.getString(6));
                u.setType(rs.getInt(7));
                return u;
            } // end while %>
            // end while %>
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO, null, ex);
            return null;
        }catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO, null, e);
            return null;
        }finally{
              try {
                    if(ps != null)
                        ps.close();
                    if(rs != null)
                        rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
      }// end try-catch-finally
  }// end method


  public String updateUser(User u){
      PreparedStatement preparedStatement = null ;
      try {
            preparedStatement = connect.prepareStatement("UPDATE MyUSER SET USER_NAME = ?, ADDRESS = ?, Telephone = ?, EMAIL = ?, LOGIN = ?, PASSWD = ?, USER_TYPE = ? WHERE USER_ID = ? ");

          preparedStatement.setString(1, u.getName());
          preparedStatement.setString(2, u.getAddress());
          preparedStatement.setString(3, u.getTelephone());
          preparedStatement.setString(4, u.getEmail());
          preparedStatement.setString(5, u.getLogin());
          preparedStatement.setString(6, u.getPassword());
          preparedStatement.setInt(7, u.getType());
          preparedStatement.setInt(8, u.getID());

          preparedStatement.executeUpdate();
          connect.commit();
          return null ;
      }catch(SQLException se){
            try {
                connect.rollback();
                System.out.println("Error occured in updating Form data. " + se.getMessage());
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return se.getMessage();
      }catch(Exception e){
            System.out.println("Error occured while updating Form data. " + e.getMessage());
            return e.getMessage();
      }finally{
              try {
                    if(preparedStatement != null)
                        preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
      }// end try-catch-finally
  }// end method saveForm

  public String deleteUser(int userId){

      PreparedStatement preparedStatement = null ;
      try {
          preparedStatement = connect.prepareStatement("DELETE FROM MyUser WHERE USER_ID = " + userId);
          preparedStatement.executeUpdate();
          connect.commit();
          return null ;
      }catch(SQLException se){
            try {
                connect.rollback();
                System.out.println("Error occured in deleting Form data. " + se.getMessage());
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return se.getMessage();
      }catch(Exception e){
            System.out.println("Error occured while deleting Form data. " + e.getMessage());
            return e.getMessage();
      }finally{
              try {
                    if(preparedStatement != null)
                        preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
      }// end try-catch-finally
  }// end method saveForm

  public String saveDVDIssue(int userId, String dvdId){

      PreparedStatement preparedStatement = null ;
      try {
          preparedStatement = connect.prepareStatement("insert into  RENTAL_DVD (CUSTOMER_ID, DVD_ID, ISSUE_DATE) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS );
          preparedStatement.setInt(1, userId);
          preparedStatement.setString(2, dvdId);
          preparedStatement.setDate(3, getSqlDate(new Date()));
          preparedStatement.executeUpdate();
          //  System.out.println(" Rent Entry ADDED.");
          preparedStatement = connect.prepareStatement("UPDATE DVD SET DVD_AVAILABLE = " + AppContext.DVD_STATUS_NOT_AVAILABLE + " WHERE DVD_ID = '" + dvdId + "'" );
          preparedStatement.executeUpdate();
          connect.commit();
          return null ;
      }catch(SQLException se){
            try {
                connect.rollback();
                System.out.println("Error occured in saving User data. " + se.getMessage());
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return se.getMessage();
      }catch(Exception e){
            System.out.println("Error occured while saving attachments. " + e.getMessage());
            return e.getMessage();
      }finally{
              try {
                    if(preparedStatement != null)
                        preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
      }// end try-catch-finally
  }// end method saveForm

  public ArrayList getAllIssuedDVDs(){
        ArrayList returnList = new ArrayList();
        ResultSet rs = null ;
        PreparedStatement ps = null ;
        try {
            String sql = " SELECT USER_ID, USER_NAME, d.DVD_ID, DVD_NAME, DVD_PRICE, ISSUE_DATE, RENTAL_ID FROM DVD d, MyUser u, RENTAL_DVD r WHERE d.DVD_ID = r.DVD_ID AND u.USER_ID = r.CUSTOMER_ID AND RETURN_DATE IS NULL " ;
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap map = new HashMap();
                map.put(CUSTOMER_ID, rs.getString(1));
                map.put(CUSTOMER_NAME, rs.getString(2));
                map.put(DVD_ID, rs.getString(3));
                map.put(DVD_NAME, rs.getString(4));
                map.put(DVD_RENT, rs.getInt(5));
                map.put(ISSUE_DATE, rs.getDate(6));
                map.put(RENTAL_ID, rs.getInt(7));

                returnList.add(map);
            } // end while %>
            // end while %>
            return returnList;
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO, null, ex);
            return null;
        }catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO, null, e);
            return null;
        }finally{
              try {
                    if(ps != null)
                        ps.close();
                    if(rs != null)
                        rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
      }// end try-catch-finally
  }// end method

  public String returnDVD(int rentalId, String dvdId){
      PreparedStatement preparedStatement = null ;
      try {
          //preparedStatement = connect.prepareStatement("insert into  RENTAL_DVD (CUSTOMER_ID, DVD_ID, ISSUE_DATE) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS );
          preparedStatement = connect.prepareStatement("UPDATE RENTAL_DVD SET RETURN_DATE = ? WHERE RENTAL_ID = ? " );
          preparedStatement.setDate(1, getSqlDate(new Date()));
          preparedStatement.setInt(2, rentalId);
          preparedStatement.executeUpdate();
          //  System.out.println(" Rent Entry ADDED.");
          preparedStatement = connect.prepareStatement("UPDATE DVD SET DVD_AVAILABLE = " + AppContext.DVD_STATUS_AVAILABLE + " WHERE DVD_ID = '" + dvdId + "'" );
          preparedStatement.executeUpdate();
          connect.commit();
          return null ;
      }catch(SQLException se){
            try {
                connect.rollback();
                System.out.println("Error occured in saving User data. " + se.getMessage());
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return se.getMessage();
      }catch(Exception e){
            System.out.println("Error occured while saving attachments. " + e.getMessage());
            return e.getMessage();
      }finally{
              try {
                    if(preparedStatement != null)
                        preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
      }// end try-catch-finally
  }// end method saveForm

  private java.sql.Date getSqlDate(java.util.Date utilDate){
       return new java.sql.Date(utilDate.getTime());
  }
    // you need to close all three to make sure
  public void closeDBConnection() {
    try{
      if(statement != null)
            statement.close();
        if(connect != null)
            connect.close();
    } catch(Exception e){
        System.out.println(" Exception occured in closing DB Connection");
    }
    System.out.println(" DB Connection is closed.");
  }// end method close

}

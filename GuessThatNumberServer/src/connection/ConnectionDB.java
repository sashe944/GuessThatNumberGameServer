package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import gameObjects.Points;
import gameObjects.User;

public class ConnectionDB {
	Connection conn = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
     public boolean connection(){
		
		  try{
			  
		      Class.forName("org.sqlite.JDBC");
		      conn=DriverManager.getConnection("jdbc:sqlite:/C:/Users/Home/Desktop/GuessGame.db");
		      return true;
		    }
		      catch(Exception e)
		      {
			    e.printStackTrace();
			    return false;
		      }finally{
			    	  try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		      }
	  } 
     
    public User getUser(String username,String pasword) {
    	 User user = new User();
		return user; 
    }
     public User RegisterUser(String username,String password,String email,String gender,String avatar){
    	 User registerUser = new User();
       return registerUser;
     }
     public Points getPoints(String user_id){
    	 Points points = new Points();
		return points;
     }
}
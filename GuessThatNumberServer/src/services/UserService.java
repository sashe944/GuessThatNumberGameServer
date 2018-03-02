package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import gameObjects.User;

public class UserService {

	public User find(String username,String password){
		User user = new User();
    	  Connection conn = null;
    	    Statement stmt = null;
    	    try{
    	    	Class.forName("org.sqlite.JDBC");
    	    	conn=DriverManager.getConnection("jdbc:sqlite:/C:/Users/Home/Desktop/Guess.db");
    	        conn.setAutoCommit(false);
    	        stmt = conn.createStatement();
    	        ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE username = \"" + username + "\" AND password =\"" +password + "\"");

    	        while (rs.next()){
    	        	user.id = rs.getInt("_id");
    	        	user.username = rs.getString("username");
    	        	user.password = rs.getString("password");
    	        	user.email = rs.getString("email");
    	        	user.gender = rs.getString("gender");
    	        	user.avatar = rs.getString("avatar");
    	        }  
    	    }
    	    catch(Exception e){
    	    	e.printStackTrace();
    	    } finally {
    			try {
    				stmt.close();
    				conn.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	    System.out.println(user.toString());
		return user;
	}
	       	
}

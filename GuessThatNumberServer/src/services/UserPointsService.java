package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import gameObjects.Points;

public class UserPointsService {
	
	public Points find(String user_id){
		Points points = new Points();
    	  Connection conn = null;
    	    Statement stmt = null;
    	    try{
    	    	Class.forName("org.sqlite.JDBC");
    	    	conn=DriverManager.getConnection("jdbc:sqlite:/C:/Users/Home/Desktop/Guess.db");
    	        conn.setAutoCommit(false);
    	        stmt = conn.createStatement();
    	    
    	        ResultSet rs =stmt.executeQuery("SELECT * FROM points_table WHERE user_id="+user_id);

    	        while (rs.next()){
    	        	points.points_id = rs.getLong("points_id");
    	        	points.points = rs.getInt("points");
    	        	points.user_id = rs.getLong("user_id");
    	        }  
    	    }
    	    catch(Exception e){
    	    	e.printStackTrace();
    	    }  finally {
    			try {
    				stmt.close();
    				conn.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	    System.out.println(points.toString());
		return points;
	}

}

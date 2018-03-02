package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import gameObjects.Points;



public class RegisterPointsService {
	
	public Points register(int playerPoints, String user_id) {
		Points point = new Points();
    	  Connection conn = null;
    	    	PreparedStatement statement = null;
    	    	try {
    	    		
    	    		final String sql = "INSERT INTO points_table (points,user_id) VALUES (?,?)";
    	    		
    	    		conn = DriverManager.getConnection("jdbc:sqlite:/C:/Users/Home/Desktop/Guess.db");
    	    		
    	    			statement = conn.prepareStatement(sql);
    	    			
    	    			statement.setInt(1,playerPoints);
    	    			statement.setString(2,user_id);
    	    			
    	    			statement.executeUpdate();
    	    			
    	    			UserPointsService userPointsService = new UserPointsService();
    	    			point =  userPointsService.find(user_id);
    	    			
    	    			System.out.println("register user: " + playerPoints);
    	    	}
    	    catch(Exception e){
    	    	e.printStackTrace();
    	    }finally {
    			try {
    				statement.close();
    				conn.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	    System.out.println(point.toString());
		return point;
     }
}

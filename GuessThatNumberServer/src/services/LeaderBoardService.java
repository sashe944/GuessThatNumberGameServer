package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import gameObjects.LeaderBoard;



public class LeaderBoardService {
	
	UserPointsService userPointsService = new UserPointsService();
	
	
	public List<LeaderBoard> getllPointsWithUsernamesOrderedByPoints() {

		List<LeaderBoard> leaderBoards = new ArrayList<>();
		
		  Connection conn = null;
    	    Statement stmt = null;
    	    try{
    	    	Class.forName("org.sqlite.JDBC");
    	    	conn=DriverManager.getConnection("jdbc:sqlite:/C:/Users/Home/Desktop/Guess.db");
    	        conn.setAutoCommit(false);
    	        stmt = conn.createStatement();
    	        String sql="SELECT username,points from user,points_table WHERE user._id = points_table.user_id order by points_table.points";
    	        ResultSet rs = stmt.executeQuery(sql);
    	        while (rs.next()) {
    	        	LeaderBoard lBoard = new LeaderBoard();
    	        	lBoard.points = rs.getInt("points");
    	        	lBoard.username = rs.getString("username");
    	        	leaderBoards.add(lBoard);
    	        }  
    	    }
    	    catch(Exception e){
    	    	e.printStackTrace();
    	    }finally {
    			try {
    				stmt.close();
    				conn.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	    return leaderBoards;    	    
		
	}
}

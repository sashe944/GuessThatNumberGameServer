package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import gameObjects.User;

public class RegisterUserService {
	

	public User register(String username, String password, String email, String gender, String avatar) {
		Connection connection = null;
		User user = null;
		PreparedStatement statement = null;
	try {
		
		final String sql = "INSERT INTO user (username,password,email,gender,avatar) VALUES (?,?,?,?,?)";
		
		connection = DriverManager.getConnection("jdbc:sqlite:/C:/Users/Home/Desktop/Guess.db");
		
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, email);
			statement.setString(4, gender);
			statement.setString(5, avatar);
			
			statement.executeUpdate();
			
			UserService userService = new UserService();
			user =  userService.find(username, password);
			
			System.out.println("register user: " + user);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}
}

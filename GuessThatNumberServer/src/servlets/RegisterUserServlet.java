package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gameObjects.User;
import services.RegisterUserService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static GsonBuilder gson_builder = new GsonBuilder().serializeNulls().setDateFormat("MM/dd/yyyy");
	   
    private RegisterUserService registerUserService;
    
    public RegisterUserServlet() {
        super();
        // TODO Auto-generated constructor stub
        registerUserService = new RegisterUserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		
			 String username = request.getParameter("username");
			 String password = request.getParameter("password");
			 String email = request.getParameter("email");
			 String gender = request.getParameter("gender");
			 String avatar = request.getParameter("avatar");
			
		      user = registerUserService.register(username,password,email,gender,avatar);
		  
		    if (user != null) {
			  response.setContentType("application/json;charset=UTF-8");
		      Gson gson = gson_builder.create();
		      response.getWriter().write(gson.toJson(user));
		  }
		  else {
		      request.setAttribute("error", "Registration was unsuccessful!");
		  }	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

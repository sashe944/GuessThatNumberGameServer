package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gameObjects.Points;
import services.RegisterPointsService;

/**
 * Servlet implementation class RegisterUserPointsServlet
 */
@WebServlet("/RegisterUserPointsServlet")
public class RegisterUserPointsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static GsonBuilder gson_builder = new GsonBuilder().serializeNulls().setDateFormat("MM/dd/yyyy");
	
	RegisterPointsService registerPointsService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserPointsServlet() {
        super();
        // TODO Auto-generated constructor stub
        registerPointsService = new RegisterPointsService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Points points = new Points();
		
		 int user_points = Integer.valueOf(request.getParameter("points"));
		 String user_id = request.getParameter("user_id");
		
	      points = registerPointsService.register(user_points,user_id);
	  
	    if (points != null) {
		  response.setContentType("application/json;charset=UTF-8");
	      Gson gson = gson_builder.create();
	      response.getWriter().write(gson.toJson(points));
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

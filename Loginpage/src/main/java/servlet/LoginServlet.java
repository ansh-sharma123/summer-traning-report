package Shiva;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demomysql","root","3176");
			String name = request.getParameter("txtName");
			String password = request.getParameter("txtPwd");
			
			PreparedStatement pst = con.prepareStatement("SELECT * FROM login WHERE username=? AND password=?");
			pst.setString(1, name);
			pst.setString(2, password);
			
			ResultSet rs=pst.executeQuery();
			
			if(rs.next())
			{
				response.sendRedirect("success.jsp");
			}else {
				response.sendRedirect("error.jsp");
			}

		}
		catch(Exception e) {
			System.out.println(e);
			
		}
		

}
}

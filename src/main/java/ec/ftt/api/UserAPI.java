package ec.ftt.api;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ec.ftt.dao.UserDAO;
import ec.ftt.model.User;


/**
 * Servlet implementation class UserApi
 * 
 * CRUD - 
 * 
 */
@WebServlet("/user")
public class UserAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setStatus(418); //200 - OK - Padr�o (Default)

		String userCPF = request.getParameter("user-cpf");
		
	    if(userCPF != null) {
	    	UserDAO userDAO = new UserDAO();
	    	
	        User user = userDAO.Consultar(userCPF).get(0);
	     	Gson gson = new Gson();
	    	response.getWriter().append(gson.toJson(user));
	    	
	    } else {
	    	
	    	UserDAO userDAO = new UserDAO();
	    	
	    	List<User> users = userDAO.Consultar();
	        
	    	Gson gson = new Gson();

	    	response.getWriter().append(gson.toJson(users));
	    	/*
	    	 for (User u : users)
	    	 
	    		response.getWriter().append(u.toString());
	    	*/
	    } //if
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String data = request.getParameter("user-dataNascimento");
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		User user = new User(
				request.getParameter("user-cpf"),
				request.getParameter("user-login"),
				request.getParameter("user-password"),
				localDate,
				request.getParameter("user-telefone"),
				request.getParameter("user-email")
				);
		
		UserDAO userDAO = new UserDAO();
		
		userDAO.Inserir(user);
		
		System.out.println(user);
		
		response.getWriter().append(user.toString());
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json"); //mimeType - https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String data = request.getParameter("user-dataNascimento");
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		User user = new User(
				request.getParameter("user-cpf"),
				request.getParameter("user-login"),
				request.getParameter("user-password"),
				localDate,
				request.getParameter("user-telefone"),
				request.getParameter("user-email")
				);
		
		UserDAO userDAO = new UserDAO();
		
		userDAO.Atualizar(user);
		
		System.out.println(user);
		
		response.getWriter().append(user.toString());
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// https://www.tutorialspoint.com/servlets/servlets-http-status-codes.htm
		
		// TODO Verificar se está enviando o parametro
		// TODO Verificar se o parametro é null
		// TODO Se o ID já foi apagado
		// TODO Verificar se o ID não existe...
		
		if (request.getParameter("user-cpf") == null)
			 response.sendError(407, "Informe o ID do usuário a ser retornado!!!" );
		else {
		String userCPF = request.getParameter("user-cpf");
		
		
		
		UserDAO userDAO = new UserDAO();
		
		userDAO.Remover(userCPF);
		
		response.getWriter().append(request.getParameter("user-cpf") + " User removido");
		}
	}

}

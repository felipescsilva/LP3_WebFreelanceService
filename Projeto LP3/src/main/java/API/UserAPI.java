package API;

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

import DAO.UserDAO;
import model.User;



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
		
		response.setStatus(418); //200 - OK - Padrão (Default)

		String userCPF = request.getParameter("user-cpf");
		
	    if(userCPF != null) {
	    	UserDAO userDAO = new UserDAO();
	    	List<User> listaUser = userDAO.Consultar(userCPF);
	    	if (!listaUser.isEmpty()) {
	    		User user = listaUser.get(0);
	     		Gson gson = new Gson();
	     		response.getWriter().append(gson.toJson(user));
	    	} else {
	    		response.getWriter().append("User não encontrado");
	    		response.setStatus(404);
	    	}
	    	
	    } else {
	    	
	    	UserDAO userDAO = new UserDAO();
	    	List<User> users = userDAO.Consultar();
	    	Gson gson = new Gson();
	    	if (!users.isEmpty())
	    		response.getWriter().append(gson.toJson(users));
	    	else {
	    		response.getWriter().append("Nao existem usuarios cadastrados!");
	    		response.setStatus(404);
	    	}
	    	
	    	
	    }
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
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
			
			List<User> listaUsuario = userDAO.Consultar(user.getCPF());
			
			if (listaUsuario.isEmpty()) {
				if(userDAO.Inserir(user))
					response.getWriter().append(request.getParameter("user-login") + " adicionado!");
				else {
					response.getWriter().append("Ocorreu um erro ao inserir o user!");
					response.setStatus(404);
				}
					
			} else {
				response.getWriter().append("User ja existente!");
				response.setStatus(404);
			}
		} catch (Exception e) {
			response.getWriter().append("Preencha os campos corretamente!");
			response.setStatus(404);
		}
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json"); //mimeType - https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types
		
		try {
		
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
			List<User> listaUser = userDAO.Consultar(user.getCPF());
			if (!listaUser.isEmpty()) {
				if (userDAO.Atualizar(user)) {
					response.getWriter().append(request.getParameter("user-login") + " atualizado!");
				} else {
					response.getWriter().append("Ocorreu um erro ao atualizar o user!");
					response.setStatus(404);
				}
			} else {
				response.getWriter().append("Não existe um cadastro para o CPF informado!");
				response.setStatus(404);
			}
		} catch (Exception e) {
			response.getWriter().append("Preencha os campos corretamente!");
			response.setStatus(404);
		}
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// https://www.tutorialspoint.com/servlets/servlets-http-status-codes.htm
		
		if (request.getParameter("user-cpf") == null) {
			 response.getWriter().append("Informe o CPF a ser retornado!" );
			 response.setStatus(404);
		}
		else {
			String userCPF = request.getParameter("user-cpf");
		
			
		
			UserDAO userDAO = new UserDAO();
			
			List<User> listaUser = userDAO.Consultar(userCPF);
			if (listaUser.isEmpty()) {
				response.getWriter().append("CPF não encontrado, por favor digite um CPF valido!");
				 response.setStatus(404);
			}
			else {
				if (userDAO.Remover(userCPF))
					response.getWriter().append("User de CPF " + request.getParameter("user-cpf") + " removido");
				else {
					response.getWriter().append("Ocorreu um erro ao remover o user de CPF " + request.getParameter("user-cpf"));
					response.setStatus(404);
				}
			}
		}
	}

}
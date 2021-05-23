
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

import ec.ftt.dao.AnuncioDAO;
import ec.ftt.dao.UserDAO;
import ec.ftt.model.Anuncio;
import ec.ftt.model.User;

/**
 * Servlet implementation class UserApi
 * 
 * CRUD - 
 * 
 */
@WebServlet("/anuncio")
public class AnuncioAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnuncioAPI() {
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

		String anuncioId = request.getParameter("anuncio-idAnuncio");
		
	    if(anuncioId != null) {
	    	int id = Integer.parseInt(anuncioId);
	    	AnuncioDAO anuncioDAO = new AnuncioDAO();
	    	
	        Anuncio anuncio = anuncioDAO.Consultar(id).get(0);
	     	Gson gson = new Gson();
	    	response.getWriter().append(gson.toJson(anuncio));
	    	
	    } else {
	    	
	    	AnuncioDAO anuncioDAO = new AnuncioDAO();
	    	
	    	List<Anuncio> anuncios = anuncioDAO.Consultar();
	        
	    	Gson gson = new Gson();

	    	response.getWriter().append(gson.toJson(anuncios));
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
		String data = request.getParameter("anuncio-dataAnuncio");
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		Anuncio anuncio = new Anuncio(
				Integer.parseInt(request.getParameter("anuncio-idAnuncio")),
				request.getParameter("anuncio-cpf"),
				request.getParameter("anuncio-titulo"),
				request.getParameter("anuncio-descricao"),
				Double.parseDouble(request.getParameter("anuncio-valorHora")),
				localDate
				);
		
		AnuncioDAO anuncioDAO = new AnuncioDAO();
		
		anuncioDAO.Inserir(anuncio);
		
		System.out.println(anuncio);
		
		response.getWriter().append(anuncio.toString());
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json"); //mimeType - https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String data = request.getParameter("anuncio-dataAnuncio");
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		Anuncio anuncio = new Anuncio(
				Integer.parseInt(request.getParameter("anuncio-idAnuncio")),
				request.getParameter("anuncio-cpf"),
				request.getParameter("anuncio-titulo"),
				request.getParameter("anuncio-descricao"),
				Double.parseDouble(request.getParameter("anuncio-valorHora")),
				localDate
				);
		
		AnuncioDAO anuncioDAO = new AnuncioDAO();
		
		anuncioDAO.Atualizar(anuncio);
		
		System.out.println(anuncio);
		
		response.getWriter().append(anuncio.toString());
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
		
		if (request.getParameter("anuncio-idAnuncio") == null)
			 response.sendError(407, "Informe o ID do anuncio a ser retornado!!!" );
		else {
		int anuncioId = Integer.parseInt(request.getParameter("anuncio-idAnuncio"));
		
		
		
		AnuncioDAO anuncioDAO = new AnuncioDAO();
		
		anuncioDAO.Remover(anuncioId);
		
		response.getWriter().append(request.getParameter("anuncio-idAnuncio") + " anuncio removido");
		}
	}

}

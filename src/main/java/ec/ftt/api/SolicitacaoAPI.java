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
import ec.ftt.dao.SolicitacaoDAO;
import ec.ftt.dao.UserDAO;
import ec.ftt.model.Anuncio;
import ec.ftt.model.Solicitacao;
import ec.ftt.model.User;


/**
 * Servlet implementation class UserApi
 * 
 * CRUD - 
 * 
 */
@WebServlet("/solicitacao")
public class SolicitacaoAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolicitacaoAPI() {
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

		String solicitacaoId = request.getParameter("solicitacao-idSolicitacao");
		
	    if(solicitacaoId != null) {
	    	int id = Integer.parseInt(solicitacaoId);
	    	SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
	    	
	        Solicitacao solicitacao = solicitacaoDAO.Consultar(id).get(0);
	     	Gson gson = new Gson();
	    	response.getWriter().append(gson.toJson(solicitacao));
	    	
	    } else {
	    	
	    	SolicitacaoDAO userDAO = new SolicitacaoDAO();
	    	
	    	List<Solicitacao> solicitacoes = userDAO.Consultar();
	        
	    	Gson gson = new Gson();

	    	response.getWriter().append(gson.toJson(solicitacoes));
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
		String data = request.getParameter("solicitacao-dataSolicitacao");
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		Solicitacao u = new Solicitacao(
				Integer.parseInt(request.getParameter("solicitacao-idSolicitacao")),
				Integer.parseInt(request.getParameter("solicitacao-idAnuncio")),
				Integer.parseInt(request.getParameter("solicitacao-qtdHoras")),
				localDate,
				request.getParameter("solicitacao-status"),
				Double.parseDouble(request.getParameter("solicitacao-valorTotal"))
				);
		
		SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
		
		solicitacaoDAO.Inserir(u);
		
		System.out.println(u);
		
		response.getWriter().append(u.toString());
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json"); //mimeType - https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String data = request.getParameter("solicitacao-dataSolicitacao");
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		Solicitacao solicitacao = new Solicitacao(
				Integer.parseInt(request.getParameter("solicitacao-idSolicitacao")),
				Integer.parseInt(request.getParameter("solicitacao-idAnuncio")),
				Integer.parseInt(request.getParameter("solicitacao-qtdHoras")),
				localDate,
				request.getParameter("solicitacao-status"),
				Double.parseDouble(request.getParameter("solicitacao-valorTotal"))
				);
		
		SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
		
		solicitacaoDAO.Atualizar(solicitacao);
		
		System.out.println(solicitacao);
		
		response.getWriter().append(solicitacao.toString());
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
		
		if (request.getParameter("solicitacao-idSolicitacao") == null)
			 response.sendError(407, "Informe o ID da solicitacao a ser retornada!!!" );
		else {
		int solicitacaoId = Integer.parseInt(request.getParameter("solicitacao-idSolicitacao"));
		
		
		
		SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
		
		solicitacaoDAO.Remover(solicitacaoId);
		
		response.getWriter().append(request.getParameter("solicitacao-idSolicitacao") + " Solicitacao removida");
		}
	}

}

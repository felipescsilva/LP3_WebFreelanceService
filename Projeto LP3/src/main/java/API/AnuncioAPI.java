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

import DAO.AnuncioDAO;
import model.Anuncio;

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
		

		String anuncioId = request.getParameter("anuncio-idAnuncio");
		String CPF = request.getParameter("anuncio-cpf");
		
	    if(anuncioId != null) {
	    	
	    	try {
		    	int id = Integer.parseInt(anuncioId);
		    	AnuncioDAO anuncioDAO = new AnuncioDAO();
		    	
		    	List<Anuncio> anuncioLista = anuncioDAO.Consultar(id);
		    	if (!anuncioLista.isEmpty()) {
		    		Anuncio anuncio = anuncioDAO.Consultar(id).get(0);
		     		Gson gson = new Gson();
		     		response.getWriter().append(gson.toJson(anuncio));
		    	} else {
		    		response.getWriter().append("Anuncio não encontrado.");
					response.setStatus(404);
		    	}
		    	
	    	} catch (Exception e) {
	    		response.getWriter().append("Insira um valor válido.");
				response.setStatus(404);
	    	}
	    	
	    } else if (CPF != null) {
	    	
	    	try {
		    	AnuncioDAO anuncioDAO = new AnuncioDAO();
		    	
		    	List<Anuncio> anuncios = anuncioDAO.Consultar("CPF", CPF);
		    	Gson gson = new Gson();
		    	if (!anuncios.isEmpty())
		    		response.getWriter().append(gson.toJson(anuncios));	
		    	else {
		    		response.getWriter().append("Nao existem anuncios cadastrados");
					response.setStatus(404);
		    	}
		    	
	    	} catch (Exception e) {
	    		response.getWriter().append("Insira um valor válido.");
				response.setStatus(404);
	    	}
	    } else {
	    	
	    	AnuncioDAO anuncioDAO = new AnuncioDAO();
	    	List<Anuncio> anuncios = anuncioDAO.Consultar();
	    	Gson gson = new Gson();
	    	if (!anuncios.isEmpty())
	    		response.getWriter().append(gson.toJson(anuncios));	
	    	else {
	    		response.getWriter().append("Nao existem anuncios cadastrados");
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
			String data = request.getParameter("anuncio-dataAnuncio");
			LocalDate localDate = LocalDate.parse(data, formatter);
			
			Anuncio anuncio = new Anuncio();
			anuncio.setCPF(request.getParameter("anuncio-cpf"));
			anuncio.setTitulo(request.getParameter("anuncio-titulo"));
			anuncio.setDescricao(request.getParameter("anuncio-descricao"));
			anuncio.setValorHora(Double.parseDouble(request.getParameter("anuncio-valorHora")));
			anuncio.setDataAnuncio(localDate);
			
			AnuncioDAO anuncioDAO = new AnuncioDAO();
			
			if(anuncioDAO.Inserir(anuncio)) {
				response.getWriter().append("Anuncio adicionado!");
			} else {
				response.getWriter().append("Ocorreu um erro ao inserir o anuncio!");
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
			String data = request.getParameter("anuncio-dataAnuncio");
			LocalDate localDate = LocalDate.parse(data, formatter);
			
			Anuncio anuncio = new Anuncio();
			anuncio.setIdAnuncio(Integer.parseInt(request.getParameter("anuncio-idAnuncio")));
			anuncio.setCPF(request.getParameter("anuncio-cpf"));
			anuncio.setTitulo(request.getParameter("anuncio-titulo"));
			anuncio.setDescricao(request.getParameter("anuncio-descricao"));
			anuncio.setValorHora(Double.parseDouble(request.getParameter("anuncio-valorHora")));
			anuncio.setDataAnuncio(localDate);
			
			AnuncioDAO anuncioDAO = new AnuncioDAO();
			List<Anuncio> listaAnuncio = anuncioDAO.Consultar(anuncio.getIdAnuncio());
			if (!listaAnuncio.isEmpty()) {
				if(anuncioDAO.Atualizar(anuncio))
					response.getWriter().append("Anuncio atualizado!");
				else {
					response.getWriter().append("Ocorreu um erro ao atualizar o anuncio!");
					response.setStatus(404);
				}
			} else {
				response.getWriter().append("Não existe um cadastro para o ID informado!");
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
		
		if (request.getParameter("anuncio-idAnuncio") == null) {
			 response.getWriter().append("Informe o ID do Anuncio a ser retornado!" );
			 response.setStatus(404);
		}
		else {
			
			String anuncioIdString = request.getParameter("anuncio-idAnuncio");
			
			try {
				int anuncioId = Integer.parseInt(anuncioIdString);
							
				AnuncioDAO anuncioDAO = new AnuncioDAO();
				
				List<Anuncio> listaanuncio = anuncioDAO.Consultar(anuncioId);
				if (listaanuncio.isEmpty()) {
					response.getWriter().append("ID do anuncio não encontrado, por favor digite um ID valido!");
					 response.setStatus(404);
				}
				else {
					if(anuncioDAO.Remover(anuncioId))
						response.getWriter().append("anuncio de ID " + request.getParameter("anuncio-idAnuncio") + " removido");
					else {
						response.getWriter().append("Ocorreu um erro ao remover o anuncio de ID " + request.getParameter("anuncio-idAnuncio"));
						response.setStatus(404);
					}
				}
			} catch (Exception e) {
				response.getWriter().append("Insira um valor válido");
				response.setStatus(404);
			}
		}
	}

}

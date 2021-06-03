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

import DAO.SolicitacaoDAO;
import model.Solicitacao;


/**
 * Servlet implementation class SolicitacaoAPI
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

		String solicitacaoId = request.getParameter("solicitacao-idSolicitacao");
		String CPF_User = request.getParameter("solicitacao-CPF_User");
		String CPF_Anunciante = request.getParameter("solicitacao-CPF_Anunciante");
		
	    if(solicitacaoId != null) {
	    	try {
	    		int id = Integer.parseInt(solicitacaoId);
	    			SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
	    	
	    			List<Solicitacao> listaSolicitacao = solicitacaoDAO.Consultar(id);
	    			if (!listaSolicitacao.isEmpty()) {
	    				Solicitacao solicitacao = listaSolicitacao.get(0);
	    				Gson gson = new Gson();
	    				response.getWriter().append(gson.toJson(solicitacao));
	    			} else {
	    				response.getWriter().append("Solicitação não encontrada");
	    				response.setStatus(404);
	    			}
	    	} catch (Exception e) {
	    		response.getWriter().append("Insira um valor válido.");
				response.setStatus(404);
	    	}
	    	
	    } else if (CPF_User != null) {
	    	
	    	try {
	    			SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
	    	
	    			List<Solicitacao> solicitacoes = solicitacaoDAO.Consultar("CPF_User", CPF_User);
	    			Gson gson = new Gson();
	    	    	if (!solicitacoes.isEmpty())
	    	    		response.getWriter().append(gson.toJson(solicitacoes));
	    	    	else {
	    				response.getWriter().append("Nao existem solicitacoes cadastradas");
	    				response.setStatus(404);
	    			}
	    	} catch (Exception e) {
	    		response.getWriter().append("Insira um valor válido.");
				response.setStatus(404);
	    	}
	    	
	    } else if (CPF_Anunciante != null) {
	    	try {
	    			SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
	    	
	    			List<Solicitacao> solicitacoes = solicitacaoDAO.Consultar("CPF_Anunciante", CPF_Anunciante);
	    			Gson gson = new Gson();
	    	    	if (!solicitacoes.isEmpty())
	    	    		response.getWriter().append(gson.toJson(solicitacoes));
	    	    	else {
	    				response.getWriter().append("Nao existem solicitacoes cadastradas");
	    				response.setStatus(404);
	    			}
	    	} catch (Exception e) {
	    		response.getWriter().append("Insira um valor válido.");
				response.setStatus(404);
	    	}
	    	
	    }
	    
	    else {
	    	
	    	SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
	    	List<Solicitacao> solicitacoes = solicitacaoDAO.Consultar();
	    	Gson gson = new Gson();
	    	if (!solicitacoes.isEmpty())
	    		response.getWriter().append(gson.toJson(solicitacoes));
	    	else {
				response.getWriter().append("Nao existem solicitacoes cadastradas");
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
			String data = request.getParameter("solicitacao-dataSolicitacao");
			LocalDate localDate = LocalDate.parse(data, formatter);
		
		
			Solicitacao solicitacao = new Solicitacao();
			
			solicitacao.setIdAnuncio(Integer.parseInt(request.getParameter("solicitacao-idAnuncio")));
			solicitacao.setQtdHoras(Integer.parseInt(request.getParameter("solicitacao-qtdHoras")));
			solicitacao.setDataSolicitacao(localDate);
			solicitacao.setStatus(request.getParameter("solicitacao-status"));
			solicitacao.setValorTotal(Double.parseDouble(request.getParameter("solicitacao-valorTotal")));
			solicitacao.setCPF_User(request.getParameter("solicitacao-CPF_User"));
			solicitacao.setCPF_Anunciante(request.getParameter("solicitacao-CPF_Anunciante"));
			
			SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
			
			if (solicitacaoDAO.Inserir(solicitacao)) {
				response.getWriter().append("Solicitação adicionada!");
			} else {
				response.getWriter().append("Ocorreu um erro ao inserir a solicitação!");
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
			String data = request.getParameter("solicitacao-dataSolicitacao");
			LocalDate localDate = LocalDate.parse(data, formatter);
			
			Solicitacao solicitacao = new Solicitacao();
			solicitacao.setIdSolicitacao(Integer.parseInt(request.getParameter("solicitacao-idSolicitacao")));
			solicitacao.setIdAnuncio(Integer.parseInt(request.getParameter("solicitacao-idAnuncio")));
			solicitacao.setQtdHoras(Integer.parseInt(request.getParameter("solicitacao-qtdHoras")));
			solicitacao.setDataSolicitacao(localDate);
			solicitacao.setStatus(request.getParameter("solicitacao-status"));
			solicitacao.setValorTotal(Double.parseDouble(request.getParameter("solicitacao-valorTotal")));
			solicitacao.setCPF_User(request.getParameter("solicitacao-CPF_User"));
			solicitacao.setCPF_Anunciante(request.getParameter("solicitacao-CPF_Anunciante"));
			
			SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
			List<Solicitacao> listaSolicitacao = solicitacaoDAO.Consultar(solicitacao.getIdSolicitacao());
			
			if (!listaSolicitacao.isEmpty()) {
				if(solicitacaoDAO.Atualizar(solicitacao)) {
					response.getWriter().append("Solicitação atualizada!");
				} else {
					response.getWriter().append("Ocorreu um erro ao atualizar a solicitação!");
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
		
		if (request.getParameter("solicitacao-idSolicitacao") == null) {
			 response.getWriter().append("Informe o ID da Solicitação a ser retornado!" );
			 response.setStatus(404);
		}
		else {
			
			String solicitacaoIdString = request.getParameter("solicitacao-idSolicitacao");
			
			
			try {
				int solicitacaoId = Integer.parseInt(solicitacaoIdString);
				
				
			
				SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
				
				List<Solicitacao> listaSolicitacao = solicitacaoDAO.Consultar(solicitacaoId);
				if (listaSolicitacao.isEmpty()) {
					response.getWriter().append("ID da Solicitação não encontrado, por favor digite um ID valido!");
					 response.setStatus(404);
				}
				else {
					if(solicitacaoDAO.Remover(solicitacaoId))
						response.getWriter().append("Solicitacao de ID " + request.getParameter("solicitacao-idSolicitacao") + " removido");
					else {
						response.getWriter().append("Ocorreu um erro ao remover a solicitacao de ID " + request.getParameter("solicitacao-idSolicitacao"));
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

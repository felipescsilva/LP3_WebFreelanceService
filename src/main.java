import java.time.LocalDate;
import java.util.List;

import DAO.AnuncioDAO;
import DAO.SolicitacaoDAO;
import DAO.UserDAO;
import model.Anuncio;
import model.Solicitacao;
import model.User;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// teste de insert
		/*
		User user = new User();
		user.setCPF("12345678912");
		user.setDataNascimento(LocalDate.now());
		user.setEmail("lucascaffer@gmail.com");
		user.setLogin("C4ffer");
		user.setPassword("123456");
		user.setTelefone("11 992930434");
		
		UserDAO userDAO = new UserDAO();
		userDAO.Inserir(user);
		
		Anuncio anuncio = new Anuncio();
		anuncio.setCPF("12345678912");
		anuncio.setDataAnuncio(LocalDate.now());
		anuncio.setDescricao("Desc");
		anuncio.setIdAnuncio(1);
		anuncio.setTitulo("Titulo");
		anuncio.setValorHora(1);
		
		AnuncioDAO anuncioDAO = new AnuncioDAO();
		anuncioDAO.Inserir(anuncio);
		
		Solicitacao solicitacao = new Solicitacao();
		solicitacao.setDataSolicitacao(LocalDate.now());
		solicitacao.setIdAnuncio(2);
		solicitacao.setIdSolicitacao(1);
		solicitacao.setQtdHoras(1);
		solicitacao.setStatus("Ativo");
		solicitacao.setValorTotal(100.50);
		
		
		SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
		solicitacaoDAO.Inserir(solicitacao);
		*/
		//Teste de Update
		/*
		User user = new User();
		user.setCPF("12345678912");
		user.setDataNascimento(LocalDate.now());
		user.setEmail("lucascaffer@gmail.com");
		user.setLogin("C4ffer");
		user.setPassword("123456");
		user.setTelefone("11 992930434");
		
		UserDAO userDAO = new UserDAO();
		userDAO.Atualizar(user);
		
		Anuncio anuncio = new Anuncio();
		anuncio.setCPF("12345678912");
		anuncio.setDataAnuncio(LocalDate.now());
		anuncio.setDescricao("Descricao");
		anuncio.setIdAnuncio(2);
		anuncio.setTitulo("Outro Titulo");
		anuncio.setValorHora(2);
		
		AnuncioDAO anuncioDAO = new AnuncioDAO();
		anuncioDAO.Atualizar(anuncio);
		
		Solicitacao solicitacao = new Solicitacao();
		solicitacao.setDataSolicitacao(LocalDate.now());
		solicitacao.setIdAnuncio(3);
		solicitacao.setIdSolicitacao(2);
		solicitacao.setQtdHoras(1);
		solicitacao.setStatus("Desativado");
		solicitacao.setValorTotal(50);
		
		
		SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
		solicitacaoDAO.Atualizar(solicitacao);
		
		*/
		//Teste Delete
		/*
		SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
		solicitacaoDAO.Remover(2);
		AnuncioDAO anuncioDAO = new AnuncioDAO();
		anuncioDAO.Remover(2);
		
		UserDAO userDAO = new UserDAO();
		userDAO.Remover("12345678912");
		*/
		//Teste Select
		/*
		User user = new User();
		UserDAO userDAO = new UserDAO();
		List<User> listaUser = userDAO.Consultar("12345678912");
		
		user = listaUser.get(0);
		System.out.println(user.getCPF());
		System.out.println(user.getEmail());
		System.out.println(user.getLogin());
		System.out.println(user.getPassword());
		System.out.println(user.getTelefone());
		System.out.println(user.getDataNascimento());
		
		AnuncioDAO anuncioDAO = new AnuncioDAO();
		List<Anuncio> listaAnuncio = anuncioDAO.Consultar(4);
		Anuncio anuncio = listaAnuncio.get(0);
		
		System.out.println(anuncio.getIdAnuncio());
		System.out.println(anuncio.getCPF());
		System.out.println(anuncio.getDescricao());
		System.out.println(anuncio.getTitulo());
		System.out.println(anuncio.getValorHora());
		System.out.println(anuncio.getDataAnuncio());
		
		SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
		List<Solicitacao> listaSolicitacao = solicitacaoDAO.Consultar(4);
		
		Solicitacao solicitacao = listaSolicitacao.get(1);
		System.out.println(solicitacao.getIdSolicitacao());
		System.out.println(solicitacao.getIdAnuncio());
		System.out.println(solicitacao.getQtdHoras());
		System.out.println(solicitacao.getStatus());
		System.out.println(solicitacao.getValorTotal());
		System.out.println(solicitacao.getDataSolicitacao());
		*/
		
		System.out.println("Funcionando");
	}

}

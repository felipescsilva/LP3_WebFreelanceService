package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Solicitacao;


public class SolicitacaoDAO {
	ConexaoDAO con;
	public boolean Inserir(Solicitacao solicitacaoObjeto) {
		PreparedStatement ps = null;
		try {
			con = new ConexaoDAO();
			String dataSolicitacao = solicitacaoObjeto.getDataSolicitacao().getDayOfMonth() + "/" + solicitacaoObjeto.getDataSolicitacao().getMonthValue() + "/" + solicitacaoObjeto.getDataSolicitacao().getYear();
			String SQL = "insert into tblSolicitacao values (?, ?, ?, ?, ?, ?, ?)";
			ps = con.getConexao().prepareStatement(SQL);
			ps.setInt(1, solicitacaoObjeto.getIdAnuncio());
			ps.setInt(2, solicitacaoObjeto.getQtdHoras());
			ps.setString(3, dataSolicitacao);
			ps.setString(4, solicitacaoObjeto.getStatus());
			ps.setDouble(5, solicitacaoObjeto.getValorTotal());
			ps.setString(6, solicitacaoObjeto.getCPF_User());
			ps.setString(7, solicitacaoObjeto.getCPF_Anunciante());
			
			if (ps.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				con.FecharConexao();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public boolean Atualizar(Solicitacao solicitacaoObjeto) {
		PreparedStatement ps = null;
		try {
			con = new ConexaoDAO();
			String dataSolicitacao = solicitacaoObjeto.getDataSolicitacao().getDayOfMonth() + "/" + solicitacaoObjeto.getDataSolicitacao().getMonthValue() + "/" + solicitacaoObjeto.getDataSolicitacao().getYear();
			String SQL = "update tblSolicitacao set idAnuncio = ?, qtdHoras = ?, DataSolicitacao = ?, Status = ?, ValorTotal = ? where idSolicitacao = ?";
			ps = con.getConexao().prepareStatement(SQL);
			ps.setInt(1, solicitacaoObjeto.getIdAnuncio());
			ps.setInt(2, solicitacaoObjeto.getQtdHoras());
			ps.setString(3, dataSolicitacao);
			ps.setString(4, solicitacaoObjeto.getStatus());
			ps.setDouble(5, solicitacaoObjeto.getValorTotal());
			ps.setString(6, solicitacaoObjeto.getCPF_User());
			ps.setString(7, solicitacaoObjeto.getCPF_Anunciante());
			ps.setInt(8, solicitacaoObjeto.getIdSolicitacao());
			
			if (ps.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				con.FecharConexao();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public boolean Remover(int idSolicitacao) {
		PreparedStatement ps = null;
		try {
			con = new ConexaoDAO();
			String SQL = "delete from tblSolicitacao where idSolicitacao = ?";
			ps = con.getConexao().prepareStatement(SQL);
			ps.setInt(1, idSolicitacao);
			
			if (ps.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				con.FecharConexao();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public List<Solicitacao> Consultar(int idSolicitacao){
		List<Solicitacao> lista = new ArrayList<Solicitacao>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = new ConexaoDAO();
			String SQL = "select * from tblSolicitacao where idSolicitacao = ?";
			ps = con.getConexao().prepareStatement(SQL);
			ps.setInt(1, idSolicitacao);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Solicitacao solicitacao = new Solicitacao();
	
				solicitacao.setIdSolicitacao(rs.getInt("idSolicitacao"));
				solicitacao.setDataSolicitacao(LocalDate.parse(rs.getDate("DataSolicitacao").toString()));
				solicitacao.setStatus(rs.getString("Status"));
				solicitacao.setValorTotal(rs.getDouble("ValorTotal"));
				solicitacao.setIdAnuncio(rs.getInt("idAnuncio"));
				solicitacao.setQtdHoras(rs.getInt("qtdHoras"));
				solicitacao.setCPF_User(rs.getString("CPF_User"));
				solicitacao.setCPF_Anunciante(rs.getString("CPF_Anunciante"));
				
				lista.add(solicitacao);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 try{
				 if (rs != null)
					 rs.close();
				 if(ps != null)
					 ps.close();
				 con.FecharConexao();
			 }catch(Exception e){
				 e.printStackTrace();
			 }
		}
		
		return lista;
	}
	
	public List<Solicitacao> Consultar(){
		List<Solicitacao> lista = new ArrayList<Solicitacao>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = new ConexaoDAO();
			String SQL = "select * from tblSolicitacao";
			ps = con.getConexao().prepareStatement(SQL);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Solicitacao solicitacao = new Solicitacao();
	
				solicitacao.setIdSolicitacao(rs.getInt("idSolicitacao"));
				solicitacao.setDataSolicitacao(LocalDate.parse(rs.getDate("DataSolicitacao").toString()));
				solicitacao.setStatus(rs.getString("Status"));
				solicitacao.setValorTotal(rs.getDouble("ValorTotal"));
				solicitacao.setIdAnuncio(rs.getInt("idAnuncio"));
				solicitacao.setQtdHoras(rs.getInt("qtdHoras"));
				solicitacao.setCPF_User(rs.getString("CPF_User"));
				solicitacao.setCPF_Anunciante(rs.getString("CPF_Anunciante"));
				
				lista.add(solicitacao);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 try{
				 if (rs != null)
					 rs.close();
				 if(ps != null)
					 ps.close();
				 con.FecharConexao();
			 }catch(Exception e){
				 e.printStackTrace();
			 }
		}
		
		return lista;
	}
	
	public List<Solicitacao> Consultar(String campo, String valor){
		List<Solicitacao> lista = new ArrayList<Solicitacao>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = new ConexaoDAO();
			String SQL = "exec dbo.sp_consulta tblSolicitacao, ?, ?";
			ps = con.getConexao().prepareStatement(SQL);
			ps.setString(1, campo);
			ps.setString(2, valor);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Solicitacao solicitacao = new Solicitacao();
	
				solicitacao.setIdSolicitacao(rs.getInt("idSolicitacao"));
				solicitacao.setDataSolicitacao(LocalDate.parse(rs.getDate("DataSolicitacao").toString()));
				solicitacao.setStatus(rs.getString("Status"));
				solicitacao.setValorTotal(rs.getDouble("ValorTotal"));
				solicitacao.setIdAnuncio(rs.getInt("idAnuncio"));
				solicitacao.setQtdHoras(rs.getInt("qtdHoras"));
				solicitacao.setCPF_User(rs.getString("CPF_User"));
				solicitacao.setCPF_Anunciante(rs.getString("CPF_Anunciante"));
				
				lista.add(solicitacao);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 try{
				 if (rs != null)
					 rs.close();
				 if(ps != null)
					 ps.close();
				 con.FecharConexao();
			 }catch(Exception e){
				 e.printStackTrace();
			 }
		}
		
		return lista;
	}
}

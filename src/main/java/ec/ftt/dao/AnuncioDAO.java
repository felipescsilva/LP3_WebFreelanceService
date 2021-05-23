package ec.ftt.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ec.ftt.model.Anuncio;

public class AnuncioDAO {
	ConexaoDAO con;
	public boolean Inserir(Anuncio anuncioObjeto) {
		PreparedStatement ps = null;
		try {
			con = new ConexaoDAO();
			String dataAnuncio = anuncioObjeto.getDataAnuncio().getDayOfMonth() + "/" + anuncioObjeto.getDataAnuncio().getMonthValue() + "/" + anuncioObjeto.getDataAnuncio().getYear();
			String SQL = "insert into tblAnuncio values (?, ?, ?, ?, ?)";
			ps = con.getConexao().prepareStatement(SQL);
			ps.setString(1, anuncioObjeto.getCPF());
			ps.setString(2, anuncioObjeto.getTitulo());
			ps.setString(3, anuncioObjeto.getDescricao());
			ps.setDouble(4, anuncioObjeto.getValorHora());
			ps.setString(5, dataAnuncio);
			
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
	
	
	public boolean Atualizar(Anuncio anuncioObjeto) {
		PreparedStatement ps = null;
		try {
			con = new ConexaoDAO();
			String dataAnuncio = anuncioObjeto.getDataAnuncio().getDayOfMonth() + "/" + anuncioObjeto.getDataAnuncio().getMonthValue() + "/" + anuncioObjeto.getDataAnuncio().getYear();
			String SQL = "update tblAnuncio set CPF = ?, Titulo = ?, Descricao = ?, ValorHora = ?, DataAnuncio = ? where idAnuncio = ?";
			ps = con.getConexao().prepareStatement(SQL);
			ps.setString(1, anuncioObjeto.getCPF());
			ps.setString(2, anuncioObjeto.getTitulo());
			ps.setString(3, anuncioObjeto.getDescricao());
			ps.setDouble(4, anuncioObjeto.getValorHora());
			ps.setString(5, dataAnuncio);
			ps.setInt(6, anuncioObjeto.getIdAnuncio());
			
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
	
	public boolean Remover(int idAnuncio) {
		PreparedStatement ps = null;
		try {
			con = new ConexaoDAO();
			String SQL = "delete from tblAnuncio where idAnuncio = ?";
			ps = con.getConexao().prepareStatement(SQL);
			ps.setInt(1, idAnuncio);
			
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
	
	
	public List<Anuncio> Consultar(int idAnuncio){
		List<Anuncio> lista = new ArrayList<Anuncio>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = new ConexaoDAO();
			String SQL = "select * from tblAnuncio where idAnuncio = ?";
			ps = con.getConexao().prepareStatement(SQL);
			ps.setInt(1, idAnuncio);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Anuncio anuncio = new Anuncio();
	
				anuncio.setCPF(rs.getString("CPF"));
				anuncio.setDataAnuncio(LocalDate.parse(rs.getDate("DataAnuncio").toString()));
				anuncio.setDescricao(rs.getString("Descricao"));
				anuncio.setValorHora(rs.getDouble("ValorHora"));
				anuncio.setIdAnuncio(rs.getInt("idAnuncio"));
				
				lista.add(anuncio);
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
	
	public List<Anuncio> Consultar(){
		List<Anuncio> lista = new ArrayList<Anuncio>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = new ConexaoDAO();
			String SQL = "select * from tblAnuncio";
			ps = con.getConexao().prepareStatement(SQL);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Anuncio anuncio = new Anuncio();
	
				anuncio.setCPF(rs.getString("CPF"));
				anuncio.setDataAnuncio(LocalDate.parse(rs.getDate("DataAnuncio").toString()));
				anuncio.setDescricao(rs.getString("Descricao"));
				anuncio.setValorHora(rs.getDouble("ValorHora"));
				anuncio.setIdAnuncio(rs.getInt("idAnuncio"));
				
				lista.add(anuncio);
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

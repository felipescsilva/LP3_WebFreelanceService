package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Solicitacao;
import model.User;

public class UserDAO {
	ConexaoDAO con;
	public boolean Inserir(User userObjeto) {
		PreparedStatement ps = null;
		try {
			con = new ConexaoDAO();
			
			String dataNascimento = userObjeto.getDataNascimento().getDayOfMonth() + "/" + userObjeto.getDataNascimento().getMonthValue() + "/" + userObjeto.getDataNascimento().getYear();
			String SQL = "insert into tblUser values (?, ?, ?, ?, ?, ?)";
			ps = con.getConexao().prepareStatement(SQL);
			ps.setString(1, userObjeto.getCPF());
			ps.setString(2, userObjeto.getLogin());
			ps.setString(3, userObjeto.getPassword());
			ps.setString(4, dataNascimento);
			ps.setString(5, userObjeto.getTelefone());
			ps.setString(6, userObjeto.getEmail());
			
			
			if (ps.executeUpdate() > 0)
				return true;
			
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
	
	public boolean Atualizar(User userObjeto) {
		PreparedStatement ps = null;
		try {
			con = new ConexaoDAO();
			
			String dataNascimento = userObjeto.getDataNascimento().getDayOfMonth() + "/" + userObjeto.getDataNascimento().getMonthValue() + "/" + userObjeto.getDataNascimento().getYear();
			String SQL = "update tblUser set Login = ?, Password = ?, DataNascimento = ?, Telefone = ?, Email = ? where CPF = ?";
			ps = con.getConexao().prepareStatement(SQL);
			ps.setString(1, userObjeto.getLogin());
			ps.setString(2, userObjeto.getPassword());
			ps.setString(3, dataNascimento);
			ps.setString(4, userObjeto.getTelefone());
			ps.setString(5, userObjeto.getEmail());
			ps.setString(6, userObjeto.getCPF());
			
			
			if (ps.executeUpdate() > 0)
				return true;
			
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
	
	public boolean Remover(String CPF) {
		PreparedStatement ps = null;
		try {
			con = new ConexaoDAO();
			
			String SQL = "delete from tblUser where CPF = ?";
			ps = con.getConexao().prepareStatement(SQL);
			ps.setString(1, CPF);
			
			
			if (ps.executeUpdate() > 0)
				return true;
			
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
	
	public List<User> Consultar(String CPF){
		List<User> lista = new ArrayList<User>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = new ConexaoDAO();
			String SQL = "select * from tblUser where CPF = ?";
			ps = con.getConexao().prepareStatement(SQL);
			ps.setString(1, CPF);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				
				user.setCPF(rs.getString("CPF"));
				user.setDataNascimento(LocalDate.parse(rs.getDate("DataNascimento").toString()));
				user.setLogin(rs.getString("Login"));
				user.setPassword(rs.getString("Password"));
				user.setTelefone(rs.getString("Telefone"));
				user.setEmail(rs.getString("Email"));
				
				lista.add(user);
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
	
	public List<User> Consultar(){
		List<User> lista = new ArrayList<User>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = new ConexaoDAO();
			String SQL = "select * from tblUser";
			ps = con.getConexao().prepareStatement(SQL);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				
				user.setCPF(rs.getString("CPF"));
				user.setDataNascimento(LocalDate.parse(rs.getDate("DataNascimento").toString()));
				user.setLogin(rs.getString("Login"));
				user.setPassword(rs.getString("Password"));
				user.setTelefone(rs.getString("Telefone"));
				user.setEmail(rs.getString("Email"));
				
				lista.add(user);
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

package br.univel.entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClienteDao {

	private static final String SQL_BUSCA_TODOS = "SELECT * FROM listacliente";
	private Connection con;
	private StringBuilder sb;
	private PreparedStatement ps;
	private String sql;
	
	public List<Cliente> getTodos(){
		
		con = ConexaoDB.getIntance().getConnection();
		List<Cliente> lista = new ArrayList<>();
		
		try(PreparedStatement ps = con.prepareStatement(SQL_BUSCA_TODOS); ResultSet rs = ps.executeQuery()){
			
			while(rs.next()){
				Cliente c = new Cliente();
				c.setIdade(rs.getLong("idade"));
				c.setNome(rs.getString("nome"));
				c.setCpf(rs.getString("cpf"));
				c.setEmail(rs.getString("email"));
				
				lista.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public void insere(Cliente c){
		con = ConexaoDB.getIntance().getConnection();
		
		sb = new StringBuilder();
		sb.append("INSERT INTO listacliente (idade, nome, cpf, email)");
		sb.append("VALUES (" + c.getIdade() + ", '" + c.getNome() + "', " + "'" +  c.getCpf() + "'" + "'" + 
		c.getEmail() + "'" + ")");
		
		sql = sb.toString();
		System.out.println(sql);
		
		try {
			
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void alterar(int id, Cliente c){
		con = ConexaoDB.getIntance().getConnection();
		
		sb = new StringBuilder();
		sb.append("UPDATE listacliente SET nome = '" + c.getNome() + "' WHERE idade = " + id);
		
		sql = sb.toString();
		System.out.println(sql);
		
		try {
		
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void excluir(int id){
		con = ConexaoDB.getIntance().getConnection();
		
		sb = new StringBuilder();
		sb.append("DELETE FROM listacliente WHERE idade = " + id);
		
		sql = sb.toString();
		System.out.println(sql);
		
		try {
		
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}

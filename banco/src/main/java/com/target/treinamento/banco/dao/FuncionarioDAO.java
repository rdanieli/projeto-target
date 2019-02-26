package com.target.treinamento.banco.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.target.treinamento.banco.FabricaDeConexao;
import com.target.treinamento.banco.LogManager;
import com.target.treinamento.banco.dominio.Funcionario;

public class FuncionarioDAO {

	public void criar(Funcionario funcionario) {
		FabricaDeConexao conexao = new FabricaDeConexao();

		try {
			conexao.setLogManager(new LogManager());

			conexao.abreConexao();

			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO funcionarios");
			sb.append("(nome, cargo, idade, salario, data_inclusao)");
			sb.append("VALUES (?, ?, ?, ?, ?)");

			PreparedStatement ps = conexao.getPreparedStatement(sb.toString());
			ps.setString(1, funcionario.getNome());
			ps.setInt(2, funcionario.getCargo());
			ps.setInt(3, funcionario.getIdade());
			ps.setDouble(4, funcionario.getSalario());
			ps.setDate(5, new Date(funcionario.getDataInclusao().getTimeInMillis()));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conexao.fecharConexao();
		}
	}

	public List<Funcionario> buscarTodos() {
		FabricaDeConexao conexao = new FabricaDeConexao();
		List<Funcionario> funcionariosLista = new ArrayList<Funcionario>();
		
		conexao.setLogManager(new LogManager());
		
		try (Connection c = conexao.abreConexao()) {
			
			PreparedStatement ps = c.prepareStatement("");
			
			ResultSet resultSet = ps.executeQuery();
		
			while (resultSet.next()) {
				Funcionario f = new Funcionario();
				f.setId(resultSet.getLong("ID"));
				f.setNome(resultSet.getString("NOME"));
				
				Calendar dataInclusao = Calendar.getInstance();//AGORA 22/02/2019 22:31
				
				Date dateDoBanco = resultSet.getDate("data_inclusao");
				
				dataInclusao.setTimeInMillis(dateDoBanco.getTime());
				
				f.setDataInclusao(dataInclusao);
				
				
				funcionariosLista.add(f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return funcionariosLista;	
	}

}

package com.target.treinamento.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Connection connection = null;
    	
        try 
        {
        	Class.forName("org.postgresql.Driver");
        	
        	//Criação da conexão com o banco de dados
			 connection = DriverManager.getConnection("jdbc:postgresql://ec2-23-21-128-35.compute-1.amazonaws.com:5432/d5k5g3oob6tn20", 
																			"kxwedtxgcfjgvt", 
																			"218b0dd9927d70d198d3f587b28ad32c6dd9cd00ac1c5d33803b8bc982f819e2"); //banco
			
			
			System.out.println("Java Connection JDBC." + connection.toString());
			
			String sql = "insert into pessoas(PRIMEIRO_NOME,SEGUNDO_NOME, ENDERECO, CIDADE) "
					+ "VALUES (?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "Pedrinho");
			statement.setString(2, "Alfredo");
			statement.setString(3, "Rua Bento rosa");
			statement.setString(4, "Lajeado");
			int retorno = statement.executeUpdate();
			
			if(retorno == 1) 
			{
				System.out.println("Sucesso!");
			} 
			
			else 
			{
				System.out.println("Erro ao gravar dados!");
			}
			
			statement.close();
			
			ResultSet resultSet = connection.createStatement().executeQuery(
					"SELECT P.ID, P.PRIMEIRO_NOME, "
					+ "P.SEGUNDO_NOME, P.ENDERECO, P.CIDADE "
					+ "FROM PESSOAS P");
			
			while(resultSet.next()) 
			{
				String primeiroNome = resultSet.getString("PRIMEIRO_NOME");
				String segundoNome = resultSet.getString("SEGUNDO_NOME");
				String endereco = resultSet.getString("ENDERECO");
				String cidade = resultSet.getString("CIDADE");
				Long id = resultSet.getLong("ID");
				
				System.out.println(id);
				System.out.println(primeiroNome);
				System.out.println(segundoNome);
				System.out.println(endereco);
				System.out.println(cidade);
				System.out.println("####################################");
			}
		} 
        
        catch (SQLException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
        catch (ClassNotFoundException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
        //Bloco que sempre será executado, independente de exception ou não.
        finally 
        {
			if(connection != null) 
			{
				try 
				{
					connection.close();
					System.out.println("Fechando a conexão");
				} 
				
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
        		
    }
}

package com.target.treinamento.banco;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.target.treinamento.banco.dominio.Banco;
import com.target.treinamento.banco.dominio.Funcionario;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	EntityManagerFactory entityManagerFactory = 
    			Persistence.createEntityManagerFactory("testBanco");
    	
    	EntityManager entityManager = entityManagerFactory.createEntityManager();
    	
    	
    	entityManager.getTransaction().begin();
    	
    	//Executar a transação
    	Funcionario func = entityManager.find(Funcionario.class, 110L);
    	System.out.println(func.getSalario());
    	System.out.println(func.toString());
    	System.out.println(func.getBanco().getNome());
    	
    	
    	
    	Banco banco = entityManager.find(Banco.class, 1L);
    	
    	for(Funcionario f : banco.getFuncionarios()) {
    		System.out.println(f.getNome());
    	}
    	
    	
    	entityManager.getTransaction().commit();
    	
    	
    	entityManagerFactory.close();
    }
}

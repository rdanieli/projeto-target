package com.target.treinamento.banco;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FabricaDeConexaoTest {

	@Mock
	LogManager logManager;
	
	@Before
	public void testandoBefore() {
		System.out.println("Testando o before");
	}
	
	static FabricaDeConexao fabricaDeConexao;
	
	@BeforeClass
	public static void testandoBeforeClass() {
		 fabricaDeConexao = new FabricaDeConexao();
	}
		
	@Test
	public void testaSeAConexaoFoiCriada() {
		
		fabricaDeConexao.setLogManager(logManager);
				
		assertNotNull(fabricaDeConexao.abreConexao());
		
		
	}
	
	@Test
	public void testaSeAConexaoFoiCriadaSemLogManager() {
		
		try {
			fabricaDeConexao.setLogManager(null);
			
			fabricaDeConexao.abreConexao();
			
			fail();
		}catch(NullPointerException e) {
			assertTrue(true);
		} 
	}
	
	@Test
	public void testPreparedStatementEstaFuncionando() {
		fabricaDeConexao.setLogManager(logManager);
		fabricaDeConexao.abreConexao();
		
		PreparedStatement ps = fabricaDeConexao.getPreparedStatement("SELECT P.PRIMEIRO_NOME FROM PESSOAS P");
		
		try {
			ResultSet resultSet = ps.executeQuery();
			
			assertEquals(resultSet.next(), true);
			
			System.out.println(resultSet.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testaConexaoNaoCriadaAoFecharConexao() {
		new FabricaDeConexao().fecharConexao();
		assertTrue(true);
	}
	
	
	@After
	public void testandoAfter() {
		System.out.println("testando after");
	}
	
	@AfterClass
	public static void testandoAfterClass() {
		fabricaDeConexao.fecharConexao();
	}
}

package com.target.treinamento.banco;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FabricaDeConexaoTest {

	@Mock
	LogManager logManager;
		
	@Test
	public void testaSeAConexaoFoiCriada() {
		FabricaDeConexao fabricaDeConexao = new FabricaDeConexao();
		
		fabricaDeConexao.setLogManager(logManager);
		
		assertNotNull(fabricaDeConexao.getConexao());
		
		fabricaDeConexao.fecharConexao();
	}
	
	@Test
	public void testaSeAConexaoFoiCriadaSemLogManager() {
		FabricaDeConexao fabricaDeConexao = null;
		try {
			fabricaDeConexao = new FabricaDeConexao();
			
			fabricaDeConexao.getConexao();
			
			fail();
		}catch(NullPointerException e) {
			assertTrue(true);
		} finally {
			fabricaDeConexao.fecharConexao();
		}
	}
	
}

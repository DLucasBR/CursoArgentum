package br.com.alura.argentum.modelo;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;


public class NegociacaoTest {
	
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveCriarNegociacaoComPrecoNegativo() {
		new Negociacao(-20.0, 3, LocalDateTime.now());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveCriarNegociacaoComDataNula() {
		new Negociacao(20.0, 100, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveCriaNegociacaoComQuantidadeNegativa() {
		new Negociacao(10.0, -2, LocalDateTime.now());
	}
	
	@Test
    public void mesmoSegundoEhMesmoDia() {
		LocalDateTime agora =LocalDateTime.now();
		LocalDateTime mesmoMomento = agora;
		
		Negociacao negociacao = new Negociacao(40.0, 100, agora);
		
		Assert.assertTrue(negociacao.isMesmoDia(mesmoMomento));
		
    }
	
	@Test
    public void horariosDiferentesEhMesmoDia() {
		LocalDateTime manha = LocalDateTime.of(2016, 04,04,8,30);
		LocalDateTime tarde = LocalDateTime.of(2016, 04,04,15,30);
		
		Negociacao negociacao = new Negociacao(40.0, 100, manha);
		
		Assert.assertTrue(negociacao.isMesmoDia(tarde));
		
    }
	
	@Test 
	public void mesmoDiaMasMesesDiferentesNaoSaoDoMesmoDia() {
		LocalDateTime mesAtual = LocalDateTime.of(2016,02,25,8,30);
		LocalDateTime outroMes = LocalDateTime.of(2016,03,25,8,30);
	
		Negociacao negociacao = new Negociacao(40.0, 100, mesAtual);
		
		Assert.assertFalse(negociacao.isMesmoDia(outroMes));
	}
	
	@Test 
	public void mesmoDiaEMesMasAnosDiferentesNaoSaoDoMesmoDia() {
		LocalDateTime anoAtual = LocalDateTime.of(2016,02,25,8,30);
		LocalDateTime outroAno = LocalDateTime.of(2017,02,25,8,30);
	
		Negociacao negociacao = new Negociacao(40.0, 100, anoAtual);
		
		Assert.assertFalse(negociacao.isMesmoDia(outroAno));
	}
	
}

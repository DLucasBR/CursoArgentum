package br.com.alura.argentum.modelo;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

class CandlestickTest {

	@Test(expected = IllegalArgumentException.class)
	public void maximoNaoDeveSerMenorDoQueMinimo() {
		CandleBuilder builder = new CandleBuilder();
		Candlestick candle = builder.comAbetura(10.0).comFechamento(30.0).comMaximo(15.0).comMinimo(25.0)
				.comVolume(100).comData(LocalDateTime.now()).geraCandle();
	}
	
	@Test
	public void ehAltaSeFechamentoForIgualAbertura() {
		CandleBuilder builder = new CandleBuilder();
		Candlestick candle = builder.comAbetura(10.0).comFechamento(10.0).comMaximo(50.0).comMinimo(25.0)
				.comVolume(100).comData(LocalDateTime.now()).geraCandle();
	
		assertTrue(candle.isAlta());
	}

}

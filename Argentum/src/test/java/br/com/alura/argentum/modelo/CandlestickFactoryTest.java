package br.com.alura.argentum.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class CandlestickFactoryTest {

	@Test
	public void sequenciaDeNegociacaoesSimples() {

		LocalDateTime hoje = LocalDateTime.now();

		Negociacao negociacao1 = new Negociacao(40.0, 100, hoje);
		Negociacao negociacao2 = new Negociacao(35.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao4 = new Negociacao(20.0, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);

		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.geraCandleParaData(negociacoes, hoje);

		/*Assert.asserEquals(...), dispensamos o Assert, pois fizemos o import static de todos os membros
		 * estaticos da classe Assert*/
		
		assertEquals(20.0, candle.getMinimo(), 0.0000001); 
		assertEquals(45.0, candle.getMaximo(), 0.0000001);
		assertEquals(40.0, candle.getAbertura(), 0.0000001);
		assertEquals(20.0, candle.getFechamento(), 0.0000001);
		assertEquals(14000.0, candle.getVolume(), 0.0000001);
	}

	@Test
	public void geraCandlestickComApenasUmaNegociacao() {
		LocalDateTime data = LocalDateTime.now();
		Negociacao negociacao = new Negociacao(40.0, 100, data);

		CandlestickFactory factory = new CandlestickFactory();

		List<Negociacao> negociacoes = Arrays.asList(negociacao);

		Candlestick candle = factory.geraCandleParaData(negociacoes, data);

		assertEquals(40.0, candle.getMinimo(), 0.0000001);
		assertEquals(40.0, candle.getMaximo(), 0.0000001);
		assertEquals(40.0, candle.getAbertura(), 0.0000001);
		assertEquals(40.0, candle.getFechamento(), 0.0000001);
		assertEquals(4000.0, candle.getVolume(), 0.0000001);

	}

	@Test
	public void geraCandlestickComZerosEmCasoDeNenhumaNegociacao() {
		LocalDateTime data = LocalDateTime.now();

		CandlestickFactory factory = new CandlestickFactory();

		List<Negociacao> negociacoes = new ArrayList<>();

		Candlestick candle = factory.geraCandleParaData(negociacoes, data);

		assertEquals(0.0, candle.getMinimo(), 0.0000001);
		assertEquals(0.0, candle.getMaximo(), 0.0000001);
		assertEquals(0.0, candle.getAbertura(), 0.0000001);
		assertEquals(0.0, candle.getFechamento(), 0.0000001);
		assertEquals(0.0, candle.getVolume(), 0.0000001);

	}

}

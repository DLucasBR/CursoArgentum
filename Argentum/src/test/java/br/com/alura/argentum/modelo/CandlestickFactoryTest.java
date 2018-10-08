package br.com.alura.argentum.modelo;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class CandlestickFactoryTest {

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

		/*
		 * Assert.asserEquals(...), dispensamos o Assert, pois fizemos o import static
		 * de todos os membros estaticos da classe Assert
		 */

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

	@Test
	public void paraNegociacoesDeTresDiasDistintosGeraTresCandles() {
		LocalDateTime hoje = LocalDateTime.now();

		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
		Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);

		LocalDateTime amanha = hoje.plusDays(1);

		Negociacao negociacao5 = new Negociacao(48.8, 100, amanha);
		Negociacao negociacao6 = new Negociacao(49.3, 100, amanha);

		LocalDateTime depois = hoje.plusDays(2);

		Negociacao negociacao7 = new Negociacao(51.8, 100, depois);
		Negociacao negociacao8 = new Negociacao(52.3, 100, depois);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4, negociacao5,
				negociacao6, negociacao7, negociacao8);

		CandlestickFactory factory = new CandlestickFactory();
		List<Candlestick> candles = factory.constroiCandles(negociacoes);

		assertEquals(3, candles.size());
		assertEquals(40.5, candles.get(0).getAbertura(), 0.00001);
		assertEquals(42.3, candles.get(0).getFechamento(), 0.00001);
		assertEquals(48.8, candles.get(1).getAbertura(), 0.00001);
		assertEquals(49.3, candles.get(1).getFechamento(), 0.00001);
		assertEquals(51.8, candles.get(2).getAbertura(), 0.00001);
		assertEquals(52.3, candles.get(2).getFechamento(), 0.00001);
	}

}

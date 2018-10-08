package br.com.alura.argentum.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CandlestickFactory {

	public Candlestick geraCandleParaData(List<Negociacao> negociacoes, LocalDateTime data) {

		double abertura = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double fechamento = negociacoes.isEmpty() ? 0 : negociacoes.get(negociacoes.size() - 1).getPreco();

		double minimo = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double maximo = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();

		double volume = 0;

		for (Negociacao negociacao : negociacoes) {
			volume += negociacao.getVolume();

			if (negociacao.getPreco() > maximo)
				maximo = negociacao.getPreco();
			else if (negociacao.getPreco() < minimo)
				minimo = negociacao.getPreco();
		}

		return new Candlestick(abertura, fechamento, maximo, minimo, volume, data);
	}

	public List<Candlestick> constroiCandles(List<Negociacao> negociacoes) {
		
		LocalDateTime data = negociacoes.get(0).getData();
		List<Negociacao> negociacaoesDoDia = new ArrayList<>();
		List<Candlestick> candlesticks = new ArrayList<>();
		
		
		for(Negociacao negociacao : negociacoes) {
			if(negociacao.isMesmoDia(data)) 
				negociacaoesDoDia.add(negociacao);
			else {
				Candlestick candle = geraCandleParaData(negociacaoesDoDia, data);
				candlesticks.add(candle);
				negociacaoesDoDia = new ArrayList<>();
				negociacaoesDoDia.add(negociacao);
				data = negociacao.getData();
			}
				
		}
		
		Candlestick candle = geraCandleParaData(negociacaoesDoDia, data);
		candlesticks.add(candle);
		
		return candlesticks;
	}
}

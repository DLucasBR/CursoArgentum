package br.com.alura.argentum.modelo;

import java.time.LocalDateTime;

public final class Negociacao {

	/*Classes final nao podem ser herdadas, logo nao podem ter seus metodos sobrescritoss*/
	
	private final double preco;
	private final int quantidade;
	private final LocalDateTime data;

	public Negociacao(double preco, int quantidade, LocalDateTime data) {
		
		if(preco < 0)
			throw new IllegalArgumentException("O preco nao pode ser negativo");
		
		if(data == null)
			throw new IllegalArgumentException("A data nao pode ser nula");
		
		if(quantidade < 1)
			throw new IllegalArgumentException("A quantidade deve ser um valor positivo");
		
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public LocalDateTime getData() {
		return data;
	}
	
	public double getVolume() {
		return this.preco * this.quantidade;
	}

}

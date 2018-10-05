package br.com.alura.argentum.reader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import br.com.alura.argentum.modelo.Negociacao;

class LeitorXmlTest {

	@Test
	public void carregaXmlComApenasUmaNegociacao() {

		String xml = 
				"<list>\n"+
				"  <negociacao>\n" + 
				"    <preco>10.0</preco>\n" + 
				"    <quantidade>4</quantidade>\n" + 
				"    <data>\n" + 
				"      <time>1459782000000</time>\n" + 
				"      <timezone>America/Bahia</timezone>\n" + 
				"    </data>\n" + 
				"  </negociacao>"+
				"</list>";

		LeitorXml leitor = new LeitorXml();
		InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
		List<Negociacao> negociacaoes = leitor.carrega(inputStream);
	
		Negociacao negociacaoEsperada = new Negociacao(10.0, 4, LocalDateTime.of(2016,04,04,12,00));
		
		Assert.assertEquals(1, negociacaoes.size());
		Assert.assertEquals(negociacaoEsperada, negociacaoes.get(0));
	}
}

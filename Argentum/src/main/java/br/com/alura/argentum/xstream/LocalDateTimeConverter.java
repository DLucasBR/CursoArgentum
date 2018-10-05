package br.com.alura.argentum.xstream;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class LocalDateTimeConverter implements Converter {

	@Override
	public boolean canConvert(Class type) {
		return type.isAssignableFrom(LocalDateTime.class); 
		/*Como esse e um converter para LocalDateTime esse metodo verifica se 
		 * dado um objeto se este conversor podera operar com ele (o que so ocorre 
		 * se for passado um LocalDateTime)*/
	}
	
	
	/*Esse metodo transforma um objeto em XML (serializa o objeto)*/
	@Override
	public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
		LocalDateTime data = (LocalDateTime) object;
		ZonedDateTime dataComZona = data.atZone(ZoneOffset.systemDefault()); // Um LocalDateTime nao tem a zona por padrao
		long milis = dataComZona.toInstant().toEpochMilli();
		
		writer.startNode("time");
		writer.setValue(String.valueOf(milis));
		writer.endNode();
		
		writer.startNode("timezone");
		writer.setValue(dataComZona.getZone().toString());
		writer.endNode();
	}

	
	/* Transforma um XML em objetos*/
	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {

		reader.moveDown();
		String milis = reader.getValue();
		reader.moveUp();
		reader.moveDown();
		String timezone = reader.getValue();
		reader.moveUp();
	
		long tempoEmMilis = Long.parseLong(milis);
		Instant tempo = Instant.ofEpochMilli(tempoEmMilis);
		
		ZoneId zone = ZoneId.of(timezone);
		
		ZonedDateTime dataComZona = ZonedDateTime.ofInstant(tempo, zone);
		LocalDateTime data = dataComZona.toLocalDateTime();
		
		return data;
	}

}

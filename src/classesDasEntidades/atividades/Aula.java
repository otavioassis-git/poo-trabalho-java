package classesDasEntidades.atividades;

import java.text.ParseException;
import java.util.Date;

public class Aula extends Atividade {
	private Date data;
	
	public Aula(String n, boolean b, String d, String h) throws IllegalArgumentException{
		super(n, b);
		d = d+"-"+h;
		try {
			data = df.parse(d);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Dado inválido: "+d+".");
		}
	}

	public Date getData() {
		return data;
	}
	
	@Override
	public double getcHoraria() {
		return 2;
	}
}

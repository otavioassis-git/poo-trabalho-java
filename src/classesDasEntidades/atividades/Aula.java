package classesDasEntidades.atividades;

import java.text.ParseException;
import java.util.Date;

public class Aula extends Atividade {
	private Date data;
	
	public Aula(String n, boolean b, String d, String h) throws ParseException {
		super(n, b);
		d = d+" "+h;
		data = df.parse(d);
	}

	public Date getData() {
		return data;
	}
	
	@Override
	public double getcHoraria() {
		return 2;
	}
}

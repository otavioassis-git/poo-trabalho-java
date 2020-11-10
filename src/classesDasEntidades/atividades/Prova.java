package classesDasEntidades.atividades;

import java.text.ParseException;
import java.util.Date;

public class Prova extends Atividade implements Comparable<Prova>{
	private Date data;
	private String conteudo;
	
	public Prova(String n, boolean b, String d, String h, String c) throws IllegalArgumentException{
		super(n, b);
		d = d+"-"+h;
		try {
			data = df.parse(d);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Dado inválido: "+d+".");
		}
		conteudo = c;
	}
	
	public Date getData() {
		return data;
	}

	@Override
	public double getcHoraria() {
		return 2;
	}
	
	@Override
	public int compareTo(Prova o) {
		return data.compareTo(o.data);
	}
}

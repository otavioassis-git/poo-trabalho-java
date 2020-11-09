package classesDasEntidades.atividades;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Prova extends Atividade implements Comparable<Prova>{
	private Date data;
	private String conteudo;
	
	public Prova(String n, boolean b, String d, String h, String c) throws ParseException {
		super(n, b);
		d = d+" "+h;
		data = df.parse(d);
		conteudo = c;
	}
	
	public String getConteudo() {
		return conteudo;
	}

	@Override
	public double getcHoraria() {
		return 2;
	}
	
	public Date getData() {
		return data;
	}

	@Override
	public int compareTo(Prova o) {
		return data.compareTo(o.data);
	}
}

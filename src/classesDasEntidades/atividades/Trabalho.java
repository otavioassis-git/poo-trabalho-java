package classesDasEntidades.atividades;

import java.text.ParseException;
import java.util.Date;

public class Trabalho extends Atividade implements Comparable<Trabalho>{
	private Date prazo;
	private int nmPessoas;
	private double cHoraria;
	
	public Trabalho(String n, boolean b, String p, int nm, double ch) throws ParseException {
		super(n, b);
		p = p+" 23:59";
		prazo = df.parse(p);
		nmPessoas = nm;
		cHoraria = ch;
	}
	
	public Date getPrazo() {
		return prazo;
	}

	@Override
	public double getcHoraria() {
		return cHoraria;
	}

	@Override
	public int compareTo(Trabalho o) {
		return prazo.compareTo(o.prazo);
	}
}

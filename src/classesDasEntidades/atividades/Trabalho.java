package classesDasEntidades.atividades;

import java.text.ParseException;
import java.util.Date;

public class Trabalho extends Atividade implements Comparable<Trabalho>{
	private Date prazo;
	private int nmPessoas;
	private double cHoraria;
	
	public Trabalho(String n, boolean b, String p, int nm, double ch) throws IllegalArgumentException {
		super(n, b);
		p = p+"-23:59";
		try {
			prazo = df.parse(p);
		} catch (ParseException e) {
			String aux[] = p.split("-");
			throw new IllegalArgumentException("Dado inválido: "+aux[0]+".");
		}
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

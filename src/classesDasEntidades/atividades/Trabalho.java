package classesDasEntidades.atividades;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Trabalho extends Atividade{
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

	public int getNmPessoas() {
		return nmPessoas;
	}

	@Override
	public double getcHoraria() {
		return cHoraria;
	}

	public Date getPrazo() {
		return prazo;
	}
	
	public static void sortPrazo(ArrayList<Trabalho> d) {
		Trabalho aux;
		for(int i=0;i<d.size();i++) {
			for(int j=i;j<d.size();j++) {
				if(d.get(i).prazo.compareTo(d.get(j).prazo) < 0) {
					aux = d.get(i);
					d.set(i, d.get(j));
					d.set(j, aux);
				}
			}
		}
	}
}

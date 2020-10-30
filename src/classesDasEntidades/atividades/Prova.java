package classesDasEntidades.atividades;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Prova extends Atividade{
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
	
	public static void sortData(ArrayList<Prova> d) {
		Prova aux;
		for(int i=0;i<d.size();i++) {
			for(int j=i;j<d.size();j++) {
				if(d.get(i).data.compareTo(d.get(j).data) < 0) {
					aux = d.get(i);
					d.set(i, d.get(j));
					d.set(j, aux);
				}
			}
		}
	}
}

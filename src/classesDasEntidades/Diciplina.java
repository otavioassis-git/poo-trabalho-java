package classesDasEntidades;

import java.io.Serializable;
import java.text.Collator;
import java.util.*;

import classesDasEntidades.atividades.Atividade;

public class Diciplina implements Serializable, Comparable<Diciplina> {
    private String nome;
    private String codigo;
    private Docente doc;
    private Periodo per;
    private Map<Long, Estudante> est = new HashMap<>();
    private Map<String, Atividade> atv = new HashMap<>();
    
    public Diciplina(String n, String c, Docente d, Periodo p){
    	nome = n;
    	codigo = c;
    	doc = d;
    	per = p;
    }
    
    public Docente getDoc() {
		return doc;
	}
	public String getNome() {
		return nome;
	}
	public Periodo getPer() {
		return per;
	}
	public String getCodigo() {
		return codigo;
	}
	public Map<Long, Estudante> getEstudantes(){
		return est;
	}
	public Map<String, Atividade> getAtividades(){
		return atv;
	}
	
	public double percentualAtividadeSincrona() {
    	double cont=0;
    	
    	for(String i : atv.keySet()) {
    		if(atv.get(i).isSinc()) {
    			cont++;
    		}
    	}
    	
    	if(atv.size() == 0)
    		return 0;
    	else 
    		return (cont/(double)atv.size())*100;
    }
	
	public double percentualAtividadeAssincrona() {
		double cont=0;
    	
    	for(String i : atv.keySet()) {
    		if(!atv.get(i).isSinc()) {
    			cont++;
    		}
    	}
    	
    	if(atv.size() == 0)
    		return 0;
    	else 
    		return (cont/(double)atv.size())*100;
    }

	public double calculaCargaHoraria() {
		double cont=0;
		
		for(String i : atv.keySet()) {
			cont+=atv.get(i).getcHoraria();
		}
		
		return cont;
	}
	
	public static void sortNome(ArrayList<Diciplina> d) {
		Diciplina aux;
		Collator collator = Collator.getInstance(Locale.getDefault());
	    collator.setStrength(Collator.PRIMARY);
		for(int i=0;i<d.size();i++) {
			for(int j=i;j<d.size();j++) {
				if(collator.compare(d.get(i).getNome(), d.get(j).getNome()) > 0) {
					aux = d.get(i);
					d.set(i, d.get(j));
					d.set(j, aux);
				}
			}
		}
	}

	@Override
	public int compareTo(Diciplina o) {
		int cmp = per.getAnoSemestre().compareTo(o.per.getAnoSemestre());
	    if (cmp != 0)
		  return cmp;
		return codigo.compareTo(o.codigo);
	}
}

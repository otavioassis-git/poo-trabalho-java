package classesDasEntidades;

import java.io.Serializable;
import java.util.*;

public class Diciplina implements Serializable{
    private String nome;
    private Docente doc;
    private Periodo per;
    public Map<Double, Estudante> est = new HashMap<>();
    public Map<Integer, Atividade> atv = new HashMap<>();
    
    public Diciplina(String n, Docente d, Periodo p){
    	nome = n;
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
	
	public double percentualAtividadeSincrona() {
    	double cont=0, cont2=0;
    	
    	for(Integer i : atv.keySet()) {
    		if(atv.get(i).isSinc()) {
    			cont++;
    		}
    	}
    	
    	cont2+=atv.size();
    	
    	if(cont2 == 0)
    		return 0;
    	else 
    		return (cont/cont2)*100;
    }

	public double calculaCargaHoraria() {
		double cont=0;
		
		for(Integer i : atv.keySet()) {
			cont+=atv.get(i).getcHoraria();
		}
		
		return cont;
	}
}

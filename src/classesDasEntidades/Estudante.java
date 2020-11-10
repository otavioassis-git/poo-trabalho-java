package classesDasEntidades;

import java.io.Serializable;
import java.util.*;

public class Estudante implements Serializable, Comparable<Estudante>{
    private long matricula;
	private String nome;
	private Map<String, Diciplina> dic = new HashMap<>();
    
    public Estudante(long m, String n){
    	matricula = m;
    	nome = n;
    }
    
    public long getMatricula() {
		return matricula;
	}
	public String getNome() {
		return nome;
	}
	public Map<String, Diciplina> getDiciplinas() {
		return dic;
	}

	public int contaDiciplina() {
    	int cont=0;
    	for(String s : dic.keySet()) {
    		cont++;
    	}
    	return cont;
    }
    
    public double mediaDiciplinasPorPeriodo() {
    	int cont=1, i=0;
    	Periodo ant = null;
    	
    	for(String s : dic.keySet()) {
    		if(i == 0) {
    			ant = dic.get(s).getPer();
    			i++;
    		}
    		if(!ant.getAnoSemestre().equals(dic.get(s).getPer().getAnoSemestre())) {
    			cont++;
    		}
    		ant = dic.get(s).getPer();
    	}
    	return dic.size()/cont;
    }
    
    public double mediaAvaliacoes() {
    	double cont=0;
    	
    	for(String s : dic.keySet()) {
    		for(Integer i : dic.get(s).getAtividades().keySet()) {
    			if(dic.get(s).getAtividades().get(i).getAvaliacao().get(this.matricula)!=null) {
    				cont++;
    			}
    		}
    	}
    	
    	if(dic.size() == 0)
    		return 0;
    	else
    		return (cont/dic.size());
    }
    
    public Integer contaAvaliacoes() {
    	Integer cont=0;
    	for(String s : dic.keySet()) {
    		for(Integer i : dic.get(s).getAtividades().keySet()) {
    			if(dic.get(s).getAtividades().get(i).getAvaliacao().get(this.matricula)!=null) {
    				cont++;
    			}
    		}
    	}
    	return cont;
    }
    
    public double mediaNotas() {
    	double media=0;
    	
    	for(String s : dic.keySet()) {
    		for(Integer i : dic.get(s).getAtividades().keySet()) {
    			if(dic.get(s).getAtividades().get(i).getAvaliacao().get(this.matricula)!=null) {
    				double aux=0;
    				if(dic.get(s).getAtividades().get(i).getAvaliacao().get(this.matricula)>10)
    					aux=dic.get(s).getAtividades().get(i).getAvaliacao().get(this.matricula)/10;
    				else
    					aux=dic.get(s).getAtividades().get(i).getAvaliacao().get(this.matricula);
    				media+=aux;
    			}
    		}
    	}
    	
    	if(this.contaAvaliacoes() == 0)
    		return 0;
    	else 
    		return (media/this.contaAvaliacoes());
    }

	@Override
	public int compareTo(Estudante o) {
		int cmp = this.contaAvaliacoes().compareTo(o.contaAvaliacoes());
		cmp = -(cmp);
	    if (cmp != 0)
		  return cmp;
		return nome.compareTo(o.nome);
	}
}



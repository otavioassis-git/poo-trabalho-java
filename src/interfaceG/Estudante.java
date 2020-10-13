package interfaceG;

import java.io.Serializable;
import java.util.*;

public class Estudante implements Serializable{
    int matricula;
	String nome;
    Map<String, Diciplina> dic = new HashMap<>();
    
    Estudante(){}
    Estudante(int m, String n){
    	matricula = m;
    	nome = n;
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
    			ant = dic.get(s).per;
    			i++;
    		}
    		if(!ant.anoSemestre.equals(dic.get(s).per.anoSemestre)) {
    			cont++;
    		}
    		ant = dic.get(s).per;
    	}
    	return dic.size()/cont;
    }
    
    public double mediaAvaliacoes() {
    	double cont=0;
    	
    	for(String s : dic.keySet()) {
    		for(Integer i : dic.get(s).aul.keySet()) {
    			if(dic.get(s).aul.get(i).avaliacao.get(this.matricula)!=null) {
    				cont++;
    			}
    		}
    		for(Integer i : dic.get(s).estud.keySet()) {
    			if(dic.get(s).estud.get(i).avaliacao.get(this.matricula)!=null) {
    				cont++;
    			}
    		}
    		for(Integer i : dic.get(s).trab.keySet()) {
    			if(dic.get(s).trab.get(i).avaliacao.get(this.matricula)!=null) {
    				cont++;
    			}
    		}
    		for(Integer i : dic.get(s).prov.keySet()) {
    			if(dic.get(s).prov.get(i).avaliacao.get(this.matricula)!=null) {
    				cont++;
    			}
    		}
    	}
    	
    	if(dic.size() == 0)
    		return 0;
    	else
    		return (cont/dic.size());
    }
    
    public double mediaNotas() {
    	int media=0, cont=0;
    	
    	for(String s : dic.keySet()) {
    		for(Integer i : dic.get(s).aul.keySet()) {
    			if(dic.get(s).aul.get(i).avaliacao.get(this.matricula)!=null) {
    				cont++;
    			}
    		}
    		for(Integer i : dic.get(s).estud.keySet()) {
    			if(dic.get(s).estud.get(i).avaliacao.get(this.matricula)!=null) {
    				cont++;
    			}
    		}
    		for(Integer i : dic.get(s).trab.keySet()) {
    			if(dic.get(s).trab.get(i).avaliacao.get(this.matricula)!=null) {
    				cont++;
    			}
    		}
    		for(Integer i : dic.get(s).prov.keySet()) {
    			if(dic.get(s).prov.get(i).avaliacao.get(this.matricula)!=null) {
    				cont++;
    			}
    		}
    	}
    	
    	if(cont == 0)
    		return 0;
    	else
    		return (media/cont);
    }
}



package classesDasEntidades;

import java.io.Serializable;
import java.util.*;

public class Estudante implements Serializable{
    private long matricula;
	private String nome;
	public Map<String, Diciplina> dic = new HashMap<>();
    
    public Estudante(long m, String n){
    	matricula = m;
    	nome = n;
    }
    
    public double getMatricula() {
		return matricula;
	}
	public String getNome() {
		return nome;
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
    		for(Integer i : dic.get(s).atv.keySet()) {
    			if(dic.get(s).atv.get(i).avaliacao.get(this.matricula)!=null) {
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
    		for(Integer i : dic.get(s).atv.keySet()) {
    			if(dic.get(s).atv.get(i).avaliacao.get(this.matricula)!=null) {
    				cont++;
    			}
    		}
    	}
    	
    	if(cont == 0)
    		return 0;
    	else
    		return (media/cont);
    }
    
    public int contaAvaliacoes() {
    	int cont=0;
    	for(String s : dic.keySet()) {
    		for(Integer i : dic.get(s).atv.keySet()) {
    			if(dic.get(s).atv.get(i).avaliacao.get(this.matricula)!=null) {
    				cont++;
    			}
    		}
    	}
    	return cont;
    }
    
    public static void sortNome(ArrayList<Estudante> d) {
		Estudante aux;
		for(int i=0;i<d.size();i++) {
			for(int j=i;j<d.size();j++) {
				if(d.get(i).nome.compareTo(d.get(j).nome) > 0) {
					aux = d.get(i);
					d.set(i, d.get(j));
					d.set(j, aux);
				}
			}
		}
	}
    
    public static void sortNmAvaliacoes(ArrayList<Estudante> d) {
		Estudante aux;
		for(int i=0;i<d.size();i++) {
			for(int j=i;j<d.size();j++) {
				if(d.get(i).contaAvaliacoes() < d.get(j).contaAvaliacoes()) {
					aux = d.get(i);
					d.set(i, d.get(j));
					d.set(j, aux);
				}
			}
		}
	}
}



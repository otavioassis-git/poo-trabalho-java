package interfaceG;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Docente implements Serializable{
    String login = new String();
	String nome = new String();
    String pagweb = new String();
    Map<String, Diciplina> dic = new HashMap<>();
    Map<Integer, Aula> aul = new HashMap<>();
	Map<Integer, Estudo> estud = new HashMap<>();
	Map<Integer, Trabalho> trab = new HashMap<>();
	Map<Integer, Prova> prov = new HashMap<>();
    
    Docente(){}
    Docente(String l, String n, String p){
    	login = l;
    	nome = n;
    	pagweb = p;
    }
    
    public int contaDiciplinas() {
    	int cont=0;
    	
    	for (String s : dic.keySet()) {
    		cont++;
    	}
    	return cont;
    }
    
    public int contaPeriodos() {
    	int contp=1, i=0;
        Periodo aux = null;
    	
    	for(String s : dic.keySet()) {
    		if(i==0) {
    			aux = dic.get(s).per;
    			i++;
    		}
    		if (!aux.anoSemestre.equals(dic.get(s).per.anoSemestre)) {
    			contp++;
    		}
    		aux = dic.get(s).per;
    	}
    	
    	if(dic.size() == 0)
    		contp--;
    	
    	return contp;
    }
    
    public double mediaAtividadesPorDiciplina() {
    	double media=0;
    	
    	for(String s : dic.keySet()) {
    		media+=dic.get(s).aul.size();
    		media+=dic.get(s).estud.size();
    		media+=dic.get(s).trab.size();
    		media+=dic.get(s).prov.size();
    	}
    	
    	if(media == 0)
    		return 0;
    	else
    		return media/dic.size();
    }
    
    public double percentualAtividadesSincronas() {
    	double cont=0, cont2=0;
    	
    	for (String s : dic.keySet()) {
    		for(Integer i : dic.get(s).aul.keySet()) {
    			if(dic.get(s).aul.get(i).sinc)
    				cont++;
    		}
    		for(Integer i : dic.get(s).estud.keySet()) {
    			if(dic.get(s).estud.get(i).sinc)
    				cont++;
    		}
    		for(Integer i : dic.get(s).trab.keySet()) {
    			if(dic.get(s).trab.get(i).sinc)
    				cont++;
    		}
    		for(Integer i : dic.get(s).prov.keySet()) {
    			if(dic.get(s).prov.get(i).sinc)
    				cont++;
    		}
    	}
    	
    	cont2+=aul.size();
    	cont2+=estud.size();
    	cont2+=trab.size();
    	cont2+=prov.size();
    	
    	if(cont2 == 0)
    		return 0;
    	else 
    		return (cont/cont2)*100;
    }
    
    

    public double mediaNotasRecebidas() {
    	int media=0, cont=0;
    	
    	for(String s : dic.keySet()) {
    		for(Integer i : dic.get(s).aul.keySet()) {
    			for(Integer j : dic.get(s).aul.get(i).avaliacao.keySet()) {
    				cont++;
    				media+= dic.get(s).aul.get(i).avaliacao.get(j);
    			}
    		}
    		for(Integer i : dic.get(s).estud.keySet()) {
    			for(Integer j : dic.get(s).estud.get(i).avaliacao.keySet()) {
    				cont++;
    				media+= dic.get(s).estud.get(i).avaliacao.get(j);
    			}
    		}
    		for(Integer i : dic.get(s).trab.keySet()) {
    			for(Integer j : dic.get(s).trab.get(i).avaliacao.keySet()) {
    				cont++;
    				media+= dic.get(s).trab.get(i).avaliacao.get(j);
    			}
    		}
    		for(Integer i : dic.get(s).prov.keySet()) {
    			for(Integer j : dic.get(s).prov.get(i).avaliacao.keySet()) {
    				cont++;
    				media+= dic.get(s).prov.get(i).avaliacao.get(j);
    			}
    		}
    	}
    	
    	if(media == 0)
    		return 0;
    	else
    		return (media/cont);
    	
    }
}

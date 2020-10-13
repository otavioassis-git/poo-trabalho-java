package interfaceG;

import java.io.Serializable;
import java.util.*;

public class Diciplina implements Serializable{
    String nome = new String();
    Docente doc = new Docente();
    Periodo per = new Periodo();
    Map<Integer, Estudante> est = new HashMap<>();
    Map<Integer, Aula> aul = new HashMap<>();
	Map<Integer, Estudo> estud = new HashMap<>();
	Map<Integer, Trabalho> trab = new HashMap<>();
	Map<Integer, Prova> prov = new HashMap<>();
    
    Diciplina(){}
    Diciplina(String n, Docente d, Periodo p){
    	nome = n;
    	doc = d;
    	per = p;
    }
    
    public double percentualAtividadeSincrona() {
    	double cont=0, cont2=0;
    	
    	for(Integer i : aul.keySet()) {
    		if(aul.get(i).sinc) {
    			cont++;
    		}
    	}
    	for(Integer i : estud.keySet()) {
    		if(estud.get(i).sinc) {
    			cont++;
    		}
    	}
    	for(Integer i : trab.keySet()) {
    		if(trab.get(i).sinc) {
    			cont++;
    		}
    	}
    	for(Integer i : prov.keySet()) {
    		if(prov.get(i).sinc) {
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
}

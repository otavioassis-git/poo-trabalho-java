package classesDasEntidades;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import classesDasEntidades.atividades.Atividade;

public class Docente implements Serializable, Comparable<Docente>{
    private String login;
	private String nome;
    private String pagweb;
    private Map<String, Diciplina> dic = new HashMap<>();
    private Map<String, Atividade> atv = new HashMap<>();

    public Docente(String l, String n, String p){
    	login = l;
    	nome = n;
    	pagweb = p;
    }
    
    public String getLogin() {
		return login;
	}
	public String getNome() {
		return nome;
	}
	public String getPagweb() {
		return pagweb;
	}
	public Map<String, Diciplina> getDiciplinas(){
		return dic;
	}
	public Map<String, Atividade> getAtividades() {
		return atv;
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
    			aux = dic.get(s).getPer();
    			i++;
    		}
    		if (!aux.getAnoSemestre().equals(dic.get(s).getPer().getAnoSemestre())) {
    			contp++;
    		}
    		aux = dic.get(s).getPer();
    	}
    	if(dic.size() == 0)
    		contp--;
    	
    	return contp;
    }
    
    public double mediaAtividadesPorDiciplina() {
    	if(dic.size() == 0)
    		return 0;
    	else {
    		return (double)atv.size()/(double)dic.size();
    	}
    }
    
    public double percentualAtividadesSincronas() {
    	int cont=0;
    	if(atv.size() == 0)
    		return 0;
    	else {
	    	for(String i : atv.keySet()) {
	    		if(atv.get(i).isSinc()) {
	    			cont++;
	    		}
	    	}
    	}
    	return ((double)cont/(double)atv.size())*100;
    }
    
    public double percentualAtividadesAssincronas() {
    	int cont=0;
    	if(atv.size() == 0)
    		return 0;
    	else {
	    	for(String i : atv.keySet()) {
	    		if(!atv.get(i).isSinc()) {
	    			cont++;
	    		}
	    	}
    	}
    	return ((double)cont/(double)atv.size())*100;
    }

    public double mediaNotasRecebidas() {
    	double media=0, cont=0;
    	
    	for(String i : atv.keySet()) {
    		for(Long j : atv.get(i).getAvaliacao().keySet()) {
    			cont++;
    			media+=atv.get(i).getAvaliacao().get(j);
    		}
    	}
    	if(cont == 0)
    		return 0;
    	else {
    		return (media/cont);
    	}
    	
    }

	@Override
	public int compareTo(Docente o) {
		Collator collator = Collator.getInstance(Locale.getDefault());
	    collator.setStrength(Collator.PRIMARY);
	    return -(collator.compare(nome, o.nome));

	}
}

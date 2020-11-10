package classesDasEntidades.atividades;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Atividade implements Serializable{
	protected DateFormat df = new SimpleDateFormat ("dd/MM/yyyy HH:mm");
	protected String nome;
    protected boolean sinc;
    private Map<Long, Double> avaliacao = new HashMap<>(); //<matricula do estudante, avaliacao dele>
 
    public String getNome() {
		return nome;
	}
	public boolean isSinc() {
		return sinc;
	}
	public Map<Long, Double> getAvaliacao(){
		return avaliacao;
	}
	
	Atividade(){}
    Atividade(String n, boolean b){
    	nome = n;
    	sinc = b;
    }
    
    public abstract double getcHoraria();
}
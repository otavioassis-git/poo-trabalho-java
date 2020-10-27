package classesDasEntidades;

import java.io.Serializable;
import java.util.*;

public abstract class Atividade implements Serializable{
    protected String nome;
    protected boolean sinc;
    public Map<Long, Integer> avaliacao = new HashMap<>(); //<matricula do estudante, avaliacao dele>
 
    public String getNome() {
		return nome;
	}
	public boolean isSinc() {
		return sinc;
	}
	
	Atividade(){}
    Atividade(String n, boolean b){
    	nome = n;
    	sinc = b;
    }
    
    public abstract double getcHoraria();
}
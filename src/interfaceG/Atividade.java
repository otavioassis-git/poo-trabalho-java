package interfaceG;

import java.io.Serializable;
import java.util.*;

public class Atividade implements Serializable{
    String nome = new String();
    boolean sinc;
    Map<Integer, Integer> avaliacao = new HashMap<>(); //<matricula do estudante, avaliacao dele>
    
    Atividade(){}
    Atividade(String n, boolean b){
    	nome = n;
    	sinc = b;
    }
    
    
}
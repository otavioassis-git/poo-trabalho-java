package classesDasEntidades;

import java.io.Serializable;
import java.util.*;


public class Periodo implements Serializable, Comparable<Periodo>{
    private String anoSemestre;
    private Map<String, Diciplina> dic = new HashMap<>();
    
    public Periodo(String as){
    	anoSemestre = as;
    }
    
	public String getAnoSemestre() {
		return anoSemestre;
	}
	public Map<String, Diciplina> getDiciplinas(){
		return dic;
	}
	
	@Override
	public int compareTo(Periodo o) {
		return anoSemestre.compareTo(o.anoSemestre);
	}
}

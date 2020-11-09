package classesDasEntidades;

import java.io.Serializable;
import java.util.*;


public class Periodo implements Serializable, Comparable<Periodo>{
    private String anoSemestre;
    public Map<String, Diciplina> dic = new HashMap<>();
    
    public Periodo(String as){
    	anoSemestre = as;
    }
	public String getAnoSemestre() {
		return anoSemestre;
	}
	
	@Override
	public int compareTo(Periodo o) {
		return anoSemestre.compareTo(o.anoSemestre);
	}
}

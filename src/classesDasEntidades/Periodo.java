package classesDasEntidades;

import java.io.Serializable;
import java.util.*;


public class Periodo implements Serializable{
    private String anoSemestre;
    public Map<String, Diciplina> dic = new HashMap<>();
    
    public Periodo(String as){
    	anoSemestre = as;
    }
	public String getAnoSemestre() {
		return anoSemestre;
	}
	
	public static void sortAnoSemestre(ArrayList<Periodo> d) {
		Periodo aux;
		for(int i=0;i<d.size();i++) {
			for(int j=i;j<d.size();j++) {
				if(d.get(i).anoSemestre.compareTo(d.get(j).anoSemestre) < 0) {
					aux = d.get(i);
					d.set(i, d.get(j));
					d.set(j, aux);
				}
			}
		}
	}
}

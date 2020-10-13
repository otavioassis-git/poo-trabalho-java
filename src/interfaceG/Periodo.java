package interfaceG;

import java.io.Serializable;
import java.util.*;


public class Periodo implements Serializable{
    String anoSemestre;
    Map<String, Diciplina> dic = new HashMap<>();
    
    Periodo(){}
    Periodo(String as){
    	anoSemestre = as;
    }
    
    
}

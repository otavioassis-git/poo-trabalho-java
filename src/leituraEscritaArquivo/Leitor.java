package leituraEscritaArquivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import classesDasEntidades.Diciplina;
import classesDasEntidades.Docente;
import classesDasEntidades.Estudante;
import classesDasEntidades.Periodo;
import classesDasEntidades.atividades.Atividade;
import classesDasEntidades.atividades.Aula;
import classesDasEntidades.atividades.Estudo;
import classesDasEntidades.atividades.Prova;
import classesDasEntidades.atividades.Trabalho;

public class Leitor {
	private String path;
	
	public Leitor(String p) {
		path = p;
	}
	
	public Map<String, Periodo> readPeriodo() throws IllegalArgumentException, IOException {
		FileReader arq = new FileReader(path);
		Map<String, Periodo> periodos = new HashMap<>();
		BufferedReader lerarq = new BufferedReader(arq);
		lerarq.readLine();
		String linha = lerarq.readLine();
		while(linha!=null) {
			String dados[] = linha.split(";");
			if(dados[1].toCharArray().length > 1) {
				throw new IllegalArgumentException("Dado inválido: "+dados[1]+".");
			}
			try {
				Integer.parseInt(dados[0]);
			}
			catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Dado inválido: "+dados[0]+".");
			}
			String dadosf = dados[0]+"/"+dados[1];
			if(periodos.containsKey(dadosf)) {
				throw new IllegalArgumentException("Cadastro repetido: "+dadosf+".");
			}
			periodos.put(dadosf, new Periodo(dadosf));
			linha = lerarq.readLine();
		}
		lerarq.close();
		return periodos;
	}
	
	public Map<String, Docente> readDocente() throws IllegalArgumentException, IOException{
		FileReader arq = new FileReader(path);
		Map<String, Docente> docentes = new HashMap<>();
		BufferedReader lerarq = new BufferedReader(arq);
		lerarq.readLine();
		String linha = lerarq.readLine();
		while(linha!=null) {
			String dados[] = linha.split(";");
			Docente aux;
			if(docentes.containsKey(dados[0])) {
				throw new IllegalArgumentException("Cadastro repetido: "+dados[0]+".");
			}
			if(dados.length == 2) {
				aux = new Docente(dados[0], dados[1], "");
			}
			else {
				aux = new Docente(dados[0], dados[1], dados[2]);
			}
			docentes.put(dados[0], aux);
			linha = lerarq.readLine();
		}
		lerarq.close();
		return docentes;
	}
	
	public Map<String, Diciplina> readDiciplina(Map<String, Periodo> periodos, Map<String, Docente> docentes) throws IllegalArgumentException, IOException{
		FileReader arq = new FileReader(path);
		Map<String, Diciplina> diciplinas = new HashMap<>();
		BufferedReader lerarq = new BufferedReader(arq);
		lerarq.readLine();
		String linha = lerarq.readLine();
		while(linha!=null) {
			String dados[] = linha.split(";");
			if(!docentes.containsKey(dados[3])) {
				throw new IllegalArgumentException("Referência inválida: "+dados[3]+".");
			}
			if(!periodos.containsKey(dados[0])) {
				throw new IllegalArgumentException("Referência inválida: "+dados[0]+".");
			}
			if(diciplinas.containsKey(dados[1]+"-"+dados[0])) {
				throw new IllegalArgumentException("Cadastro repetido: "+dados[1]+"-"+dados[0]+".");
			}
			Diciplina aux = new Diciplina(dados[2], dados[1], docentes.get(dados[3]), periodos.get(dados[0]));
			diciplinas.put(dados[1]+"-"+dados[0], aux);
			periodos.get(dados[0]).getDiciplinas().put(dados[1]+"-"+dados[0], diciplinas.get(dados[1]+"-"+dados[0]));
			docentes.get(dados[3]).getDiciplinas().put(dados[1]+"-"+dados[0], diciplinas.get(dados[1]+"-"+dados[0]));
			linha = lerarq.readLine();
		}
		lerarq.close();
		return diciplinas;
	}
	
	public Map<Long, Estudante> readEstudante() throws IllegalArgumentException, IOException{
		FileReader arq = new FileReader(path);
		Map<Long, Estudante> estudantes = new HashMap<>();
		BufferedReader lerarq = new BufferedReader(arq);
		lerarq.readLine();
		String linha = lerarq.readLine();
		while(linha!=null) {
			String dados[] = linha.split(";");
			try {
				Long.parseLong(dados[0]);
			}catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Dado inválido: "+dados[0]+".");
			}
			if(estudantes.containsKey(Long.parseLong(dados[0]))) {
				throw new IllegalArgumentException("Cadastro repetido: "+dados[0]+".");
			}
			Estudante aux = new Estudante(Long.parseLong(dados[0]), dados[1]);
			estudantes.put(Long.parseLong(dados[0]), aux);
			linha = lerarq.readLine();
		}
		lerarq.close();
		return estudantes;
	}
	
	public void readMatriculas(Map<String, Diciplina> diciplinas, Map<Long, Estudante> estudantes) throws IllegalArgumentException, IOException {
		FileReader arq = new FileReader(path);
		BufferedReader lerarq = new BufferedReader(arq);
		lerarq.readLine();
		String linha = lerarq.readLine();
		while(linha!=null) {
			String dados[] = linha.split(";");
			Long mat = Long.parseLong(dados[1]);
			if(!diciplinas.containsKey(dados[0])) {
				throw new IllegalArgumentException("Referência inválida: "+dados[0]+".");
			}
			if(!estudantes.containsKey(mat)) {
				throw new IllegalArgumentException("Referência inválida: "+mat+".");
			}
			if(diciplinas.get(dados[0]).getEstudantes().containsKey(mat)) {
				throw new IllegalArgumentException("Matrícula repetida: "+mat+" em "+dados[0]+".");
			}
			diciplinas.get(dados[0]).getEstudantes().put(mat, estudantes.get(mat));
			estudantes.get(mat).getDiciplinas().put(dados[0], diciplinas.get(dados[0]));
			linha = lerarq.readLine();
		}
		lerarq.close();
	}
	
	public Map<String, Atividade> readAtividade(Map<String, Periodo> periodos, Map<String, Diciplina> diciplinas) throws IllegalArgumentException, IOException{
		FileReader arq = new FileReader(path);
		Map<String, Atividade> atividades = new HashMap<>();
		BufferedReader lerarq = new BufferedReader(arq);
		lerarq.readLine();
		String linha = lerarq.readLine();
		while(linha!=null) {
			int seqnum=1;
			String seq = null;
			String dados[] = linha.split(";");
			if(!diciplinas.containsKey(dados[0])) {
				throw new IllegalArgumentException("Referência inválida: "+dados[0]+".");
			}
			if(dados[2].equals("A")) {
				seqnum=1+diciplinas.get(dados[0]).getAtividades().size();
				seq = seqnum+dados[0];
				atividades.put(seq, new Aula(dados[1], true, dados[3], dados[4]));
				diciplinas.get(dados[0]).getAtividades().put(seq, atividades.get(seq));
				diciplinas.get(dados[0]).getDoc().getAtividades().put(seq, atividades.get(seq));
			}
			else if(dados[2].equals("E")) {
				seqnum=1+diciplinas.get(dados[0]).getAtividades().size();
				seq = seqnum+dados[0];
				atividades.put(seq, new Estudo(dados[1], false, dados[5]));
				diciplinas.get(dados[0]).getAtividades().put(seq, atividades.get(seq));
				diciplinas.get(dados[0]).getDoc().getAtividades().put(seq, atividades.get(seq));
			}
			else if(dados[2].equals("T")) {
				seqnum=1+diciplinas.get(dados[0]).getAtividades().size();
				seq = seqnum+dados[0];
				atividades.put(seq, new Trabalho(dados[1], false, dados[3], Integer.parseInt(dados[6]), Double.parseDouble(dados[7])));
				diciplinas.get(dados[0]).getAtividades().put(seq, atividades.get(seq));
				diciplinas.get(dados[0]).getDoc().getAtividades().put(seq, atividades.get(seq));
			}
			else if(dados[2].equals("P")) {
				seqnum=1+diciplinas.get(dados[0]).getAtividades().size();
				seq = seqnum+dados[0];
				atividades.put(seq, new Prova(dados[0], true, dados[3], dados[4], dados[5]));
				diciplinas.get(dados[0]).getAtividades().put(seq, atividades.get(seq));
				diciplinas.get(dados[0]).getDoc().getAtividades().put(seq, atividades.get(seq));
			}
			linha = lerarq.readLine();
		}
		lerarq.close();
		return atividades;
	}
	
	public void readAvaliacao(Map<String, Diciplina> diciplinas) throws IllegalArgumentException, IOException{
		FileReader arq = new FileReader(path);
		BufferedReader lerarq = new BufferedReader(arq);
		lerarq.readLine();
		String linha = lerarq.readLine();
		while(linha!=null) {
			String dados[] = linha.split(";");
			if(!diciplinas.containsKey(dados[0])) {
				throw new IllegalArgumentException("Referência inválida: "+dados[0]+".");
			}
			try {
				Integer.parseInt(dados[2]);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Dado inválido: "+dados[2]+".");
			}
			String seq = dados[2]+dados[0];
			if(!diciplinas.get(dados[0]).getAtividades().containsKey(seq))
				throw new IllegalArgumentException("Referência inválida: "+dados[2]+".");
			try {
				Integer.parseInt(dados[2]);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Dado inválido: "+dados[2]+".");
			}
			try {
				Long.parseLong(dados[1]);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Dado inválido: "+dados[1]+".");
			}
			if(diciplinas.get(dados[0]).getAtividades().get(seq).getAvaliacao().containsKey(Long.parseLong(dados[1]))) {
				throw new IllegalArgumentException("Avaliação repetida: estudante "+dados[1]+" para atividade "+dados[2]+" de "+dados[0]+".");
			}
			
			char[] aux = dados[3].toCharArray();
			for(int i=0;i<aux.length;i++) {
				if(aux[i] == ',')
					aux[i] = '.';
			}
			dados[3] = String.copyValueOf(aux);
			
			try {
				Double.parseDouble(dados[3]);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Dado inválido: "+dados[3]+".");
			}
			diciplinas.get(dados[0]).getAtividades().get(seq).getAvaliacao().put(Long.parseLong(dados[1]), Double.parseDouble(dados[3]));
			linha = lerarq.readLine();
		}
		lerarq.close();
	}
}

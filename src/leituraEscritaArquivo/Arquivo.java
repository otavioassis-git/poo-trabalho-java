package leituraEscritaArquivo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JOptionPane;

import classesDasEntidades.*;
import classesDasEntidades.atividades.*;

public class Arquivo {
	static NumberFormat nf = NumberFormat.getInstance(Locale.US);
	static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	public static Map<String, Periodo> readPeriodo(String path) throws FileNotFoundException {
		FileReader arq = new FileReader(path);
		Map<String, Periodo> periodos = new HashMap<>();
		try (BufferedReader lerarq = new BufferedReader(arq)){
			lerarq.readLine();
			String linha = lerarq.readLine();
			while(linha!=null) {
				String dados[] = linha.split(";");
				String dadosf = dados[0]+"/"+dados[1];
				Periodo aux = new Periodo(dadosf);
				periodos.put(dadosf, aux);
				linha = lerarq.readLine();
			}
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo!");
		}
		
		return periodos;
	}
	
	public static Map<String, Docente> readDocente(String path) throws FileNotFoundException{
		FileReader arq = new FileReader(path);
		BufferedReader lerarq = new BufferedReader(arq);
		Map<String, Docente> docentes = new HashMap<>();
		try {
			lerarq.readLine();
			String linha = lerarq.readLine();
			while(linha!=null) {
				String dados[] = linha.split(";");
				Docente aux;
				if(dados.length == 2) {
					aux = new Docente(dados[0], dados[1], "(nao obrigatorio)");
				}
				else {
					aux = new Docente(dados[0], dados[1], dados[2]);
				}
				docentes.put(dados[0], aux);
				linha = lerarq.readLine();
			}
			lerarq.close();
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo!");
		}
		return docentes;
	}
	
	public static Map<String, Diciplina> readDiciplina(String path, Map<String, Periodo> periodos, Map<String, Docente> docentes) throws FileNotFoundException{
		FileReader arq = new FileReader(path);
		BufferedReader lerarq = new BufferedReader(arq);
		Map<String, Diciplina> diciplinas = new HashMap<>();
		try {
			lerarq.readLine();
			String linha = lerarq.readLine();
			while(linha!=null) {
				String dados[] = linha.split(";");
				Diciplina aux = new Diciplina(dados[2], dados[1], docentes.get(dados[3]), periodos.get(dados[0]));
				diciplinas.put(dados[1]+"-"+dados[0], aux);
				periodos.get(dados[0]).dic.put(dados[1]+"-"+dados[0], diciplinas.get(dados[1]+"-"+dados[0]));
				docentes.get(dados[3]).dic.put(dados[1]+"-"+dados[0], diciplinas.get(dados[1]+"-"+dados[0]));
				linha = lerarq.readLine();
			}
			lerarq.close();
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo!");
		}
		return diciplinas;
	}
	
	public static Map<Long, Estudante> readEstudante(String path) throws FileNotFoundException{
		FileReader arq = new FileReader(path);
		BufferedReader lerarq = new BufferedReader(arq);
		Map<Long, Estudante> estudantes = new HashMap<>();
		try {
			lerarq.readLine();
			String linha = lerarq.readLine();
			while(linha!=null) {
				String dados[] = linha.split(";");
				Estudante aux = new Estudante(Long.parseLong(dados[0]), dados[1]);
				estudantes.put(Long.parseLong(dados[0]), aux);
				linha = lerarq.readLine();
			}
			lerarq.close();
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo!");
		}
		
		return estudantes;
	}
	
	public static void readMatriculas(String path, Map<String, Diciplina> diciplinas, Map<Long, Estudante> estudantes) throws FileNotFoundException {
		FileReader arq = new FileReader(path);
		BufferedReader lerarq = new BufferedReader(arq);
		try {
			lerarq.readLine();
			String linha = lerarq.readLine();
			while(linha!=null) {
				String dados[] = linha.split(";");
				Long mat = Long.parseLong(dados[1]);
				diciplinas.get(dados[0]).est.put(mat, estudantes.get(mat));
				estudantes.get(mat).dic.put(dados[0], diciplinas.get(dados[0]));
				linha = lerarq.readLine();
			}
			lerarq.close();
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo!");
		}
	}
	
	public static Map<Integer, Atividade> readAtividade(String path, Map<String, Periodo> periodos, Map<String, Diciplina> diciplinas) throws FileNotFoundException, ParseException{
		FileReader arq = new FileReader(path);
		BufferedReader lerarq = new BufferedReader(arq);
		Map<Integer, Atividade> atividades = new HashMap<>();
		try {
			lerarq.readLine();
			String linha = lerarq.readLine();
			while(linha!=null) {
				int seq=1;
				String dados[] = linha.split(";");
				if(dados[2].equals("A")) {
					seq+=atividades.size();
					atividades.put(seq, new Aula(dados[1], true, dados[3], dados[4]));
					diciplinas.get(dados[0]).atv.put(seq, atividades.get(seq));
					diciplinas.get(dados[0]).getDoc().atv.put(seq, atividades.get(seq));
				}
				else if(dados[2].equals("E")) {
					seq+=atividades.size();
					atividades.put(seq, new Estudo(dados[1], false, dados[5]));
					diciplinas.get(dados[0]).atv.put(seq, atividades.get(seq));
					diciplinas.get(dados[0]).getDoc().atv.put(seq, atividades.get(seq));
				}
				else if(dados[2].equals("T")) {
					seq+=atividades.size();
					atividades.put(seq, new Trabalho(dados[1], false, dados[3], Integer.parseInt(dados[6]), Double.parseDouble(dados[7])));
					diciplinas.get(dados[0]).atv.put(seq, atividades.get(seq));
					diciplinas.get(dados[0]).getDoc().atv.put(seq, atividades.get(seq));
				}
				else if(dados[2].equals("P")) {
					seq+=atividades.size();
					atividades.put(seq, new Prova(dados[0], true, dados[3], dados[4], dados[5]));
					diciplinas.get(dados[0]).atv.put(seq, atividades.get(seq));
					diciplinas.get(dados[0]).getDoc().atv.put(seq, atividades.get(seq));
				}
				linha = lerarq.readLine();
			}
			lerarq.close();
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo!");
		}
		return atividades;
	}
	
	public static void readAvaliacao(String path, Map<String, Diciplina> diciplinas) throws FileNotFoundException, ParseException{
		FileReader arq = new FileReader(path);
		BufferedReader lerarq = new BufferedReader(arq);
		try {
			lerarq.readLine();
			String linha = lerarq.readLine();
			while(linha!=null) {
				String dados[] = linha.split(";");
				Number number = nf.parse(dados[3]);
				diciplinas.get(dados[0]).atv.get(Integer.parseInt(dados[2])).avaliacao.put(Long.parseLong(dados[1]), number.doubleValue());
				linha = lerarq.readLine();
			}
			lerarq.close();
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo!");
		}
	}
	
	public static void writeGeral(Map<String, Periodo> periodos) throws IOException {
		FileWriter arq = new FileWriter("1-visao-geral.csv");
		PrintWriter escrevearq = new PrintWriter(arq);
		ArrayList<Periodo> peraux = new ArrayList<>();
		for(String s : periodos.keySet()) {
			peraux.add(periodos.get(s));
		}
		escrevearq.println("Período;Código Disciplina;Disciplina;Docente Responsável;E-mail Docente;Qtd. Estudantes;Qtd. Atividades");
		Collections.sort(peraux);
		for(int i=0;i<peraux.size();i++) {
			ArrayList<Diciplina> dicaux = new ArrayList<>();
			for(String s : peraux.get(i).dic.keySet()) {
				dicaux.add(peraux.get(i).dic.get(s));
			}
			Diciplina.sortNome(dicaux);
			for(int j=0;j<dicaux.size();j++) {
				escrevearq.println(peraux.get(i).getAnoSemestre()+";"+dicaux.get(j).getCodigo()+";"+dicaux.get(j).getNome()+";"+dicaux.get(j).getDoc().getNome()+";"+
						dicaux.get(j).getDoc().getLogin()+";"+dicaux.get(j).est.size()+";"+dicaux.get(j).atv.size());
			}
		}
		escrevearq.close();
	}
	
	public static void writeEstDocentes(Map<String, Docente> docentes) throws IOException {
		FileWriter arq = new FileWriter("2-docentes.csv");
		PrintWriter escrevearq = new PrintWriter(arq);
		ArrayList<Docente> docaux = new ArrayList<>();
		for(String s : docentes.keySet()) {
			docaux.add(docentes.get(s));
		}
		escrevearq.println("Docente;Qtd. Disciplinas;Qtd. Períodos;Média Atividades/Disciplina;% Síncronas;% Assíncronas;Média de Notas");
		Collections.sort(docaux);
		for(int i=0;i<docaux.size();i++) {
			escrevearq.printf("%s;%d;%d;%.1f;%.0f%%;", docaux.get(i).getNome(), docaux.get(i).dic.size(), docaux.get(i).contaPeriodos(), docaux.get(i).mediaAtividadesPorDiciplina(), docaux.get(i).percentualAtividadesSincronas());
			escrevearq.printf("%.0f%%;%.1f\n",docaux.get(i).percentualAtividadesAssincronas(), docaux.get(i).mediaNotasRecebidas());
		}
		
		escrevearq.close();
	}
	
	public static void writeEstEstudantes(Map<Long, Estudante> estudantes) throws IOException {
		FileWriter arq = new FileWriter("3-estudantes.csv");
		PrintWriter escrevearq = new PrintWriter(arq);
		ArrayList<Estudante> estaux = new ArrayList<>();
		for(Long i : estudantes.keySet()) {
			estaux.add(estudantes.get(i));
		}
		escrevearq.println("Matrícula;Nome;Média Disciplinas/Período;Média Avaliações/Disciplina;Média Notas Avaliações");	
		
		Collections.sort(estaux);
		
		for(int i=0;i<estaux.size();i++) {
			escrevearq.printf("%d;%s;%.1f;%.1f;%.1f\n", estaux.get(i).getMatricula(), estaux.get(i).getNome(), estaux.get(i).mediaDiciplinasPorPeriodo(), estaux.get(i).mediaAvaliacoes(), estaux.get(i).mediaNotas());
		}
		
		escrevearq.close();
	}
	
	public static void writeEstDisciplinasDocente(Map<String, Docente> docentes) throws IOException {
		FileWriter arq = new FileWriter("4-disciplinas.csv");
		PrintWriter escrevearq = new PrintWriter(arq);
		ArrayList<Docente> docaux = new ArrayList<>();
		for(String s : docentes.keySet()) {
			docaux.add(docentes.get(s));
		}
		escrevearq.println("Docente;Período;Código;Nome;Qtd. Atividades;% Síncronas;% Assíncronas;CH;Datas Avaliações");	
		
		Collections.sort(docaux);
		for(int i=0;i<docaux.size();i++) {
			List<Diciplina> dicaux = new ArrayList<>();
			for(String s : docaux.get(i).dic.keySet()) {
				dicaux.add(docaux.get(i).dic.get(s));
			}
			
			Collections.sort(dicaux);
			
			for(int j=0;j<dicaux.size();j++) {
				String datasf = new String();
				double choraria = 0;
				for(Integer s : dicaux.get(j).atv.keySet()) {
					ArrayList<Date> datas = new ArrayList<>();
					if(dicaux.get(j).atv.get(s).getClass() == Trabalho.class) {
						datas.add(((Trabalho) dicaux.get(j).atv.get(s)).getPrazo());
					}
					else if(dicaux.get(j).atv.get(s).getClass() == Prova.class) {
						datas.add(((Prova) dicaux.get(j).atv.get(s)).getData());
					}
					Arquivo.sortDatas(datas);
					for(int k=0;k<datas.size();k++) {
						
						datasf+=df.format(datas.get(k))+" ";
					}
					choraria+=dicaux.get(j).atv.get(s).getcHoraria();
				}
				escrevearq.printf("%s;%s;%s;%s;%d;%.0f%%;%.0f%%;%.0f;%s\n", docaux.get(i).getLogin(), dicaux.get(j).getPer().getAnoSemestre(), dicaux.get(j).getCodigo(), dicaux.get(j).getNome(), dicaux.get(j).atv.size(),
						dicaux.get(j).percentualAtividadeSincrona(), dicaux.get(j).percentualAtividadeAssincrona(), choraria, datasf);
			}
		}
		
		escrevearq.close();
	}
	
	public static void sortDatas(ArrayList<Date> d) {
		Date aux;
		for(int i=0;i<d.size();i++) {
			for(int j=i;j<d.size();j++) {
				if(d.get(i).compareTo(d.get(j)) > 0) {
					aux = d.get(i);
					d.set(i, d.get(j));
					d.set(j, aux);
				}
			}
		}
	}
	
	public static void leituraArgumentos(String[] args, Map<String, Periodo> periodos, Map<Long, Estudante> estudantes, Map<String, Docente> docentes, Map<String, Diciplina> diciplinas, Map<Integer, Atividade> atividades) {
		
	}
}

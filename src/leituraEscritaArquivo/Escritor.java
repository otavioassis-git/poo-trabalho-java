package leituraEscritaArquivo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import classesDasEntidades.Diciplina;
import classesDasEntidades.Docente;
import classesDasEntidades.Estudante;
import classesDasEntidades.Periodo;
import classesDasEntidades.atividades.Prova;
import classesDasEntidades.atividades.Trabalho;

public class Escritor {
	private static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public void writeGeral(Map<String, Periodo> periodos) throws IOException {
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
			for(String s : peraux.get(i).getDiciplinas().keySet()) {
				dicaux.add(peraux.get(i).getDiciplinas().get(s));
			}
			Diciplina.sortNome(dicaux);
			for(int j=0;j<dicaux.size();j++) {
				escrevearq.println(peraux.get(i).getAnoSemestre()+";"+dicaux.get(j).getCodigo()+";"+dicaux.get(j).getNome()+";"+dicaux.get(j).getDoc().getNome()+";"+
						dicaux.get(j).getDoc().getLogin()+"@ufes.br;"+dicaux.get(j).getEstudantes().size()+";"+dicaux.get(j).getAtividades().size());
			}
		}
		escrevearq.close();
	}
	
	public void writeEstDocentes(Map<String, Docente> docentes) throws IOException {
		FileWriter arq = new FileWriter("2-docentes.csv");
		PrintWriter escrevearq = new PrintWriter(arq);
		ArrayList<Docente> docaux = new ArrayList<>();
		for(String s : docentes.keySet()) {
			docaux.add(docentes.get(s));
		}
		escrevearq.println("Docente;Qtd. Disciplinas;Qtd. Períodos;Média Atividades/Disciplina;% Síncronas;% Assíncronas;Média de Notas");
		Collections.sort(docaux);
		for(int i=0;i<docaux.size();i++) {
			escrevearq.printf("%s;%d;%d;%.1f;%.0f%%;", docaux.get(i).getNome(), docaux.get(i).getDiciplinas().size(), docaux.get(i).contaPeriodos(), docaux.get(i).mediaAtividadesPorDiciplina(), docaux.get(i).percentualAtividadesSincronas());
			escrevearq.printf("%.0f%%;%.1f\n",docaux.get(i).percentualAtividadesAssincronas(), docaux.get(i).mediaNotasRecebidas());
		}
		escrevearq.close();
	}
	
	public void writeEstEstudantes(Map<Long, Estudante> estudantes) throws IOException {
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
	
	public void writeEstDisciplinasDocente(Map<String, Diciplina> diciplinas) throws IOException {
		FileWriter arq = new FileWriter("4-disciplinas.csv");
		PrintWriter escrevearq = new PrintWriter(arq);
		ArrayList<Diciplina> dicaux = new ArrayList<>();
		for(String s : diciplinas.keySet()) {
			dicaux.add(diciplinas.get(s));
		}
		escrevearq.println("Docente;Período;Código;Nome;Qtd. Atividades;% Síncronas;% Assíncronas;CH;Datas Avaliações");
		Collections.sort(dicaux);	
		for(int j=0;j<dicaux.size();j++) {
			String datasf = new String();
			double choraria = 0;
			ArrayList<Date> datas = new ArrayList<>();
			for(String s : dicaux.get(j).getAtividades().keySet()) {
				if(dicaux.get(j).getAtividades().get(s).getClass() == Trabalho.class) {
					datas.add(((Trabalho) dicaux.get(j).getAtividades().get(s)).getPrazo());
				}
				else if(dicaux.get(j).getAtividades().get(s).getClass() == Prova.class) {
					datas.add(((Prova) dicaux.get(j).getAtividades().get(s)).getData());
				}
				choraria+=dicaux.get(j).getAtividades().get(s).getcHoraria();
			}
			Collections.sort(datas);
			for(int k=0;k<datas.size();k++) {	
				datasf+=df.format(datas.get(k))+" ";
			}
			datasf = datasf.trim();
			escrevearq.printf("%s;%s;%s;%s;%d;%.0f%%;%.0f%%;%.0f;%s\n", dicaux.get(j).getDoc().getLogin(), dicaux.get(j).getPer().getAnoSemestre(), dicaux.get(j).getCodigo(), dicaux.get(j).getNome(), 
					dicaux.get(j).getAtividades().size(), dicaux.get(j).percentualAtividadeSincrona(), dicaux.get(j).percentualAtividadeAssincrona(), choraria, datasf);
		}
		
		escrevearq.close();
	}
}

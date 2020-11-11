package interfaceG;

import classesDasEntidades.*;
import classesDasEntidades.atividades.*;
import leituraEscritaArquivo.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.awt.event.ActionEvent;

import java.util.*;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JFormattedTextField;

public class TelaPrincipal extends JFrame {
	static Map<String, Periodo> periodos;
	static Map<Long, Estudante> estudantes;
	static Map<String, Docente> docentes;
	static Map<String, Diciplina> diciplinas;
	static Map<Integer, Atividade> atividades;
	
	private JPanel contentPane;
	private JTextField txt_estudante_nome;
	private JTextField txt_docente_nome;
	private JTextField txt_diciplina_nome_cad;
	private JTextField txt_diciplina_codigo_cad;
	private JTextField txt_atividades_nome;
	private JTextField txt_atividades_codigo;
	private JTextField txt_avaliacao_matricula;
	private JTextField txt_avaliacao_sequencial;
	private JTextField txt_avaliacao_codigo;
	private JTextField txt_docente_login;
	private JTextField txt_docente_pag;
	private JTextField txt_diciplina_login_cad;
	private JTextField txt_diciplina_codigo_mat;
	private JTextField txt_diciplina_matricula_mat;
	private JTextField txt_diciplinas_exibir;
	private JTextField txt_avaliacao_nota;
	private JTextField txt_relatorio_login;
	private JTextField txt_atividades_estudo_url;
	private JTextField txt_atividades_trabalho_numero;
	private JTextField txt_atividades_prova_conteudo;
	private JTextField txt_salvar_carregar;
	private JTextField txt_estudante_matricula;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Locale.setDefault(new Locale("pt", "BR"));
		boolean[] teste = new boolean[7];
		int iteste=0;
		int iteste2=0;
		for(int i=0;i<teste.length;i++) {
			teste[i]=false;
		}
		for(int i=0;i<args.length;i++) {
			if(args[i].equals("--read-only")){
				iteste=1;
			}
			if(args[i].equals("--write-only")){
				iteste=2;
			}
			if(args[i].equals("-p")) {
				teste[0] = true;
				iteste2=1;
			}
			if(args[i].equals("-d")) {
				teste[1] = true;
				iteste2=1;
			}
			if(args[i].equals("-o")) {
				teste[2] = true;
				iteste2=1;
			}
			if(args[i].equals("-e")) {
				teste[3] = true;
				iteste2=1;
			}
			if(args[i].equals("-m")) {
				teste[4] = true;
				iteste2=1;
			}
			if(args[i].equals("-a")) {
				teste[5] = true;
				iteste2=1;
			}
			if(args[i].equals("-n")) {
				teste[6] = true;
				iteste2=1;
			}
		}
		
		if(iteste == 1) {
			try {
				if(teste[0]) {
					for(int i=0;i<args.length;i++) {
						if(args[i].equals("-p")) {
							periodos = Arquivo.readPeriodo(args[i+1]);
						}
					}
				}
				if(teste[1]) {
					for(int i=0;i<args.length;i++) {
						if(args[i].equals("-d")) {
							docentes = Arquivo.readDocente(args[i+1]);
						}
					}
				}
				if(teste[2]) {
					for(int i=0;i<args.length;i++) {
						if(args[i].equals("-o")) {
							diciplinas = Arquivo.readDiciplina(args[i+1], periodos, docentes);
						}
					}
				}
				if(teste[3]) {
					for(int i=0;i<args.length;i++) {
						if(args[i].equals("-e")) {
							estudantes = Arquivo.readEstudante(args[i+1]);
						}
					}
				}
				if(teste[4]) {
					for(int i=0;i<args.length;i++) {
						if(args[i].equals("-m")) {
							Arquivo.readMatriculas(args[i+1], diciplinas, estudantes);
						}
					}
				}
				if(teste[5]) {
					for(int i=0;i<args.length;i++) {
						if(args[i].equals("-a")) {
							atividades = Arquivo.readAtividade(args[i+1], periodos, diciplinas);
						}
					}
				}
				if(teste[6]) {
					for(int i=0;i<args.length;i++) {
						if(args[i].equals("-n")) {
							Arquivo.readAvaliacao(args[i+1], diciplinas);
						}
					}
				}
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("dados.dat"));
				out.writeObject(periodos);
				out.writeObject(estudantes);
				out.writeObject(diciplinas);
				out.writeObject(docentes);
				out.writeObject(atividades);
				out.close();
			}
			catch(IllegalArgumentException e) {
				//e.printStackTrace();
				System.out.println(e.getMessage());
			}
			catch(IOException e) {
				//e.printStackTrace();
				System.out.println("Erro de I/O.");
			}
			finally {
				System.exit(0);
			}
		}
		else if(iteste == 2){
			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("dados.dat"))) {
				periodos = (Map<String, Periodo>)in.readObject();
				estudantes = (Map<Long, Estudante>)in.readObject();
				diciplinas = (Map<String, Diciplina>)in.readObject();
				docentes = (Map<String, Docente>)in.readObject();
				atividades = (Map<Integer, Atividade>)in.readObject();
			
				Arquivo.writeGeral(periodos);
				Arquivo.writeEstEstudantes(estudantes);
				Arquivo.writeEstDocentes(docentes);
				Arquivo.writeEstDisciplinasDocente(diciplinas);

			}
			catch(IOException | ClassNotFoundException e) {
				System.out.println("Erro de I/O.");
			}
			finally {
				System.exit(0);
			}
		}
		else if(iteste == 0 && iteste2 == 1) {
			try {
				if(teste[0]) {
					for(int i=0;i<args.length;i++) {
						if(args[i].equals("-p")) {
							periodos = Arquivo.readPeriodo(args[i+1]);
						}
					}
				}
				if(teste[1]) {
					for(int i=0;i<args.length;i++) {
						if(args[i].equals("-d")) {
							docentes = Arquivo.readDocente(args[i+1]);
						}
					}
				}
				if(teste[2]) {
					for(int i=0;i<args.length;i++) {
						if(args[i].equals("-o")) {
							diciplinas = Arquivo.readDiciplina(args[i+1], periodos, docentes);
						}
					}
				}
				if(teste[3]) {
					for(int i=0;i<args.length;i++) {
						if(args[i].equals("-e")) {
							estudantes = Arquivo.readEstudante(args[i+1]);
						}
					}
				}
				if(teste[4]) {
					for(int i=0;i<args.length;i++) {
						if(args[i].equals("-m")) {
							Arquivo.readMatriculas(args[i+1], diciplinas, estudantes);
						}
					}
				}
				if(teste[5]) {
					for(int i=0;i<args.length;i++) {
						if(args[i].equals("-a")) {
							atividades = Arquivo.readAtividade(args[i+1], periodos, diciplinas);
						}
					}
				}
				if(teste[6]) {
					for(int i=0;i<args.length;i++) {
						if(args[i].equals("-n")) {
							Arquivo.readAvaliacao(args[i+1], diciplinas);
						}
					}
				}
				
				Arquivo.writeGeral(periodos);
				Arquivo.writeEstEstudantes(estudantes);
				Arquivo.writeEstDocentes(docentes);
				Arquivo.writeEstDisciplinasDocente(diciplinas);
			}
			catch(IllegalArgumentException e) {
				//e.printStackTrace();
				System.out.println(e.getMessage());
			}
			catch(IOException e) {
				//e.printStackTrace();
				System.out.println("Erro de I/O.");
			}
			finally{
				System.exit(0);
			}
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaPrincipal() throws ParseException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 573, 366);
		contentPane.add(tabbedPane);
		
		//INICIO DA ABA DE PERIODOS--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Periodo", null, panel_5, null);
		panel_5.setLayout(null);
		
		JFormattedTextField txt_periodo_cadastro = new JFormattedTextField(new MaskFormatter("####/A"));
		txt_periodo_cadastro.setToolTipText("");
		txt_periodo_cadastro.setBounds(119, 36, 206, 20);
		panel_5.add(txt_periodo_cadastro);
		
		JLabel lblNewLabel_22 = new JLabel("Ano / Semestre");
		lblNewLabel_22.setBounds(10, 39, 194, 14);
		panel_5.add(lblNewLabel_22);
		
		JLabel lblNewLabel_24 = new JLabel("Cadastro de Periodo");
		lblNewLabel_24.setBounds(156, 11, 278, 14);
		panel_5.add(lblNewLabel_24);
		
		//IMPLEMENTACAO DO BOTAO CADASTRAR PERIODO --------------------------------------------------------------------------------------------------------------
		periodos = new HashMap<>();
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) throws IllegalArgumentException{
				String conteudo = txt_periodo_cadastro.getText();
				char[]  c = conteudo.toCharArray();
				
				try {
					if(c[5] != 'E' & c[5] != '1' & c[5] != '2') {
						JOptionPane.showMessageDialog(rootPane, "Valor invalido de semestre inserido!\nLeia o terminal para mais info", "Erro", JOptionPane.ERROR_MESSAGE);
						throw new IllegalArgumentException("Para semestres eh aceito apenas 1, 2 ou E");
					}
					else {
						if(periodos.containsKey(conteudo)) {
							JOptionPane.showMessageDialog(rootPane, "Periodo ja cadastrado!\nLeia o terminal para mais info", "Erro", JOptionPane.ERROR_MESSAGE);
							throw new IllegalArgumentException("Periodo ja cadastrado!");
						}
						else {
							periodos.put(conteudo, new Periodo(conteudo));
							JOptionPane.showMessageDialog(rootPane, "Periodo cadastrado");
						}
					}
				}
				catch(IllegalArgumentException e) {
					System.out.println("ERRO: Periodo ja cadastrado (clicar em exibir periodos para visualizar os cadastrados) ou com semestre invalido (validos: 1, 2 ou E)\n");
				}
				finally {
					txt_periodo_cadastro.setText("");
				}
				
			}
			
		});
		btnNewButton.setBounds(160, 67, 123, 23);
		panel_5.add(btnNewButton);
		//--------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//IMPLEMENTACAO DO BOTAO EXIBIR PERIODOS -----------------------------------------------------------------------------------------------------------------
		JButton btnNewButton_4 = new JButton("Exibe Periodos");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Periodos:");
				for(String s : periodos.keySet()) {
					System.out.println(s);
				}
				System.out.println();
			}
		});
		btnNewButton_4.setBounds(331, 289, 148, 23);
		panel_5.add(btnNewButton_4);
		//--------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//INICIO DA ABA DE DOCENTES -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Docente", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Cadastrar novo docente");
		lblNewLabel_3.setBounds(183, 11, 261, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Nome");
		lblNewLabel_4.setBounds(10, 36, 63, 14);
		panel_1.add(lblNewLabel_4);
		
		//txt.put("txt_docente_nome", new MontaTextField(panel_1, 10, 106, 36, 241, 20));
		
		txt_docente_nome = new JTextField();
		txt_docente_nome.setBounds(106, 36, 241, 20);
		panel_1.add(txt_docente_nome);
		txt_docente_nome.setColumns(10);
		
		JLabel lblNewLabel_25 = new JLabel("Login");
		lblNewLabel_25.setBounds(10, 66, 46, 14);
		panel_1.add(lblNewLabel_25);
		
		txt_docente_login = new JTextField();
		txt_docente_login.setBounds(106, 63, 241, 20);
		panel_1.add(txt_docente_login);
		txt_docente_login.setColumns(10);
		
		JLabel lblNewLabel_26 = new JLabel("Pagina da Web");
		lblNewLabel_26.setBounds(10, 91, 127, 14);
		panel_1.add(lblNewLabel_26);
		
		txt_docente_pag = new JTextField();
		txt_docente_pag.setText("(nao obrigatorio)");
		txt_docente_pag.setToolTipText("");
		txt_docente_pag.setBounds(127, 88, 221, 20);
		panel_1.add(txt_docente_pag);
		txt_docente_pag.setColumns(10);
		
		//BOTAO PARA CADASTRAR DOCENTES ------------------------------------------------------------------------------------------------------------------------
		docentes = new HashMap<>();
		
		JButton btnNewButton_8 = new JButton("Cadastrar");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) throws IllegalArgumentException {
				String login = txt_docente_login.getText();
				
				try {
					if(docentes.containsKey(login)) {
						JOptionPane.showMessageDialog(rootPane, "Docente ja cadastrado!\nLeia o terminal para mais info", "Erro", JOptionPane.ERROR_MESSAGE);
						throw new IllegalArgumentException("Docente ja cadastrado!");
					}
					else {
						docentes.put(login, new Docente(login, txt_docente_nome.getText(),  txt_docente_pag.getText()));
						JOptionPane.showMessageDialog(rootPane, "Docente cadastrado");
					}
				}
				catch(IllegalArgumentException e) {
					System.out.println("Erro: Docente ja cadastrado (clique em exibir docentes para visualizar os cadastrados)\n");
				}
				finally {
					txt_docente_nome.setText("");
					txt_docente_login.setText("");
					txt_docente_pag.setText("(nao obrigatorio)");
				}
				
				
			}
		});
		btnNewButton_8.setBounds(389, 62, 107, 23);
		panel_1.add(btnNewButton_8);
		//-------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//BOTAO PARA EXIBIR DOCENTES ----------------------------------------------------------------------------------------------------------------------------
		JButton btnNewButton_9 = new JButton("Exibe Docentes");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Docentes: ");
				for(String s : docentes.keySet()) {
					System.out.print(docentes.get(s).getNome()+" | "+s);
					if(!docentes.get(s).getPagweb().equals("(nao obrigatorio)")) {
						System.out.print(" | "+docentes.get(s).getPagweb());
					}
					System.out.println();
				}
				System.out.println();
			}
		});
		btnNewButton_9.setBounds(340, 286, 156, 23);
		panel_1.add(btnNewButton_9);
		//--------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//INICIO DA ABA DE DICIPLINAS -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Disciplinas", null, panel_2, null);
		panel_2.setLayout(null);
		
		JFormattedTextField txt_diciplinas_periodo = new JFormattedTextField(new MaskFormatter("####/A"));
		txt_diciplinas_periodo.setBounds(139, 119, 227, 20);
		panel_2.add(txt_diciplinas_periodo);
		
		JLabel lblNewLabel_5 = new JLabel("Cadastrar nova disciplina");
		lblNewLabel_5.setBounds(176, 11, 300, 14);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Nome");
		lblNewLabel_6.setBounds(10, 36, 56, 14);
		panel_2.add(lblNewLabel_6);
		
		txt_diciplina_nome_cad = new JTextField();
		txt_diciplina_nome_cad.setBounds(139, 36, 227, 20);
		panel_2.add(txt_diciplina_nome_cad);
		txt_diciplina_nome_cad.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Codigo");
		lblNewLabel_7.setBounds(10, 67, 56, 14);
		panel_2.add(lblNewLabel_7);
		
		txt_diciplina_codigo_cad = new JTextField();
		txt_diciplina_codigo_cad.setBounds(139, 64, 227, 20);
		panel_2.add(txt_diciplina_codigo_cad);
		txt_diciplina_codigo_cad.setColumns(10);
		
		JLabel lblNewLabel_7_1 = new JLabel("Login docente responsavel");
		lblNewLabel_7_1.setBounds(10, 94, 205, 14);
		panel_2.add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_7_2 = new JLabel("Periodo");
		lblNewLabel_7_2.setBounds(10, 120, 56, 14);
		panel_2.add(lblNewLabel_7_2);
		
		txt_diciplina_login_cad = new JTextField();
		txt_diciplina_login_cad.setBounds(225, 91, 141, 20);
		panel_2.add(txt_diciplina_login_cad);
		txt_diciplina_login_cad.setColumns(10);
		
		//BOTAO PARA CADASTRAR DISCIPLINAS -----------------------------------------------------------------------------------------------------------------------
		diciplinas = new HashMap<>();
		
		JButton btnNewButton_1 = new JButton("Cadastrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) throws  IllegalArgumentException {
				String periodo = txt_diciplinas_periodo.getText();
				String codigo = txt_diciplina_codigo_cad.getText();
				String login = txt_diciplina_login_cad.getText();
				
				try {
					if(diciplinas.containsKey(codigo)) {
						JOptionPane.showMessageDialog(rootPane, "Disciplina ja cadastrada!\nLeia o terminal para mais info", "Erro", JOptionPane.ERROR_MESSAGE);
						throw new IllegalArgumentException("Disciplina ja cadastrada!");
					}
					else if(!docentes.containsKey(login)) {
						JOptionPane.showMessageDialog(rootPane, "Docente inexistente!\nLeia o terminal para mais info", "Erro", JOptionPane.ERROR_MESSAGE);
						throw new IllegalArgumentException("Docente inexistente!");
					}
					else if(!periodos.containsKey(periodo)) {
						JOptionPane.showMessageDialog(rootPane, "Periodo inexistente!\nLeia o terminal para mais info", "Erro", JOptionPane.ERROR_MESSAGE);
						throw new IllegalArgumentException("Periodo inexistente!");
					}
					else {
						diciplinas.put(codigo, new Diciplina(txt_diciplina_nome_cad.getText(), codigo, docentes.get(login), periodos.get(periodo)));
						docentes.get(login).getDiciplinas().put(codigo, diciplinas.get(codigo));
						periodos.get(periodo).getDiciplinas().put(codigo, diciplinas.get(codigo));
						
						JOptionPane.showMessageDialog(rootPane, "Diciplina cadastrada");
					}
				}
				catch(IllegalArgumentException e) {
					System.out.println("Erro: Disciplina ja cadastrada (clique em exibir disciplinas para visualizar as cadastradas) ou Docente nao cadastrado ou Periodo nao cadastrado\n");
				}
				finally {
					txt_diciplina_nome_cad.setText("");
					txt_diciplina_codigo_cad.setText("");
					txt_diciplina_login_cad.setText("");
				}
			}
		});
		btnNewButton_1.setBounds(406, 63, 107, 50);
		panel_2.add(btnNewButton_1);
		//--------------------------------------------------------------------------------------------------------------------------------------------------------
		
		JLabel lblNewLabel_8 = new JLabel("Ano / Semestre");
		lblNewLabel_8.setBounds(163, 139, 207, 14);
		panel_2.add(lblNewLabel_8);
		
		JLabel lblNewLabel_5_1 = new JLabel("Cadastrar estudante");
		lblNewLabel_5_1.setBounds(176, 176, 300, 14);
		panel_2.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_6_1 = new JLabel("Codigo Diciplina");
		lblNewLabel_6_1.setBounds(10, 202, 157, 14);
		panel_2.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_10 = new JLabel("Matricula Estudante");
		lblNewLabel_10.setBounds(10, 227, 157, 14);
		panel_2.add(lblNewLabel_10);
		
		txt_diciplina_codigo_mat = new JTextField();
		txt_diciplina_codigo_mat.setBounds(176, 201, 145, 20);
		panel_2.add(txt_diciplina_codigo_mat);
		txt_diciplina_codigo_mat.setColumns(10);
		
		txt_diciplina_matricula_mat = new JTextField();
		txt_diciplina_matricula_mat.setBounds(176, 224, 145, 20);
		panel_2.add(txt_diciplina_matricula_mat);
		txt_diciplina_matricula_mat.setColumns(10);
		
		//BOTAO PARA MATRICULAR ALUNO NA DISCIPLINA --------------------------------------------------------------------------------------------------------------
		JButton btnNewButton_3 = new JButton("Matricular");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) throws IllegalArgumentException{
				long matricula = Long.parseLong(txt_diciplina_matricula_mat.getText());
				String codigo = txt_diciplina_codigo_mat.getText();
				
				try {
					if(!estudantes.containsKey(matricula)) {
						JOptionPane.showMessageDialog(rootPane, "Estudante inexistente!\nLeia o terminal para mais info", "Erro", JOptionPane.ERROR_MESSAGE);
						throw new IllegalArgumentException("Estudante inexistente!");
					}
					else if(!diciplinas.containsKey(codigo)) {
						JOptionPane.showMessageDialog(rootPane, "Disciplina inexistente!\nLeia o terminal para mais info", "Erro", JOptionPane.ERROR_MESSAGE);
						throw new IllegalArgumentException("Disciplina inexistente!");
					}
					else if(diciplinas.get(codigo).getEstudantes().containsKey(matricula) || estudantes.get(matricula).getDiciplinas().containsKey(codigo)) {
						JOptionPane.showMessageDialog(rootPane, "Estudante ja matriculado na disciplina!\nLeia o terminal para mais info", "Erro", JOptionPane.ERROR_MESSAGE);
						throw new IllegalArgumentException("Estudante ja matriculado!");
					}
					else {
						diciplinas.get(codigo).getEstudantes().put(matricula, estudantes.get(matricula));
						estudantes.get(matricula).getDiciplinas().put(codigo, diciplinas.get(codigo));
						JOptionPane.showMessageDialog(rootPane, "Estudante Matriculado");
					}
				}
				catch(IllegalArgumentException e) {
					System.out.println("Erro: Estudante ja matriculado na disciplina (para verificar estudantes ja cadastrados use sessao verificar estudantes matriculados) ou"
							+ "Estudante inexistente ou Disciplina inexistente");
				}
				finally {
					txt_diciplina_matricula_mat.setText("");
				}
			}
		});
		btnNewButton_3.setBounds(349, 223, 107, 23);
		panel_2.add(btnNewButton_3);
		//--------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//BOTAO PARA EXIBIR DISCIPLINAS---------------------------------------------------------------------------------------------------------------------------
		JButton btnNewButton_10 = new JButton("Exibe Diciplinas");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Diciplinas: ");
				for(String s : diciplinas.keySet()) {
					System.out.println(s+" "+diciplinas.get(s).getNome()+" - "+diciplinas.get(s).getPer().getAnoSemestre()+" | Responsavel: "+diciplinas.get(s).getDoc().getNome());
				}
				System.out.println();
			}
		});
		btnNewButton_10.setBounds(376, 135, 164, 23);
		panel_2.add(btnNewButton_10);
		//--------------------------------------------------------------------------------------------------------------------------------------------------------
		
		JLabel lblNewLabel_5_2 = new JLabel("Verificar Estudantes Matriculados");
		lblNewLabel_5_2.setBounds(176, 269, 300, 14);
		panel_2.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_11 = new JLabel("Codigo da Diciplina");
		lblNewLabel_11.setBounds(10, 297, 146, 14);
		panel_2.add(lblNewLabel_11);
		
		txt_diciplinas_exibir = new JTextField();
		txt_diciplinas_exibir.setBounds(176, 294, 145, 20);
		panel_2.add(txt_diciplinas_exibir);
		txt_diciplinas_exibir.setColumns(10);
		
		//BOTAO PARA EXIBIR ALUNOS MATRICULADOS NA DISCIPLINA ----------------------------------------------------------------------------------------------------
		JButton btnNewButton_12 = new JButton("Exibe");
		btnNewButton_12.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)  throws IllegalArgumentException{
				String codigo = txt_diciplinas_exibir.getText();
				
				try {
					if(!diciplinas.containsKey(codigo)) {
						JOptionPane.showMessageDialog(rootPane, "Disciplina inexistente!", "Erro", JOptionPane.ERROR_MESSAGE);
						throw new IllegalArgumentException("Disciplina inexistente!");
					}
					else {
						System.out.println("Estudantes matriculados em "+codigo+" "+diciplinas.get(codigo).getNome());
						for (Long i : diciplinas.get(codigo).getEstudantes().keySet()) {
							System.out.println(i+" "+diciplinas.get(codigo).getEstudantes().get(i).getNome());
						}
						System.out.println();
					}
				}
				catch(IllegalArgumentException e) {
					System.out.print("Erro: disciplina informada inexistente!");
				}
				finally {
					txt_diciplinas_exibir.setText("");
				}
			}
		});
		btnNewButton_12.setBounds(349, 293, 100, 23);
		panel_2.add(btnNewButton_12);
		//--------------------------------------------------------------------------------------------------------------------------------------------------------
		
		
		//INICIO DA ABA DE ESTUDANTES -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		JPanel panel = new JPanel();
		tabbedPane.addTab("Estudantes", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastrar novo estudante");
		lblNewLabel.setBounds(50, 24, 204, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(10, 49, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Matricula");
		lblNewLabel_2.setBounds(10, 87, 67, 14);
		panel.add(lblNewLabel_2);
		
		txt_estudante_nome = new JTextField();
		txt_estudante_nome.setBounds(80, 50, 204, 20);
		panel.add(txt_estudante_nome);
		txt_estudante_nome.setColumns(10);
		
		//BOTAO PARA CADASTRAR ESTUDANTE -------------------------------------------------------------------------------------------------------------------------
		estudantes = new HashMap<>();
		
		JButton btnNewButton_2 = new JButton("Cadastrar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) throws IllegalArgumentException{
				try {
					long matricula = Long.parseLong(txt_estudante_matricula.getText());
					if(estudantes.containsKey(matricula)) {
						JOptionPane.showMessageDialog(rootPane, "Estudante ja cadastrado!\nLeia o terminal para mais info", "Erro", JOptionPane.ERROR_MESSAGE);
						throw new IllegalArgumentException("Estudante ja cadastrado!");
					}
					else {
						estudantes.put(matricula, new Estudante(matricula,txt_estudante_nome.getText()));
						JOptionPane.showMessageDialog(rootPane, "Estudante Cadastrado");
					}
				}
				catch(IllegalArgumentException e) {
					System.out.println("Erro: Estudante ja cadastrado (clique em exibir estudantes para visualizar estudantes cadastrados) ou Dados inseridos invalidos (para matricula"
							+ " eh aceito apenas numeros!)");
				}
				finally {
					txt_estudante_nome.setText("");
					txt_estudante_matricula.setText("");
				}
			}
		});
		btnNewButton_2.setBounds(324, 63, 106, 23);
		panel.add(btnNewButton_2);
		//--------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//BOTAO PARA EXIBIR ESTUDANTES ---------------------------------------------------------------------------------------------------------------------------
		JButton btnNewButton_11 = new JButton("Exibe Estudantes");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Estudantes: ");
				for(Long i : estudantes.keySet()) {
					System.out.println(i+" "+estudantes.get(i).getNome());
				}
				System.out.println();
			}
		});
		btnNewButton_11.setBounds(347, 280, 132, 23);
		panel.add(btnNewButton_11);
		
		txt_estudante_matricula = new JTextField();
		txt_estudante_matricula.setBounds(80, 84, 204, 20);
		panel.add(txt_estudante_matricula);
		txt_estudante_matricula.setColumns(10);
		//--------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//INICIO DA ABA DE ATIVIDADES -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		JPanel Atividades = new JPanel();
		tabbedPane.addTab("Atividades", null, Atividades, null);
		Atividades.setLayout(null);
		
		JRadioButton rb_aula = new JRadioButton("Aula");
		rb_aula.setSelected(true);
		rb_aula.setBounds(10, 99, 109, 23);
		Atividades.add(rb_aula);
		
		JRadioButton rb_estudo = new JRadioButton("Estudo");
		rb_estudo.setBounds(10, 136, 109, 23);
		Atividades.add(rb_estudo);
		
		JRadioButton rb_trabalho = new JRadioButton("Trabalho");
		rb_trabalho.setBounds(10, 184, 109, 23);
		Atividades.add(rb_trabalho);
		
		JRadioButton rb_prova = new JRadioButton("Prova");
		rb_prova.setBounds(10, 221, 109, 23);
		Atividades.add(rb_prova);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb_aula);
		bg.add(rb_trabalho);
		bg.add(rb_estudo);
		bg.add(rb_prova);
		
		JLabel lblNewLabel_13 = new JLabel("Cadastrar nova atividade");
		lblNewLabel_13.setBounds(206, 11, 202, 14);
		Atividades.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Nome");
		lblNewLabel_14.setBounds(41, 36, 120, 14);
		Atividades.add(lblNewLabel_14);
		
		txt_atividades_nome = new JTextField();
		txt_atividades_nome.setBounds(140, 33, 263, 20);
		Atividades.add(txt_atividades_nome);
		txt_atividades_nome.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("Codigo da disciplina");
		lblNewLabel_15.setBounds(10, 67, 120, 14);
		Atividades.add(lblNewLabel_15);
		
		txt_atividades_codigo = new JTextField();
		txt_atividades_codigo.setBounds(140, 64, 263, 20);
		Atividades.add(txt_atividades_codigo);
		txt_atividades_codigo.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel((String) null);
		lblNewLabel_12.setBounds(41, 219, 46, 14);
		Atividades.add(lblNewLabel_12);
		
		JFormattedTextField txt_atividades_aula_data = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txt_atividades_aula_data.setBounds(119, 100, 77, 20);
		Atividades.add(txt_atividades_aula_data);
		
		JFormattedTextField txt_atividades_aula_hora = new JFormattedTextField(new MaskFormatter("##:##"));
		txt_atividades_aula_hora.setBounds(218, 100, 77, 20);
		Atividades.add(txt_atividades_aula_hora);
		
		JLabel lblNewLabel_9 = new JLabel("Data");
		lblNewLabel_9.setBounds(140, 119, 46, 14);
		Atividades.add(lblNewLabel_9);
		
		JLabel lblNewLabel_23 = new JLabel("Hora");
		lblNewLabel_23.setBounds(238, 119, 46, 14);
		Atividades.add(lblNewLabel_23);
		
		txt_atividades_estudo_url = new JTextField();
		txt_atividades_estudo_url.setBounds(121, 137, 252, 20);
		Atividades.add(txt_atividades_estudo_url);
		txt_atividades_estudo_url.setColumns(10);
		
		JLabel lblNewLabel_29 = new JLabel("Url da pagina da atividade");
		lblNewLabel_29.setBounds(158, 160, 225, 14);
		Atividades.add(lblNewLabel_29);
		
		JFormattedTextField txt_atividades_trabalho_prazo = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txt_atividades_trabalho_prazo.setBounds(119, 185, 77, 20);
		Atividades.add(txt_atividades_trabalho_prazo);
		
		JLabel lblNewLabel_30 = new JLabel("Prazo");
		lblNewLabel_30.setBounds(140, 202, 46, 14);
		Atividades.add(lblNewLabel_30);
		
		txt_atividades_trabalho_numero = new JTextField();
		txt_atividades_trabalho_numero.setBounds(304, 184, 51, 20);
		Atividades.add(txt_atividades_trabalho_numero);
		txt_atividades_trabalho_numero.setColumns(10);
		
		JLabel lblNewLabel_31 = new JLabel("N. de pessoas/grupo");
		lblNewLabel_31.setBounds(365, 187, 161, 14);
		Atividades.add(lblNewLabel_31);
		
		JFormattedTextField txt_atividades_trabalho_ch = new JFormattedTextField();
		txt_atividades_trabalho_ch.setBounds(206, 185, 89, 20);
		Atividades.add(txt_atividades_trabalho_ch);
		
		JLabel lblNewLabel_32 = new JLabel("Carga Horaria");
		lblNewLabel_32.setBounds(206, 202, 130, 14);
		Atividades.add(lblNewLabel_32);
		
		JFormattedTextField txt_atividades_provas_data = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txt_atividades_provas_data.setBounds(119, 221, 77, 20);
		Atividades.add(txt_atividades_provas_data);
		
		JLabel lblNewLabel_9_1 = new JLabel("Data");
		lblNewLabel_9_1.setBounds(140, 240, 46, 14);
		Atividades.add(lblNewLabel_9_1);
		
		JFormattedTextField txt_atividades_provas_hora = new JFormattedTextField(new MaskFormatter("##:##"));
		txt_atividades_provas_hora.setBounds(218, 221, 77, 20);
		Atividades.add(txt_atividades_provas_hora);
		
		JLabel lblNewLabel_23_1 = new JLabel("Hora");
		lblNewLabel_23_1.setBounds(238, 240, 46, 14);
		Atividades.add(lblNewLabel_23_1);
		
		//BOTAO PARA CADASTRAR ATIVIDADES ------------------------------------------------------------------------------------------------------------------------
		atividades = new HashMap<>();
		
		JButton btnNewButton_5 = new JButton("Cadastrar");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) throws IllegalArgumentException {
				int seq=1;
				String cod = txt_atividades_codigo.getText();
				seq+=atividades.size();
				
				try {
					if(!diciplinas.containsKey(cod)) {
						JOptionPane.showMessageDialog(rootPane, "Disciplina inexistente!", "Erro", JOptionPane.ERROR_MESSAGE);
						throw new IllegalArgumentException("Disciplina inexistente!");
					}
					else if(rb_aula.isSelected()) {
						atividades.put(seq, new Aula(txt_atividades_nome.getText(), true, txt_atividades_aula_data.getText(), txt_atividades_aula_hora.getText()));
						diciplinas.get(cod).getAtividades().put(seq, atividades.get(seq));
						diciplinas.get(cod).getDoc().getAtividades().put(seq, atividades.get(seq));
						JOptionPane.showMessageDialog(rootPane, "Atividade Cadastrada");
					}else if(rb_estudo.isSelected()) {
						atividades.put(seq, new Estudo(txt_atividades_nome.getText(), false, txt_atividades_estudo_url.getText()));
						diciplinas.get(cod).getAtividades().put(seq, atividades.get(seq));
						diciplinas.get(cod).getDoc().getAtividades().put(seq, atividades.get(seq));
						JOptionPane.showMessageDialog(rootPane, "Atividade Cadastrada");
					}else if(rb_trabalho.isSelected()) {
						atividades.put(seq, new Trabalho(txt_atividades_nome.getText(), false, txt_atividades_trabalho_prazo.getText(), Integer.parseInt(txt_atividades_trabalho_numero.getText()), 
								Double.parseDouble(txt_atividades_trabalho_ch.getText())));
						diciplinas.get(cod).getAtividades().put(seq, atividades.get(seq));
						diciplinas.get(cod).getDoc().getAtividades().put(seq, atividades.get(seq));
						JOptionPane.showMessageDialog(rootPane, "Atividade Cadastrada");
					}else if(rb_prova.isSelected()) {
						atividades.put(seq, new Prova(txt_atividades_nome.getText(), true, txt_atividades_provas_data.getText(), txt_atividades_provas_hora.getText(), txt_atividades_prova_conteudo.getText()));
						diciplinas.get(cod).getAtividades().put(seq, atividades.get(seq));
						diciplinas.get(cod).getDoc().getAtividades().put(seq, atividades.get(seq));
						JOptionPane.showMessageDialog(rootPane, "Atividade Cadastrada");
					}
				} 
				finally {
					txt_atividades_nome.setText("");
					txt_atividades_aula_data.setText("");
					txt_atividades_aula_hora.setText("");
					txt_atividades_estudo_url.setText("");
					txt_atividades_trabalho_prazo.setText("");
					txt_atividades_trabalho_numero.setText("");
					txt_atividades_trabalho_ch.setText("");
					txt_atividades_provas_data.setText("");
					txt_atividades_provas_hora.setText("");
					txt_atividades_prova_conteudo.setText("");
				}				
			}
		});
		btnNewButton_5.setBounds(438, 221, 120, 23);
		Atividades.add(btnNewButton_5);
		//--------------------------------------------------------------------------------------------------------------------------------------------------------
		
		txt_atividades_prova_conteudo = new JTextField();
		txt_atividades_prova_conteudo.setBounds(303, 222, 86, 20);
		Atividades.add(txt_atividades_prova_conteudo);
		txt_atividades_prova_conteudo.setColumns(10);
		
		JLabel lblNewLabel_33 = new JLabel("Conteudo");
		lblNewLabel_33.setBounds(313, 240, 90, 14);
		Atividades.add(lblNewLabel_33);
		
		//INICIO DA ABA DE AVALIACAO ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Avaliacao", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_17 = new JLabel("Submeter avaliacao do aluno");
		lblNewLabel_17.setBounds(178, 11, 324, 14);
		panel_4.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("Matricula do aluno");
		lblNewLabel_18.setBounds(10, 33, 194, 14);
		panel_4.add(lblNewLabel_18);
		
		txt_avaliacao_matricula = new JTextField();
		txt_avaliacao_matricula.setBounds(213, 30, 86, 20);
		panel_4.add(txt_avaliacao_matricula);
		txt_avaliacao_matricula.setColumns(10);
		
		JLabel lblNewLabel_20 = new JLabel("Numero sequencial da atividade");
		lblNewLabel_20.setBounds(10, 58, 194, 14);
		panel_4.add(lblNewLabel_20);
		
		txt_avaliacao_sequencial = new JTextField();
		txt_avaliacao_sequencial.setBounds(213, 55, 86, 20);
		panel_4.add(txt_avaliacao_sequencial);
		txt_avaliacao_sequencial.setColumns(10);
		
		txt_avaliacao_codigo = new JTextField();
		txt_avaliacao_codigo.setBounds(213, 80, 86, 20);
		panel_4.add(txt_avaliacao_codigo);
		txt_avaliacao_codigo.setColumns(10);
		
		JLabel lblNewLabel_21 = new JLabel("Nota");
		lblNewLabel_21.setBounds(10, 108, 46, 14);
		panel_4.add(lblNewLabel_21);
		
		txt_avaliacao_nota = new JTextField();
		txt_avaliacao_nota.setBounds(213, 105, 86, 20);
		panel_4.add(txt_avaliacao_nota);
		txt_avaliacao_nota.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Codigo da Diciplina");
		lblNewLabel_16.setBounds(10, 83, 183, 14);
		panel_4.add(lblNewLabel_16);
		
		//BOTAO PARA SUBMETER AVALIACAO --------------------------------------------------------------------------------------------------------------------------
		JButton btnNewButton_6 = new JButton("Submeter");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) throws IllegalArgumentException {
				long mat = Long.parseLong(txt_avaliacao_matricula.getText());
				int seq = Integer.parseInt(txt_avaliacao_sequencial.getText());
				String cod = txt_avaliacao_codigo.getText();
				
				try {
					if(!diciplinas.containsKey(cod)) {
						JOptionPane.showMessageDialog(rootPane, "Disciplina inexistente!", "Erro", JOptionPane.ERROR_MESSAGE);
						throw new IllegalArgumentException("Disciplina inexistente!");
					}
					else if(!estudantes.containsKey(mat)) {
						JOptionPane.showMessageDialog(rootPane, "Estudante inexistente!", "Erro", JOptionPane.ERROR_MESSAGE);
						throw new IllegalArgumentException("Estudante inexistente!");
					}
					else if(!diciplinas.get(cod).getAtividades().containsKey(seq)) {
						JOptionPane.showMessageDialog(rootPane, "Disciplina nao contem atividade com sequencial digitado!", "Erro", JOptionPane.ERROR_MESSAGE);
						throw new IllegalArgumentException("Disciplina nao contem atividade com sequencial digitado!");
					}
					else {
						diciplinas.get(cod).getAtividades().get(seq).getAvaliacao().put(mat, Double.parseDouble(txt_avaliacao_nota.getText()));
						JOptionPane.showMessageDialog(rootPane, "Avaliacao submetida");
					}
				}
				finally {
					txt_avaliacao_matricula.setText("");
					txt_avaliacao_nota.setText("");
				}
			}
		});
		btnNewButton_6.setBounds(344, 50, 120, 50);
		panel_4.add(btnNewButton_6);
		//--------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//INICIO DA ABA DE RELATORIOS -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Relatorios", null, panel_6, null);
		panel_6.setLayout(null);
		
		JFormattedTextField txt_relatorio_periodo = new JFormattedTextField(new MaskFormatter("####/A"));
		txt_relatorio_periodo.setBounds(167, 8, 147, 20);
		panel_6.add(txt_relatorio_periodo);
		
		JLabel lblNewLabel_27 = new JLabel("Periodo Academico");
		lblNewLabel_27.setBounds(10, 11, 147, 14);
		panel_6.add(lblNewLabel_27);
		
		JLabel lblNewLabel_28 = new JLabel("Ano/Semestre");
		lblNewLabel_28.setBounds(189, 29, 139, 14);
		panel_6.add(lblNewLabel_28);
		
		//BOTAO PARA EXIBIR O RELATORIO DE UM PERIODO ------------------------------------------------------------------------------------------------------------
		JButton btnNewButton_14 = new JButton("Exibir");
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) throws IllegalArgumentException{
				String as = txt_relatorio_periodo.getText();
				
				try {
					if(!periodos.containsKey(as)) {
						JOptionPane.showMessageDialog(rootPane, "Periodo inexistente!", "Erro", JOptionPane.ERROR_MESSAGE);
						throw new IllegalArgumentException("Periodo inexistente!");
					}
					else {
						int numAtv=0, i=0;
						ArrayList <Diciplina> dicaux = new ArrayList<>();
						for(String s : periodos.get(as).getDiciplinas().keySet()) {
							dicaux.add(periodos.get(as).getDiciplinas().get(s));
						}
						Diciplina.sortNome(dicaux);
						System.out.println("Relatorio Periodo Academico "+as);
						for(String s : periodos.get(as).getDiciplinas().keySet()) {
							numAtv+=periodos.get(as).getDiciplinas().get(s).getAtividades().size();
							
							System.out.println(dicaux.get(i).getCodigo()+" "+dicaux.get(i).getNome()+" | Docente responsavel: "+dicaux.get(i).getDoc().getNome()+" - "+dicaux.get(i).getDoc().getLogin()+
									" | Nm de Estudantes Matriculados: "+dicaux.get(i).getEstudantes().size()+" | Nm de Atividades: "+numAtv);
							numAtv=0;
							i++;
						}
						System.out.println();
					}
				}
				catch(IllegalArgumentException e) {
					System.out.println("Erro: Periodo Inexistente");
				}
				finally {
					txt_relatorio_periodo.setText("");
				}
			}
		});
		btnNewButton_14.setBounds(373, 7, 106, 23);
		panel_6.add(btnNewButton_14);
		//--------------------------------------------------------------------------------------------------------------------------------------------------------
		
		JLabel lblNewLabel_27_1 = new JLabel("Estatistica dos Docentes");
		lblNewLabel_27_1.setBounds(10, 78, 147, 14);
		panel_6.add(lblNewLabel_27_1);
		
		//BOTAO PARA EXIBIR AS ESTATISTICAS DOS DOCENTES ---------------------------------------------------------------------------------------------------------
		JButton btnNewButton_15 = new JButton("Exibir");
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Docente> docaux = new ArrayList<>();
				
				for(String s : docentes.keySet()) {
					docaux.add(docentes.get(s));
				}
				
				Collections.sort(docaux);
				
				System.out.println("Estatisticas dos Docentes: ");
				for(int i=0;i<docaux.size();i++) {
					System.out.println(docaux.get(i).getLogin()+" - "+docaux.get(i).getNome()+" | Nm de Diciplinas: "+docaux.get(i).contaDiciplinas()+" | Nm de Periodos: "+docaux.get(i).contaPeriodos()+
							" | Media de Atividades/Diciplina: "+docaux.get(i).mediaAtividadesPorDiciplina()+" | Percentual de Atividades Sincronas: "+docaux.get(i).percentualAtividadesSincronas()+
							"% | Media de notas recebidas: "+docaux.get(i).mediaNotasRecebidas());
				}
				System.out.println();
			}
		});
		btnNewButton_15.setBounds(202, 72, 106, 23);
		panel_6.add(btnNewButton_15);
		//--------------------------------------------------------------------------------------------------------------------------------------------------------
		
		JLabel lblNewLabel_27_1_1 = new JLabel("Estatistica dos Estudantes");
		lblNewLabel_27_1_1.setBounds(10, 156, 162, 14);
		panel_6.add(lblNewLabel_27_1_1);
		
		//BOTAO PARA EXIBIR AS ESTATISTICAS DOS ESTUDANTES -------------------------------------------------------------------------------------------------------
		JButton btnNewButton_16 = new JButton("Exibir");
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Estudante> estaux = new ArrayList<>();
				
				for(Long i : estudantes.keySet()) {
					estaux.add(estudantes.get(i));
				}
				
				Collections.sort(estaux);
				
				System.out.println("Estatisticas dos Estudantes: ");
				for(int i=0;i<estaux.size();i++) {
					System.out.println(estaux.get(i).getMatricula()+" "+estaux.get(i).getNome()+" | Media de Diciplinas/Periodo Matriculadas: "+estaux.get(i).mediaDiciplinasPorPeriodo()+" | Media de Avaliacoes/Diciplina: "+
							estaux.get(i).mediaAvaliacoes()+" | Media de Notas: "+estaux.get(i).mediaNotas());
				}
				System.out.println("\n");
			}
		});
		btnNewButton_16.setBounds(202, 150, 106, 23);
		panel_6.add(btnNewButton_16);
		//--------------------------------------------------------------------------------------------------------------------------------------------------------
		
		JLabel lblNewLabel_27_1_1_1 = new JLabel("Estatistica das Disciplinas");
		lblNewLabel_27_1_1_1.setBounds(10, 236, 181, 14);
		panel_6.add(lblNewLabel_27_1_1_1);
		
		txt_relatorio_login = new JTextField();
		txt_relatorio_login.setBounds(202, 231, 209, 20);
		panel_6.add(txt_relatorio_login);
		txt_relatorio_login.setColumns(10);
		
		//BOTAO PARA EXIBIR AS ESTATISTICAS DAS DISCIPLINAS DE UM DOCENTE ----------------------------------------------------------------------------------------
		JButton btnNewButton_17 = new JButton("Exibir");
		btnNewButton_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) throws IllegalArgumentException {
				String log = txt_relatorio_login.getText();
				
				try {
					if(!docentes.containsKey(log)) {
						JOptionPane.showMessageDialog(rootPane, "Docente inexistente!", "Erro", JOptionPane.ERROR_MESSAGE);
						throw new IllegalArgumentException("Docente inexistente!");
					}
					else {
						ArrayList<Periodo> peraux = new ArrayList<>();
						
						for(String s : docentes.get(log).getDiciplinas().keySet()) {
							peraux.add(docentes.get(log).getDiciplinas().get(s).getPer());
						}
						
						System.out.println("Estatisticas das Diciplinas do Docente "+docentes.get(log).getLogin()+" - "+docentes.get(log).getNome()+": ");
						int j=0;
						Periodo aux = null;
						for(int i=0;i<peraux.size();i++) {
							if(!peraux.get(i).equals(aux)) {
								j=0;
							}
							ArrayList<Diciplina> dicaux = new ArrayList<>();
							for(String s : peraux.get(i).getDiciplinas().keySet()) {
								dicaux.add(peraux.get(i).getDiciplinas().get(s));
							}
							Diciplina.sortNome(dicaux);
							
							
							System.out.println(peraux.get(i).getAnoSemestre()+" - "+dicaux.get(j).getCodigo()+" "+dicaux.get(j).getNome()+" | Nm de Atividades: "+
									dicaux.get(j).getAtividades().size()+" | Percentual de Ativiaddes Sincronas: "+dicaux.get(j).percentualAtividadeSincrona()+"% | Carga Horaria: "+dicaux.get(j).calculaCargaHoraria());
							System.out.println("	Atividades Avaliativas da Disciplina:");
							ArrayList<Trabalho> trabaux = new ArrayList<>();
							ArrayList<Prova> provaux = new ArrayList<>();
							for(Integer k : dicaux.get(j).getAtividades().keySet()) {
								if (dicaux.get(j).getAtividades().get(k).getClass() == Trabalho.class) {
									trabaux.add((Trabalho) dicaux.get(j).getAtividades().get(k));
								}
								else if(dicaux.get(j).getAtividades().get(k).getClass() == Prova.class) {
									provaux.add((Prova) dicaux.get(j).getAtividades().get(k));
								}
							}
							Collections.sort(trabaux);
							Collections.sort(provaux);
							int l;
							if(trabaux.size()<provaux.size()) {
								l = trabaux.size();
								int k;
								for(k=0; k<l; k++) {
									if(trabaux.get(k).getPrazo().compareTo(provaux.get(k).getData()) < 0) {
										System.out.println("        "+trabaux.get(k).getNome()+" | "+trabaux.get(k).getPrazo());
										System.out.println("        "+provaux.get(k).getNome()+" | "+provaux.get(k).getData());
									}
									else {
										System.out.println("        "+provaux.get(k).getNome()+" | "+provaux.get(k).getData());
										System.out.println("        "+trabaux.get(k).getNome()+" | "+trabaux.get(k).getPrazo());
									}
								}
								for(;k<provaux.size();k++) {
									System.out.println("        "+provaux.get(k).getNome()+" | "+provaux.get(k).getData());
								}
							}
								
							else{
								l = provaux.size();
								int k;
								for(k=0; k<l; k++) {
									if(trabaux.get(k).getPrazo().compareTo(provaux.get(k).getData()) < 0) {
										System.out.println("        "+trabaux.get(k).getNome()+" | "+trabaux.get(k).getPrazo());
										System.out.println("        "+provaux.get(k).getNome()+" | "+provaux.get(k).getData());
									}
									else {
										System.out.println("        "+provaux.get(k).getNome()+" | "+provaux.get(k).getData());
										System.out.println("        "+trabaux.get(k).getNome()+" | "+trabaux.get(k).getPrazo());
									}
								}
								for(;k<trabaux.size();k++) {
									System.out.println("        "+trabaux.get(k).getNome()+" | "+trabaux.get(k).getPrazo());
								}
							}
							j++;
							aux=peraux.get(i);
						}
						System.out.println();
					}
				}
				catch (IllegalArgumentException e) {
					System.out.println("Erro: Docente inexistente");
				}
			}
		});
		btnNewButton_17.setBounds(421, 231, 106, 23);
		panel_6.add(btnNewButton_17);
		//--------------------------------------------------------------------------------------------------------------------------------------------------------
		
		JLabel lblNewLabel_27_1_1_1_1 = new JLabel("Login do docente responsavel");
		lblNewLabel_27_1_1_1_1.setBounds(202, 255, 277, 14);
		panel_6.add(lblNewLabel_27_1_1_1_1);
		
		//INICIO DA ABA DE SALVAR E CARREGAR DADOS ----------------------------------------------------------------------------------------------------------------------------------------------------------------
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Salvar e Carregar Dados", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_34 = new JLabel("Nome do arquivo");
		lblNewLabel_34.setBounds(10, 30, 162, 14);
		panel_3.add(lblNewLabel_34);
		
		//BOTAO PARA SALVAR DADOS --------------------------------------------------------------------------------------------------------------------------------
		JButton btnNewButton_18 = new JButton("Salvar");
		btnNewButton_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(txt_salvar_carregar.getText()));
					out.writeObject(periodos);
					out.writeObject(estudantes);
					out.writeObject(diciplinas);
					out.writeObject(docentes);
					out.writeObject(atividades);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(rootPane, "Erro ao salvar o arquivo!", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				finally {
					JOptionPane.showMessageDialog(rootPane, "Salvo no arquivo "+txt_salvar_carregar.getText());
					txt_salvar_carregar.setText(".dat");
				}
			}
		});
		btnNewButton_18.setBounds(348, 27, 102, 23);
		panel_3.add(btnNewButton_18);
		//--------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//BOTAO PARA CARREGAR DADOS ------------------------------------------------------------------------------------------------------------------------------
		JButton btnNewButton_19 = new JButton("Carregar");
		btnNewButton_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(txt_salvar_carregar.getText()));
					periodos = (Map<String, Periodo>)in.readObject();
					estudantes = (Map<Long, Estudante>)in.readObject();
					diciplinas = (Map<String, Diciplina>)in.readObject();
					docentes = (Map<String, Docente>)in.readObject();
					atividades = (Map<Integer, Atividade>)in.readObject();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(rootPane, "Erro na leitura arquivo!", "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(rootPane, "Erro de Leitura das Classes!", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				finally {
					JOptionPane.showMessageDialog(rootPane, "Arquivo "+txt_salvar_carregar.getText()+" carregado");
					txt_salvar_carregar.setText(".dat");
				}
			}
		});
		btnNewButton_19.setBounds(348, 61, 102, 23);
		panel_3.add(btnNewButton_19);
		//--------------------------------------------------------------------------------------------------------------------------------------------------------
		
		txt_salvar_carregar = new JTextField();
		txt_salvar_carregar.setText(".dat");
		txt_salvar_carregar.setBounds(140, 28, 198, 20);
		panel_3.add(txt_salvar_carregar);
		txt_salvar_carregar.setColumns(10);
	}
}

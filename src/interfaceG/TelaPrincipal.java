package interfaceG;

import java.awt.BorderLayout;
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
	Map<String, Periodo> periodos;
	Map<Integer, Estudante> estudantes;
	Map<String, Docente> docentes;
	Map<String, Diciplina> diciplinas;
	Map<Integer, Aula> aulas;
	Map<Integer, Estudo> estudos;
	Map<Integer, Trabalho> trabalhos;
	Map<Integer, Prova> provas;
	
	private JPanel contentPane;
	private JTextField txt_estudante_nome;
	private JTextField txt_estudante_matricula;
	private JTextField txt_docente_nome;
	private JTextField txt_diciplina_nome_cad;
	private JTextField txt_diciplina_codigo_cad;
	private JTextField txt_atividades_nome;
	private JTextField txt_atividades_codigo;
	private JTextField txt_avaliacao_matricula;
	private JTextField txt_avaliacao_sequencial;
	private JTextField txt_avaliacao_codigo;
	private JTextField txt_avaliacao_cons_sequencial;
	private JTextField txt_docente_login;
	private JTextField txt_docente_pag;
	private JTextField txt_diciplina_login_cad;
	private JTextField txt_diciplina_codigo_mat;
	private JTextField txt_diciplina_matricula_mat;
	private JTextField txt_diciplinas_exibir;
	private JTextField txt_avaliacao_nota;
	private JTextField txt_avaliacao_cons_codigo;
	private JTextField txt_relatorio_login;
	private JTextField txt_atividades_estudo_url;
	private JTextField txt_atividades_trabalho_numero;
	private JTextField txt_atividades_prova_conteudo;
	private JTextField txt_salvar_carregar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.err.println(ex);
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
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Periodo", null, panel_5, null);
		panel_5.setLayout(null);
		
		JFormattedTextField txt_periodo_cadastro = new JFormattedTextField(new MaskFormatter("####/#"));
		txt_periodo_cadastro.setToolTipText("");
		txt_periodo_cadastro.setBounds(119, 36, 206, 20);
		panel_5.add(txt_periodo_cadastro);
		
		JLabel lblNewLabel_22 = new JLabel("Ano / Semestre");
		lblNewLabel_22.setBounds(10, 39, 194, 14);
		panel_5.add(lblNewLabel_22);
		
		JLabel lblNewLabel_24 = new JLabel("Cadastro de Periodo");
		lblNewLabel_24.setBounds(156, 11, 278, 14);
		panel_5.add(lblNewLabel_24);
		
		periodos = new HashMap<>();
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String conteudo = txt_periodo_cadastro.getText();
				
				periodos.put(conteudo, new Periodo(conteudo));
				
				JOptionPane.showMessageDialog(rootPane, "Periodo cadastrado");
				txt_periodo_cadastro.setText("");
				
			}
			
		});
		btnNewButton.setBounds(160, 67, 123, 23);
		panel_5.add(btnNewButton);
		
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
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Docente", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Cadastrar novo docente");
		lblNewLabel_3.setBounds(70, 11, 261, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Nome");
		lblNewLabel_4.setBounds(10, 36, 63, 14);
		panel_1.add(lblNewLabel_4);
		
		txt_docente_nome = new JTextField();
		txt_docente_nome.setBounds(90, 36, 241, 20);
		panel_1.add(txt_docente_nome);
		txt_docente_nome.setColumns(10);
		
		JLabel lblNewLabel_25 = new JLabel("Login");
		lblNewLabel_25.setBounds(10, 66, 46, 14);
		panel_1.add(lblNewLabel_25);
		
		txt_docente_login = new JTextField();
		txt_docente_login.setBounds(90, 63, 241, 20);
		panel_1.add(txt_docente_login);
		txt_docente_login.setColumns(10);
		
		JLabel lblNewLabel_26 = new JLabel("Pagina da Web");
		lblNewLabel_26.setBounds(10, 91, 127, 14);
		panel_1.add(lblNewLabel_26);
		
		txt_docente_pag = new JTextField();
		txt_docente_pag.setText("(nao obrigatorio)");
		txt_docente_pag.setToolTipText("");
		txt_docente_pag.setBounds(111, 88, 221, 20);
		panel_1.add(txt_docente_pag);
		txt_docente_pag.setColumns(10);
		
		docentes = new HashMap<>();
		
		JButton btnNewButton_8 = new JButton("Cadastrar");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				docentes.put(txt_docente_login.getText(), new Docente(txt_docente_login.getText(), txt_docente_nome.getText(),  txt_docente_pag.getText()));
				
				JOptionPane.showMessageDialog(rootPane, "Docente cadastrado");
				txt_docente_nome.setText("");
				txt_docente_login.setText("");
				txt_docente_pag.setText("(nao obrigatorio)");
			}
		});
		btnNewButton_8.setBounds(373, 62, 107, 23);
		panel_1.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Exibe Docentes");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Docentes: ");
				for(String s : docentes.keySet()) {
					System.out.print(docentes.get(s).nome+" | "+s);
					if(!docentes.get(s).pagweb.equals("(nao obrigatorio)")) {
						System.out.print(" | "+docentes.get(s).pagweb);
					}
					System.out.println();
				}
				System.out.println();
			}
		});
		btnNewButton_9.setBounds(341, 304, 127, 23);
		panel_1.add(btnNewButton_9);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Disciplinas", null, panel_2, null);
		panel_2.setLayout(null);
		
		JFormattedTextField txt_diciplinas_periodo = new JFormattedTextField(new MaskFormatter("####/#"));
		txt_diciplinas_periodo.setBounds(90, 119, 227, 20);
		panel_2.add(txt_diciplinas_periodo);
		
		JLabel lblNewLabel_5 = new JLabel("Cadastrar nova disciplina");
		lblNewLabel_5.setBounds(66, 11, 300, 14);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Nome");
		lblNewLabel_6.setBounds(10, 36, 56, 14);
		panel_2.add(lblNewLabel_6);
		
		txt_diciplina_nome_cad = new JTextField();
		txt_diciplina_nome_cad.setBounds(90, 36, 227, 20);
		panel_2.add(txt_diciplina_nome_cad);
		txt_diciplina_nome_cad.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Codigo");
		lblNewLabel_7.setBounds(10, 67, 56, 14);
		panel_2.add(lblNewLabel_7);
		
		txt_diciplina_codigo_cad = new JTextField();
		txt_diciplina_codigo_cad.setBounds(90, 64, 227, 20);
		panel_2.add(txt_diciplina_codigo_cad);
		txt_diciplina_codigo_cad.setColumns(10);
		
		JLabel lblNewLabel_7_1 = new JLabel("Login docente responsavel");
		lblNewLabel_7_1.setBounds(10, 94, 156, 14);
		panel_2.add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_7_2 = new JLabel("Periodo");
		lblNewLabel_7_2.setBounds(10, 120, 56, 14);
		panel_2.add(lblNewLabel_7_2);
		
		txt_diciplina_login_cad = new JTextField();
		txt_diciplina_login_cad.setBounds(176, 91, 141, 20);
		panel_2.add(txt_diciplina_login_cad);
		txt_diciplina_login_cad.setColumns(10);
		
		diciplinas = new HashMap<>();
		
		JButton btnNewButton_1 = new JButton("Cadastrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String per = txt_diciplinas_periodo.getText();
				
				Diciplina aux = new Diciplina(txt_diciplina_nome_cad.getText(), docentes.get(txt_diciplina_login_cad.getText()), periodos.get(per));
				diciplinas.put(txt_diciplina_codigo_cad.getText(), aux);
				docentes.get(txt_diciplina_login_cad.getText()).dic.put(txt_diciplina_codigo_cad.getText(), diciplinas.get(txt_diciplina_codigo_cad.getText()));
			
				periodos.get(per).dic.put(txt_diciplina_codigo_cad.getText(), diciplinas.get(txt_diciplina_codigo_cad.getText()));
				
				JOptionPane.showMessageDialog(rootPane, "Diciplina cadastrada");
				txt_diciplina_nome_cad.setText("");
				txt_diciplina_codigo_cad.setText("");
				txt_diciplina_login_cad.setText("");
			}
		});
		btnNewButton_1.setBounds(357, 63, 107, 50);
		panel_2.add(btnNewButton_1);
		
		JLabel lblNewLabel_8 = new JLabel("Ano / Semestre");
		lblNewLabel_8.setBounds(114, 139, 207, 14);
		panel_2.add(lblNewLabel_8);
		
		JLabel lblNewLabel_5_1 = new JLabel("Cadastrar estudante");
		lblNewLabel_5_1.setBounds(66, 176, 300, 14);
		panel_2.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_6_1 = new JLabel("Codigo Diciplina");
		lblNewLabel_6_1.setBounds(10, 202, 107, 14);
		panel_2.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_10 = new JLabel("Matricula Estudante");
		lblNewLabel_10.setBounds(10, 227, 134, 14);
		panel_2.add(lblNewLabel_10);
		
		txt_diciplina_codigo_mat = new JTextField();
		txt_diciplina_codigo_mat.setBounds(127, 201, 145, 20);
		panel_2.add(txt_diciplina_codigo_mat);
		txt_diciplina_codigo_mat.setColumns(10);
		
		txt_diciplina_matricula_mat = new JTextField();
		txt_diciplina_matricula_mat.setBounds(127, 224, 145, 20);
		panel_2.add(txt_diciplina_matricula_mat);
		txt_diciplina_matricula_mat.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Matricular");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int mat = Integer.parseInt(txt_diciplina_matricula_mat.getText());
				diciplinas.get(txt_diciplina_codigo_mat.getText()).est.put(mat, estudantes.get(mat));
				String cod = txt_diciplina_codigo_mat.getText();
				estudantes.get(mat).dic.put(cod, diciplinas.get(cod));
				
				JOptionPane.showMessageDialog(rootPane, "Estudante Matriculado");
				txt_diciplina_matricula_mat.setText("");
			}
		});
		btnNewButton_3.setBounds(300, 223, 107, 23);
		panel_2.add(btnNewButton_3);
		
		JButton btnNewButton_10 = new JButton("Exibe Diciplinas");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Diciplinas: ");
				for(String s : diciplinas.keySet()) {
					System.out.println(s+" "+diciplinas.get(s).nome+" - "+diciplinas.get(s).per.anoSemestre+" | Responsavel: "+diciplinas.get(s).doc.nome);
				}
				System.out.println();
			}
		});
		btnNewButton_10.setBounds(346, 135, 134, 23);
		panel_2.add(btnNewButton_10);
		
		JLabel lblNewLabel_5_2 = new JLabel("Verificar Estudantes Matriculados");
		lblNewLabel_5_2.setBounds(66, 266, 300, 14);
		panel_2.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_11 = new JLabel("Codigo da Diciplina");
		lblNewLabel_11.setBounds(10, 297, 146, 14);
		panel_2.add(lblNewLabel_11);
		
		txt_diciplinas_exibir = new JTextField();
		txt_diciplinas_exibir.setBounds(127, 294, 145, 20);
		panel_2.add(txt_diciplinas_exibir);
		txt_diciplinas_exibir.setColumns(10);
		
		JButton btnNewButton_12 = new JButton("Exibe");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cod = txt_diciplinas_exibir.getText();
				System.out.println("Estudantes matriculados em "+cod+" "+diciplinas.get(cod).nome);
				for (Integer i : diciplinas.get(cod).est.keySet()) {
					System.out.println(i+" "+diciplinas.get(cod).est.get(i).nome);
				}
				System.out.println();
				
				txt_diciplinas_exibir.setText("");
			}
		});
		btnNewButton_12.setBounds(300, 293, 100, 23);
		panel_2.add(btnNewButton_12);
		
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
		
		txt_estudante_matricula = new JTextField();
		txt_estudante_matricula.setBounds(80, 84, 204, 20);
		panel.add(txt_estudante_matricula);
		txt_estudante_matricula.setColumns(10);
		
		estudantes = new HashMap<>();
		
		JButton btnNewButton_2 = new JButton("Cadastrar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				estudantes.put(Integer.parseInt(txt_estudante_matricula.getText()), new Estudante(Integer.parseInt(txt_estudante_matricula.getText()),txt_estudante_nome.getText()));
				
				JOptionPane.showMessageDialog(rootPane, "Estudante Cadastrado");
				txt_estudante_nome.setText("");
				txt_estudante_matricula.setText("");
			}
		});
		btnNewButton_2.setBounds(324, 63, 106, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_11 = new JButton("Exibe Estudantes");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Estudantes: ");
				for(Integer i : estudantes.keySet()) {
					System.out.println(i+" "+estudantes.get(i).nome);
				}
				System.out.println();
			}
		});
		btnNewButton_11.setBounds(348, 304, 132, 23);
		panel.add(btnNewButton_11);
		
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
		rb_trabalho.setBounds(10, 173, 109, 23);
		Atividades.add(rb_trabalho);
		
		JRadioButton rb_prova = new JRadioButton("Prova");
		rb_prova.setBounds(10, 210, 109, 23);
		Atividades.add(rb_prova);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb_aula);
		bg.add(rb_trabalho);
		bg.add(rb_estudo);
		bg.add(rb_prova);
		
		JLabel lblNewLabel_13 = new JLabel("Cadastrar nova atividade");
		lblNewLabel_13.setBounds(121, 11, 202, 14);
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
		txt_atividades_estudo_url.setBounds(121, 137, 282, 20);
		Atividades.add(txt_atividades_estudo_url);
		txt_atividades_estudo_url.setColumns(10);
		
		JLabel lblNewLabel_29 = new JLabel("Url da pagina da atividade");
		lblNewLabel_29.setBounds(413, 140, 145, 14);
		Atividades.add(lblNewLabel_29);
		
		JFormattedTextField txt_atividades_trabalho_prazo = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txt_atividades_trabalho_prazo.setBounds(119, 174, 77, 20);
		Atividades.add(txt_atividades_trabalho_prazo);
		
		JLabel lblNewLabel_30 = new JLabel("Prazo");
		lblNewLabel_30.setBounds(140, 191, 46, 14);
		Atividades.add(lblNewLabel_30);
		
		txt_atividades_trabalho_numero = new JTextField();
		txt_atividades_trabalho_numero.setBounds(293, 173, 51, 20);
		Atividades.add(txt_atividades_trabalho_numero);
		txt_atividades_trabalho_numero.setColumns(10);
		
		JLabel lblNewLabel_31 = new JLabel("N. de pessoas/grupo");
		lblNewLabel_31.setBounds(354, 176, 161, 14);
		Atividades.add(lblNewLabel_31);
		
		JFormattedTextField txt_atividades_trabalho_ch = new JFormattedTextField();
		txt_atividades_trabalho_ch.setBounds(206, 174, 77, 20);
		Atividades.add(txt_atividades_trabalho_ch);
		
		JLabel lblNewLabel_32 = new JLabel("Carga Horaria");
		lblNewLabel_32.setBounds(206, 191, 89, 14);
		Atividades.add(lblNewLabel_32);
		
		JFormattedTextField txt_atividades_provas_data = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txt_atividades_provas_data.setBounds(119, 210, 77, 20);
		Atividades.add(txt_atividades_provas_data);
		
		JLabel lblNewLabel_9_1 = new JLabel("Data");
		lblNewLabel_9_1.setBounds(140, 229, 46, 14);
		Atividades.add(lblNewLabel_9_1);
		
		JFormattedTextField txt_atividades_provas_hora = new JFormattedTextField(new MaskFormatter("##:##"));
		txt_atividades_provas_hora.setBounds(218, 210, 77, 20);
		Atividades.add(txt_atividades_provas_hora);
		
		JLabel lblNewLabel_23_1 = new JLabel("Hora");
		lblNewLabel_23_1.setBounds(238, 229, 46, 14);
		Atividades.add(lblNewLabel_23_1);
		
		aulas = new HashMap<>();
		estudos = new HashMap<>();
		trabalhos = new HashMap<>();
		provas = new HashMap<>();
		
		JButton btnNewButton_5 = new JButton("Cadastrar");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int seq=1;
				String cod = txt_atividades_codigo.getText();
				seq+=aulas.size();
				seq+=estudos.size();
				seq+=trabalhos.size();
				seq+=provas.size();
				
				if(rb_aula.isSelected()) {
					aulas.put(seq, new Aula(txt_atividades_nome.getText(), true, txt_atividades_aula_data.getText(), txt_atividades_aula_hora.getText()));
					diciplinas.get(cod).aul.put(seq, aulas.get(seq));
					diciplinas.get(cod).doc.aul.put(seq, aulas.get(seq));
				}else if(rb_estudo.isSelected()) {
					estudos.put(seq, new Estudo(txt_atividades_nome.getText(), false, txt_atividades_estudo_url.getText()));
					diciplinas.get(cod).estud.put(seq, estudos.get(seq));
					diciplinas.get(cod).doc.estud.put(seq, estudos.get(seq));
				}else if(rb_trabalho.isSelected()) {
					trabalhos.put(seq, new Trabalho(txt_atividades_nome.getText(), false, txt_atividades_trabalho_prazo.getText(), Integer.parseInt(txt_atividades_trabalho_numero.getText()), 
							Double.parseDouble(txt_atividades_trabalho_ch.getText())));
					diciplinas.get(cod).trab.put(seq, trabalhos.get(seq));
					diciplinas.get(cod).doc.trab.put(seq, trabalhos.get(seq));
				}else if(rb_prova.isSelected()) {
					provas.put(seq, new Prova(txt_atividades_nome.getText(), true, txt_atividades_provas_data.getText(), txt_atividades_provas_hora.getText(), txt_atividades_prova_conteudo.getText()));
					diciplinas.get(cod).prov.put(seq, provas.get(seq));
					diciplinas.get(cod).doc.prov.put(seq, provas.get(seq));
				}
				
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
				JOptionPane.showMessageDialog(rootPane, "Atividade Cadastrada");
				
			}
		});
		
		btnNewButton_5.setBounds(438, 210, 120, 23);
		Atividades.add(btnNewButton_5);
		
		txt_atividades_prova_conteudo = new JTextField();
		txt_atividades_prova_conteudo.setBounds(303, 211, 86, 20);
		Atividades.add(txt_atividades_prova_conteudo);
		txt_atividades_prova_conteudo.setColumns(10);
		
		JLabel lblNewLabel_33 = new JLabel("Conteudo");
		lblNewLabel_33.setBounds(313, 229, 90, 14);
		Atividades.add(lblNewLabel_33);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Avaliacao", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_17 = new JLabel("Submeter avaliacao do aluno");
		lblNewLabel_17.setBounds(141, 11, 324, 14);
		panel_4.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("Matricula do aluno");
		lblNewLabel_18.setBounds(10, 33, 104, 14);
		panel_4.add(lblNewLabel_18);
		
		txt_avaliacao_matricula = new JTextField();
		txt_avaliacao_matricula.setBounds(214, 30, 86, 20);
		panel_4.add(txt_avaliacao_matricula);
		txt_avaliacao_matricula.setColumns(10);
		
		JLabel lblNewLabel_20 = new JLabel("Numero sequencial da atividade");
		lblNewLabel_20.setBounds(10, 58, 183, 14);
		panel_4.add(lblNewLabel_20);
		
		txt_avaliacao_sequencial = new JTextField();
		txt_avaliacao_sequencial.setBounds(214, 55, 86, 20);
		panel_4.add(txt_avaliacao_sequencial);
		txt_avaliacao_sequencial.setColumns(10);
		
		txt_avaliacao_codigo = new JTextField();
		txt_avaliacao_codigo.setBounds(214, 80, 86, 20);
		panel_4.add(txt_avaliacao_codigo);
		txt_avaliacao_codigo.setColumns(10);
		
		JLabel lblNewLabel_21 = new JLabel("Nota");
		lblNewLabel_21.setBounds(10, 108, 46, 14);
		panel_4.add(lblNewLabel_21);
		
		JLabel lblNewLabel_20_1 = new JLabel("Sequencial da atividade");
		lblNewLabel_20_1.setBounds(10, 226, 183, 14);
		panel_4.add(lblNewLabel_20_1);
		
		txt_avaliacao_cons_sequencial = new JTextField();
		txt_avaliacao_cons_sequencial.setBounds(214, 223, 86, 20);
		panel_4.add(txt_avaliacao_cons_sequencial);
		txt_avaliacao_cons_sequencial.setColumns(10);
		
		JLabel lblNewLabel_19 = new JLabel("Consultar avaliacoes");
		lblNewLabel_19.setBounds(156, 163, 176, 14);
		panel_4.add(lblNewLabel_19);
		
		JButton btnNewButton_7 = new JButton("Consultar");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_7.setBounds(345, 190, 135, 50);
		panel_4.add(btnNewButton_7);
		
		txt_avaliacao_nota = new JTextField();
		txt_avaliacao_nota.setBounds(214, 105, 86, 20);
		panel_4.add(txt_avaliacao_nota);
		txt_avaliacao_nota.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Codigo da Diciplina");
		lblNewLabel_16.setBounds(10, 83, 120, 14);
		panel_4.add(lblNewLabel_16);
		
		JButton btnNewButton_6 = new JButton("Submeter");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int mat = Integer.parseInt(txt_avaliacao_matricula.getText());
				int seq = Integer.parseInt(txt_avaliacao_sequencial.getText());
				String cod = txt_avaliacao_codigo.getText();
				
				if(diciplinas.get(cod).aul.keySet().contains(seq)) {
					diciplinas.get(cod).aul.get(seq).avaliacao.put(mat, Integer.parseInt(txt_avaliacao_nota.getText()));
				}
				else if(diciplinas.get(cod).estud.keySet().contains(seq)) {
					diciplinas.get(cod).estud.get(seq).avaliacao.put(mat, Integer.parseInt(txt_avaliacao_nota.getText()));
				}
				else if(diciplinas.get(cod).trab.keySet().contains(seq)) {
					diciplinas.get(cod).trab.get(seq).avaliacao.put(mat, Integer.parseInt(txt_avaliacao_nota.getText()));
				}
				else if(diciplinas.get(cod).prov.keySet().contains(seq)) {
					diciplinas.get(cod).prov.get(seq).avaliacao.put(mat, Integer.parseInt(txt_avaliacao_nota.getText()));
				}
				
				JOptionPane.showMessageDialog(rootPane, "Avaliacao submetida");
				txt_avaliacao_matricula.setText("");
				txt_avaliacao_nota.setText("");
				
			}
		});
		btnNewButton_6.setBounds(345, 50, 120, 50);
		panel_4.add(btnNewButton_6);
		
		JLabel lblNewLabel_16_1 = new JLabel("Codigo da Diciplina");
		lblNewLabel_16_1.setBounds(10, 193, 120, 14);
		panel_4.add(lblNewLabel_16_1);
		
		txt_avaliacao_cons_codigo = new JTextField();
		txt_avaliacao_cons_codigo.setBounds(214, 188, 86, 20);
		panel_4.add(txt_avaliacao_cons_codigo);
		txt_avaliacao_cons_codigo.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Relatorios", null, panel_6, null);
		panel_6.setLayout(null);
		
		JFormattedTextField txt_relatorio_periodo = new JFormattedTextField(new MaskFormatter("####/#"));
		txt_relatorio_periodo.setBounds(167, 8, 147, 20);
		panel_6.add(txt_relatorio_periodo);
		
		JLabel lblNewLabel_27 = new JLabel("Periodo Academico");
		lblNewLabel_27.setBounds(10, 11, 147, 14);
		panel_6.add(lblNewLabel_27);
		
		JLabel lblNewLabel_28 = new JLabel("Ano/Semestre");
		lblNewLabel_28.setBounds(189, 29, 139, 14);
		panel_6.add(lblNewLabel_28);
		
		JButton btnNewButton_14 = new JButton("Exibir");
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String as = txt_relatorio_periodo.getText();
				int numAtv=0;
				System.out.println("Relatorio Periodo Academico "+as);
				for(String s : periodos.get(as).dic.keySet()) {
					numAtv+=periodos.get(as).dic.get(s).aul.size();
					numAtv+=periodos.get(as).dic.get(s).estud.size();
					numAtv+=periodos.get(as).dic.get(s).trab.size();
					numAtv+=periodos.get(as).dic.get(s).prov.size();
					System.out.println(s+" "+periodos.get(as).dic.get(s).nome+" | Docente responsavel: "+periodos.get(as).dic.get(s).doc.nome+" - "+periodos.get(as).dic.get(s).doc.login+
							" | Nm de Estudantes Matriculados: "+periodos.get(as).dic.get(s).est.size()+" | Nm de Atividades: "+numAtv);
					numAtv=0;
				}
				
				System.out.println();
			}
		});
		btnNewButton_14.setBounds(373, 7, 106, 23);
		panel_6.add(btnNewButton_14);
		
		JLabel lblNewLabel_27_1 = new JLabel("Estatistica dos Docentes");
		lblNewLabel_27_1.setBounds(10, 78, 147, 14);
		panel_6.add(lblNewLabel_27_1);
		
		JButton btnNewButton_15 = new JButton("Exibir");
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Estatisticas dos Docentes: ");
				for(String s : docentes.keySet()) {
					System.out.println(docentes.get(s).login+" - "+docentes.get(s).nome+" | Nm de Diciplinas: "+docentes.get(s).contaDiciplinas()+" | Nm de Periodos: "+docentes.get(s).contaPeriodos()+
							" | Media de Atividades/Diciplina: "+docentes.get(s).mediaAtividadesPorDiciplina()+" | Percentual de Atividades Sincronas: "+docentes.get(s).percentualAtividadesSincronas()+
							"% | Media de notas recebidas: "+docentes.get(s).mediaNotasRecebidas());
				}
				System.out.println();
			}
		});
		btnNewButton_15.setBounds(202, 72, 106, 23);
		panel_6.add(btnNewButton_15);
		
		JLabel lblNewLabel_27_1_1 = new JLabel("Estatistica dos Estudantes");
		lblNewLabel_27_1_1.setBounds(10, 156, 162, 14);
		panel_6.add(lblNewLabel_27_1_1);
		
		JButton btnNewButton_16 = new JButton("Exibir");
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Estatisticas dos Estudantes: ");
				for(Integer i : estudantes.keySet()) {
					System.out.println(i+" "+estudantes.get(i).nome+" | Media de Diciplinas/Periodo Matriculadas: "+estudantes.get(i).mediaDiciplinasPorPeriodo()+" | Media de Avaliacoes/Diciplina: "+
							estudantes.get(i).mediaAvaliacoes()+" | Media de Notas: "+estudantes.get(i).mediaNotas());
				}
				System.out.println();
			}
		});
		btnNewButton_16.setBounds(202, 150, 106, 23);
		panel_6.add(btnNewButton_16);
		
		JLabel lblNewLabel_27_1_1_1 = new JLabel("Estatistica das Disciplinas");
		lblNewLabel_27_1_1_1.setBounds(10, 236, 162, 14);
		panel_6.add(lblNewLabel_27_1_1_1);
		
		txt_relatorio_login = new JTextField();
		txt_relatorio_login.setBounds(202, 231, 162, 20);
		panel_6.add(txt_relatorio_login);
		txt_relatorio_login.setColumns(10);
		
		JButton btnNewButton_17 = new JButton("Exibir");
		btnNewButton_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String log = txt_relatorio_login.getText();
				int numAtv=0;
				
				System.out.println("Estatisticas das Diciplinas do Docente "+docentes.get(log).login+" - "+docentes.get(log).nome+": ");
				for(String s : docentes.get(log).dic.keySet()) {
					numAtv+=docentes.get(log).dic.get(s).aul.size();
					numAtv+=docentes.get(log).dic.get(s).estud.size();
					numAtv+=docentes.get(log).dic.get(s).trab.size();
					numAtv+=docentes.get(log).dic.get(s).prov.size();
					System.out.println(docentes.get(log).dic.get(s).per.anoSemestre+" - "+s+" "+docentes.get(log).dic.get(s).nome+" | Nm de Atividades: "+
							numAtv+" | Percentual de Atividades Sincronas: "+docentes.get(log).dic.get(s).percentualAtividadeSincrona()+"%");
					System.out.println("	Atividades Avaliativas da Disciplina:");
					for(Integer i : docentes.get(log).dic.get(s).aul.keySet()) {
						System.out.println("		"+docentes.get(log).dic.get(s).aul.get(i).nome);
					}
					for(Integer i : docentes.get(log).dic.get(s).prov.keySet()) {
						System.out.println("		"+docentes.get(log).dic.get(s).prov.get(i).nome);
					}
					numAtv=0;
				}
			}
		});
		btnNewButton_17.setBounds(406, 230, 106, 23);
		panel_6.add(btnNewButton_17);
		
		JLabel lblNewLabel_27_1_1_1_1 = new JLabel("Login do docente responsavel");
		lblNewLabel_27_1_1_1_1.setBounds(202, 255, 184, 14);
		panel_6.add(lblNewLabel_27_1_1_1_1);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Salvar e Carregar Dados", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_34 = new JLabel("Nome do arquivo");
		lblNewLabel_34.setBounds(10, 30, 162, 14);
		panel_3.add(lblNewLabel_34);
		
		JButton btnNewButton_18 = new JButton("Salvar");
		btnNewButton_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(txt_salvar_carregar.getText()));
					out.writeObject(periodos);
					out.writeObject(estudantes);
					out.writeObject(diciplinas);
					out.writeObject(docentes);
					out.writeObject(aulas);
					out.writeObject(estudos);
					out.writeObject(trabalhos);
					out.writeObject(provas);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(rootPane, "Erro");
					e.printStackTrace();
				}
				txt_salvar_carregar.setText(".dat");
			}
		});
		btnNewButton_18.setBounds(324, 26, 102, 23);
		panel_3.add(btnNewButton_18);
		
		JButton btnNewButton_19 = new JButton("Carregar");
		btnNewButton_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(txt_salvar_carregar.getText()));
					periodos = (Map<String, Periodo>)in.readObject();
					estudantes = (Map<Integer, Estudante>)in.readObject();
					diciplinas = (Map<String, Diciplina>)in.readObject();
					docentes = (Map<String, Docente>)in.readObject();
					aulas = (Map<Integer, Aula>)in.readObject();
					estudos = (Map<Integer, Estudo>)in.readObject();
					trabalhos = (Map<Integer, Trabalho>)in.readObject();
					provas = (Map<Integer, Prova>)in.readObject();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(rootPane, "Erro de Leitura do Arquivo");
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(rootPane, "Erro de Leitura das Classes");
					e.printStackTrace();
				}
				txt_salvar_carregar.setText(".dat");
			}
		});
		btnNewButton_19.setBounds(324, 60, 102, 23);
		panel_3.add(btnNewButton_19);
		
		txt_salvar_carregar = new JTextField();
		txt_salvar_carregar.setText(".dat");
		txt_salvar_carregar.setBounds(116, 27, 198, 20);
		panel_3.add(txt_salvar_carregar);
		txt_salvar_carregar.setColumns(10);
	}
}

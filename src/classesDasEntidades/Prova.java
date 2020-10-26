package classesDasEntidades;

public class Prova extends Atividade{
	private String data;
	private String hora;
	private String conteudo;
	
	public Prova(String n, boolean b, String d, String h, String c) {
		super(n, b);
		data = d;
		hora = h;
		conteudo = c;
	}

	public String getData() {
		return data;
	}
	public String getHora() {
		return hora;
	}
	public String getConteudo() {
		return conteudo;
	}

	@Override
	public double getcHoraria() {
		return 2;
	}
}

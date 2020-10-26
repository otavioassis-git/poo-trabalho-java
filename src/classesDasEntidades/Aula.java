package classesDasEntidades;

public class Aula extends Atividade {
	private String data;
	private String hora;
	
	public Aula(String n, boolean b, String d, String h) {
		super(n, b);
		data = d;
		hora = h;
	}
	

	public String getData() {
		return data;
	}

	public String getHora() {
		return hora;
	}

	@Override
	public double getcHoraria() {
		return 2;
	}
	
	
}

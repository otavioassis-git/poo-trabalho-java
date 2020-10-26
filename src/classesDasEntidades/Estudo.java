package classesDasEntidades;

public class Estudo extends Atividade{
	private String url;
	
	public Estudo(String n, boolean b, String u) {
		super(n, b);
		url = u;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public double getcHoraria() {
		return 2;
	}
}

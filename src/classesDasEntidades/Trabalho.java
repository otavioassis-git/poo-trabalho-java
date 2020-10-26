package classesDasEntidades;

public class Trabalho extends Atividade{
	private String prazo;
	private int nmPessoas;
	private double cHoraria;
	
	public Trabalho(String n, boolean b, String p, int nm, double ch) {
		super(n, b);
		prazo = p;
		nmPessoas = nm;
		cHoraria = ch;
	}

	public String getPrazo() {
		return prazo;
	}

	public int getNmPessoas() {
		return nmPessoas;
	}

	@Override
	public double getcHoraria() {
		return cHoraria;
	}
}

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Medalhista implements Comparable<Medalhista>{

	private static final int MAX_MEDALHAS = 8; 
	private String nome;
	private SexoEnum sexo;
	private LocalDate dataNascimento;
	private String pais;
	private Medalha[] medalhas;
	private int numMedalhas;
	
	public Medalhista (String nome, SexoEnum sexo, LocalDate dataNascimento, String pais) {
		
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.pais = pais;
		this.medalhas = new Medalha[MAX_MEDALHAS];
		this.numMedalhas = 0;
	}
	
	public int incluirMedalha (Medalha medalha) {
	
		medalhas[numMedalhas++] = medalha;
		return numMedalhas;
	}
	
	public int totalDeMedalhas() {
		
		return numMedalhas;
	}
	
	public int totalDeMedalhas(MedalhaEnum tipoMedalha) {
		
		Medalha[] medalhasTipo = getMedalhas(tipoMedalha);
		
		return medalhasTipo.length;
	}

	private Medalha[] getMedalhas(MedalhaEnum tipoMedalha) {
	
		Medalha[] medalhasTemp = new Medalha[numMedalhas];
		Medalha[] medalhasTipo;
		int numMedalhasTipo = 0;
		
		for (int i = 0; i < numMedalhas; i++) {
			if (medalhas[i].getTipo().equals(tipoMedalha))
				medalhasTemp[numMedalhasTipo++] = medalhas[i];
		}
		
		medalhasTipo = new Medalha[numMedalhasTipo];
		for (int i = 0; i < numMedalhasTipo; i++)
			medalhasTipo[i] = medalhasTemp[i];
		
		return medalhasTipo;
	}
	
	public Medalha[] ouros() {
		
		return getMedalhas(MedalhaEnum.OURO);
	}
	
	public Medalha[] pratas() {
		
		return getMedalhas(MedalhaEnum.PRATA);
	}
	
	public Medalha[] bronzes() {
		
		return getMedalhas(MedalhaEnum.BRONZE);
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public SexoEnum getSexo() {
		return sexo;
	}
	
	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
		
	@Override
	public String toString() {
		
		String dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(dataNascimento);  //formata a data em DD/MM/AAAA
		
		return (nome + ", " + sexo + ". Nascimento: " + dataFormatada + ". Pais: " + pais);
	}
	
	@Override
	public boolean equals(Object outro) {
		
		Medalhista outroMedalhista = (Medalhista) outro;
		
		return (outroMedalhista.getNome().equals(getNome()));
	}
	
	@Override
	public int compareTo(Medalhista outroMedalhista) {
		
		return (this.nome.compareTo(outroMedalhista.getNome()));
	}
}


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Medalha {

	private MedalhaEnum tipoMedalha;
	private LocalDate dataMedalha;
	private String esporte;
	private String evento;
	
	public Medalha (MedalhaEnum tipoMedalha, LocalDate dataMedalha, String esporte, String evento) {
		
		this.tipoMedalha = tipoMedalha;
		this.dataMedalha = dataMedalha;
		this.esporte = esporte;
		this.evento = evento;
	}
	
	public MedalhaEnum getTipo() {
		return tipoMedalha;
	}
	
	public void setTipo(MedalhaEnum tipoMedalha) {
		this.tipoMedalha = tipoMedalha;
	}
	
	public LocalDate getDataMedalha() {
		return dataMedalha;
	}
	
	public void setDataMedalha(LocalDate dataMedalha) {
		this.dataMedalha = dataMedalha;
	}
	
	public String getEsporte() {
		return esporte;
	}
	
	public void setEsporte(String esporte) {
		this.esporte = esporte;
	}
	
	public String getEvento() {
		return evento;
	}
	
	public void setEvento(String evento) {
		this.evento = evento;
	}

	@Override
	public String toString() {
		
		String dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(dataMedalha);  //formata a data em DD/MM/AAAA
		
		return (tipoMedalha + " - " + esporte + " - " + evento + " - " + dataFormatada);
	}
	
}

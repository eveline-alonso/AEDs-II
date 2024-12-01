import java.time.LocalDate;

public class Evento implements Comparable<Evento>{
    private String evento;
    private String disciplina;
    private LocalDate dataEvento;

    private int quantMedalhistas;
    private ABB<Medalhista> medalhistas;

    public Evento(String evento, String disciplina, LocalDate data){
        this.disciplina = disciplina;
        this.evento = evento;
        dataEvento = data;
        quantMedalhistas = 0;
        medalhistas = new ABB<>();
    }

    public void inserirMedalhista(Medalhista medalhista){
        medalhistas.adicionar(medalhista);
        quantMedalhistas++;
    }

    public LocalDate getData(){
        return dataEvento;
    }

    public void relatorioMedalhistas(){
        medalhistas.caminhamentoEmOrdem();
    }

    @Override
    public int compareTo(Evento o) {
        return toString().compareTo(o.toString());
    }

    @Override
    public boolean equals(Object o) {
        
        return toString().equals(o.toString());
    }

    @Override
    public String toString(){
        return disciplina + " - "+evento;
    }
    

}

import java.util.Comparator;

public class Seleção<T extends Comparable<T>> implements IOrdenator<T> {

	private T[] dados;
	private Comparator<T> comparador;
	private long comparacoes;
	private long movimentacoes;
	private long inicio;
	private long termino;
	
	public Seleção(T[] dados) {
		
		comparacoes = 0;
		movimentacoes = 0;
		inicio = System.nanoTime();
		this.dados = dados;
	}
	
	@Override
	public void setComparador(Comparator<T> comparador) {
		this.comparador = comparador;
	}
	
	@Override
	public T[] ordenar() {
	
		comparacoes = 0;
		movimentacoes = 0;
		iniciar();
		
		for (int i = 0; i < (dados.length - 1); i++) {
			 int menor = i;
			 for (int j = (i + 1); j < dados.length; j++) {
				 comparacoes++;
	        	 if (comparador.compare(dados[menor], dados[j]) > 0)
	        		 menor = j;
			 }
	         if (menor != i)
	        	 swap(menor, i);
	    }
		
		terminar();
		
		return dados;
	}
	
	private void swap(int i, int j) {
	      
		movimentacoes++;
		
		T temp = dados[i];
	    dados[i] = dados[j];
	    dados[j] = temp;
	}
	
	@Override
	public long getComparacoes() {
		return comparacoes;
	}
	
	@Override
	public long getMovimentacoes() {
		return movimentacoes;
	}
	
	private void iniciar() {
		inicio = System.nanoTime();
	}
	
	private void terminar() {
		termino = System.nanoTime();
	}
	
	@Override
	public double getTempoOrdenacao() {
		
		double tempoTotal;
		
	    tempoTotal = (termino - inicio) / 1_000_000;
	    return tempoTotal;
	}
}
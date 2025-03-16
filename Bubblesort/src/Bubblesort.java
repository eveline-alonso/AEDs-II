import java.util.Comparator;

public class Bubblesort<T extends Comparable<T>> implements IOrdenator<T> {

	private T[] dados;
	private Comparator<T> comparador;
	private long comparacoes;
	private long movimentacoes;
	private long inicio;
	private long termino;
	
	public Bubblesort(T[] dados) {
		
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
	public T[] ordenar () {

		comparacoes = 0;
		movimentacoes = 0;
		iniciar();
		
		for (int i = dados.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				comparacoes++;
				if (comparador.compare(dados[j], dados[j + 1]) > 0)
					swap (j, j + 1);
			}
		}
		
		terminar();
		
		return  dados;
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
import java.util.Comparator;

public class Quicksort<T extends Comparable<T>> implements IOrdenator<T> {

	private T[] dadosOrdenados;
	private Comparator<T> comparador;
	private long comparacoes;
	private long movimentacoes;
	private long inicio;
	private long termino;
	
	public Quicksort() {
		
		comparacoes = 0;
		movimentacoes = 0;
	}
	
	@Override
	public void setComparador(Comparator<T> comparador) {
		this.comparador = comparador;
	}
	
	@Override
	public T[] ordenar(T[] dados) {
	
		dadosOrdenados = dados;
		
		comparacoes = 0;
		movimentacoes = 0;
		iniciar();
		
		quicksort(0, dadosOrdenados.length - 1);
	
		terminar();
		
		return dadosOrdenados;
	}
	
	/**
	* Algoritmo de ordenação Quicksort.
	* @param int esq: início da partição a ser ordenada
	* @param int dir: fim da partição a ser ordenada
	*/
	private void quicksort(int esq, int dir) {
				
		int part;
		if (esq < dir) {
			part = particao(esq, dir);
			quicksort(esq, part - 1);
			quicksort(part + 1, dir);
		}
	}
					
	private int particao(int inicio, int fim) {
			
		T pivot = dadosOrdenados[fim];
		int part = inicio - 1;
		for (int i = inicio; i < fim; i++) {
			if (comparador.compare(dadosOrdenados[i], pivot) < 0) {
				part++;
				swap(part, i);
			}
		}
		part++;
		swap(part, fim);
		return (part);
	}
		
	private void swap(int i, int j) {
	      
		movimentacoes++;
		
		T temp = dadosOrdenados[i];
	    dadosOrdenados[i] = dadosOrdenados[j];
	    dadosOrdenados[j] = temp;
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
import java.util.Comparator;

public class Mergesort<T extends Comparable<T>> implements IOrdenator<T> {

	private T[] dadosOrdenados;
	private Comparator<T> comparador;
	private long comparacoes;
	private long movimentacoes;
	private long inicio;
	private long termino;
	
	public Mergesort() {
		
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
		
		mergesort(0, dadosOrdenados.length - 1);
		
		terminar();
		
		return dadosOrdenados;
	}
	
	/**
	* Algoritmo de ordenação Mergesort.
	* @param int esq: início do vetor a ser ordenado
	* @param int dir: fim do vetor a ser ordenado
	*/
	// 1.a chamada do método mergesort: esq: 0; dir: dadosOrdenados.length - 1
	private void mergesort(int esq, int dir) {
		
		if (esq < dir) {
			int meio = (esq + dir) / 2;
	        mergesort(esq, meio);
	        mergesort(meio + 1, dir);
	        intercalar(esq, meio, dir);
	    }
	}

	/**
	* Algoritmo que intercala os elementos localizados entre as posições esq e dir
	* @param int esq: início do vetor a ser ordenado
	* @param int meio: posição do meio do vetor a ser ordenado
	* @param int dir: fim do vetor a ser ordenado
	*/ 
	private void intercalar(int esq, int meio, int dir) {

		int n1, n2, i, j, k;

	    //Definir tamanho dos dois subvetores
	    n1 = meio - esq + 1;
	    n2 = dir - meio;

	    @SuppressWarnings("unchecked")
		T[] a1 = (T[]) new Comparable[n1];
	      	
		@SuppressWarnings("unchecked")
		T[] a2 = (T[]) new Comparable[n2];

	    //Inicializar primeiro subvetor
	    for (i = 0; i < n1; i++) {
	    	a1[i] = dadosOrdenados[esq + i];
	    }

	    //Inicializar segundo subvetor
	    for (j = 0; j < n2; j++) {
	    	a2[j] = dadosOrdenados[meio + 1 + j];
	    }

		//Intercalação propriamente dita
	    for (i = j = 0, k = esq; (i < n1 && j < n2); k++) {
	    	comparacoes++;
	      	movimentacoes++;
	        if (comparador.compare(a1[i], a2[j]) < 0)
	        	dadosOrdenados[k] = a1[i++];
	        else
	        	dadosOrdenados[k] = a2[j++];
	    }
		
		if (i == n1)
			for (; k <= dir; k++) {
				movimentacoes++;
        		dadosOrdenados[k] = a2[j++];
		    }
		else
		    for (; k <= dir; k++) {
		    	movimentacoes++;
	        	dadosOrdenados[k] = a1[i++];
		    }
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
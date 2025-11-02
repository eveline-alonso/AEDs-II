import java.util.NoSuchElementException;

public class Lista<E extends Comparable<E>> {

	private E[] lista;
	private final int primeiro;
	private int ultimo;
	
	@SuppressWarnings("unchecked")
	public Lista(int tamanho) {
		
		lista = (E[]) new Comparable[tamanho];
		this.primeiro = this.ultimo = 0;
	}
	
	public boolean vazia() {
		
		return (this.primeiro == this.ultimo);
	}
	
	public boolean cheia() {
		
		return (this.ultimo == this.lista.length); 
	}
	
	public void inserir(E novo, int posicao) {
		
		if (cheia())
			throw new IllegalStateException("Não foi possível inserir o item na lista: "
					+ "a lista está cheia!");

		if ((posicao < 0) || (posicao > this.ultimo)) 
			throw new IndexOutOfBoundsException ("Não foi possível inserir o item na lista: "
					+ "a posição informada é inválida!");
		
		for (int i = this.ultimo; i > posicao; i--)
			lista[i] = lista[i-1];
				
		lista[posicao] = novo;
				
		this.ultimo++;
	}
	
	public E remover(int posicao) {
		
		E removido;
		
		if (vazia())
			throw new IllegalStateException("Não foi possível remover o item da lista: "
					+ "a lista está vazia!");

		if ((posicao < 0) || (posicao >= this.ultimo))
			throw new IndexOutOfBoundsException ("Não foi possível remover o item da lista: "
					+ "a posição informada é inválida!");
			
		removido = lista[posicao];
		
		this.ultimo--;
				
		for (int i = posicao; i < this.ultimo; i++)
			lista[i] = lista[i+1];
				
		return removido;
	}
	
	public void ordenar() {
		Quicksort<E> metodoOrdenacao = new Quicksort<>();
		lista = metodoOrdenacao.ordenar(lista);
	}
	
	@Override
	public String toString() {
	
		String listaTexto;
		
		if (vazia()) {
			listaTexto = "A lista está vazia."; 
		} else {
			listaTexto = "";
		
			for (int i = this.primeiro; i < this.ultimo; i++) {
				listaTexto += lista[i] + "\n";
			}
		}
		return listaTexto;
	}
	
	public E pesquisar(E procurado) {
		
		return (pesquisar(this.primeiro, this.ultimo - 1, procurado));
	}
	
	private E pesquisar(int inicio, int fim, E procurado) {
		
		int meio, comparacao;
		
		if (inicio > fim)
			throw new NoSuchElementException("Item não encontrado!");
		
		meio = (inicio + fim)/2;
		comparacao = procurado.compareTo(lista[meio]);
		
		if (comparacao == 0)
			// encontrou!
			return lista[meio];
		else if (comparacao > 0)
			// buscar entre os maiores
			return pesquisar(meio + 1, fim, procurado);
		else
			// buscar entre os menores
			return pesquisar(inicio, meio - 1, procurado);
	}
}

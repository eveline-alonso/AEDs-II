import java.util.Comparator;
import java.util.NoSuchElementException;

public class ABB<K, V> implements IMapeamento<K, V> {

	private No<K, V> raiz; // referência à raiz da árvore.
	private Comparator<K> comparador; //comparador empregado para definir "menores" e "maiores".
	private int tamanho;
	
	/**
	 * Método auxiliar para inicialização da árvore binária de busca.
	 * 
	 * Este método define a raiz da árvore como {@code null} e seu tamanho como 0.
	 * Utiliza o comparador fornecido para definir a organização dos elementos na árvore.
	 * @param comparador o comparador para organizar os elementos da árvore.
	 */
	private void init(Comparator<K> comparador) {
		raiz = null;
		tamanho = 0;
		this.comparador = comparador;
	}
	
	/**
     * Construtor da classe.
     * O comparador padrão de ordem natural será utilizado.
	 */ 
    @SuppressWarnings("unchecked")
	public ABB() {
        init((Comparator<K>) Comparator.naturalOrder());
    }

    /**
     * Construtor da classe.
     * Esse construtor cria uma nova árvore binária de busca vazia.
     *  
     * @param comparador o comparador a ser utilizado para organizar os elementos da árvore.  
     */
    public ABB(Comparator<K> comparador) {
        init(comparador);
    }
    
    /**
     * Método booleano que indica se a árvore está vazia ou não.
     * @return
     * verdadeiro: se a raiz da árvore for null, o que significa que a árvore está vazia.
     * falso: se a raiz da árvore não for null, o que significa que a árvore não está vazia.
     */
    public Boolean vazia() {
        return (this.raiz == null);
    }

    @Override
    /**
     * Método que encapsula a pesquisa recursiva de itens na árvore.
     * @param chave a chave do item que será pesquisado na árvore.
     * @return o valor associado à chave.
     */
	public V pesquisar(K chave) {
    	return pesquisar(raiz, chave);
	}
    
    private V pesquisar(No<K, V> raizArvore, K procurado) {
    	
    	int comparacao;
    	
    	if (raizArvore == null)
    		/// Se a raiz da árvore ou sub-árvore for null, a árvore/sub-árvore está vazia e então o item não foi encontrado.
    		throw new NoSuchElementException("O item não foi localizado na árvore!");
    	
    	comparacao = comparador.compare(procurado, raizArvore.getChave());
    	
    	if (comparacao == 0)
    		/// O item procurado foi encontrado.
    		return raizArvore.getItem();
    	else if (comparacao < 0)
    		/// Se o item procurado for menor do que o item armazenado na raiz da árvore:
            /// pesquise esse item na sub-árvore esquerda.    
    		return pesquisar(raizArvore.getEsquerda(), procurado);
    	else
    		/// Se o item procurado for maior do que o item armazenado na raiz da árvore:
            /// pesquise esse item na sub-árvore direita.
    		return pesquisar(raizArvore.getDireita(), procurado);
    }
    
    @Override
    /**
     * Método que encapsula a adição recursiva de itens à árvore, associando-o à chave fornecida.
     * @param chave a chave associada ao item que será inserido na árvore.
     * @param item o item que será inserido na árvore.
     * 
     * @return o tamanho atualizado da árvore após a execução da operação de inserção.
     */
	public int inserir(K chave, V item) {
		/// Chama o método recursivo "inserir", responsável por adicionar, o item passado como parâmetro, à árvore.
        /// O método "inserir" recursivo receberá, como primeiro parâmetro, a raiz atual da árvore; 
    	/// como segundo parâmetro, a chave do item que será adicionado à árvore; e como terceiro parâmetro, o item.
        /// Por fim, a raiz atual da árvore é atualizada, com a raiz retornada pelo método "inserir" recursivo.
        this.raiz = inserir(this.raiz, chave, item);
        tamanho++;
        return tamanho;
	}
    
    /**
     * Método recursivo responsável por adicionar um item à árvore.
     * @param raizArvore a raiz da árvore ou sub-árvore em que o item será adicionado.
     * @param chave a chave associada ao item que deverá ser inserido.
     * @param item o item que deverá ser adicionado à árvore.
     * @return a raiz atualizada da árvore ou sub-árvore em que o item foi adicionado.
     * @throws IllegalArgumentException se um item com a mesma chave já estiver presente na árvore.
     */
    protected No<K, V> inserir(No<K, V> raizArvore, K chave, V item) {
    	
    	int comparacao;
    	
        /// Se a raiz da árvore ou sub-árvore for null, a árvore/sub-árvore está vazia e então um novo item é inserido.
        if (raizArvore == null)
            raizArvore = new No<>(chave, item);
        else {
        	comparacao = comparador.compare(chave, raizArvore.getChave());
        
        	if (comparacao < 0)
        		/// Se a chave do item que deverá ser inserido na árvore for menor do que 
        		/// a chave do item armazenado na raiz da árvore:
        		/// adicione esse novo item à sub-árvore esquerda; 
        		/// e atualize a referência para a sub-árvore esquerda modificada. 
        		raizArvore.setEsquerda(inserir(raizArvore.getEsquerda(), chave, item));
        	else if (comparacao > 0)
        		/// Se a chave do item que deverá ser inserido na árvore for maior do que 
        		/// a chave do item armazenado na raiz da árvore:
        		/// adicione esse novo item à sub-árvore direita; 
        		/// e atualize a referência para a sub-árvore direita modificada.
        		raizArvore.setDireita(inserir(raizArvore.getDireita(), chave, item));
        	else
        		/// A chave do item armazenado na raiz da árvore 
        		/// é igual à chave do novo item que deveria ser inserido na árvore.
        		throw new IllegalArgumentException("O item já foi inserido anteriormente na árvore.");
        }
        
        /// Retorna a raiz atualizada da árvore ou sub-árvore em que o item foi adicionado.
        return raizArvore;
    }

    @Override 
    public String toString(){
    	return percorrer();
    }

    @Override
	public String percorrer() {
		return caminhamentoEmOrdem();
	}
    
    public String caminhamentoEmOrdem() {
    	
    	if (vazia())
    		throw new IllegalStateException("A árvore está vazia!");
    	
    	return caminhamentoEmOrdem(raiz);
    }
    
    private String caminhamentoEmOrdem(No<K, V> raizArvore) {
    	if (raizArvore != null) {
    		String resposta = caminhamentoEmOrdem(raizArvore.getEsquerda());
    		resposta += raizArvore.getItem() + "\n";
    		resposta += caminhamentoEmOrdem(raizArvore.getDireita());
    		
    		return resposta;
    	} else {
    		return "";
    	}
    }
    
	@Override
	/**
     * Método que encapsula a remoção recursiva de um item da árvore.
     * @param chave a chave do item que deverá ser localizado e removido da árvore.
     * @return o valor associado ao item removido.
	 */
	public V remover(K chave) {
		
		V removido = pesquisar(chave);
		
		/// Chama o método recursivo "remover", que será responsável por 
		/// pesquisar o item que apresenta a chave passada como parâmetro na árvore e retirá-lo da árvore.
        /// O método "remover" recursivo receberá, como primeiro parâmetro, a raiz atual da árvore; 
    	/// e, como segundo parâmetro, a chave do item que deverá ser localizado e retirado dessa árvore.
    	/// Por fim, a raiz atual da árvore é atualizada, com a raiz retornada pelo método "remover" recursivo.
		raiz = remover(raiz, chave);
		tamanho--;
		return removido;
	}

    /**
     * Método recursivo responsável por localizar um item na árvore e retirá-lo da árvore.
     * @param raizArvore a raiz da árvore ou sub-árvore da qual o item será retirado.
     * @param chaveRemover a chave do item que deverá ser localizado e removido da árvore.
     * @return a raiz atualizada da árvore ou sub-árvore da qual o item foi retirado.
     */
    protected No<K, V> remover(No<K, V> raizArvore, K chaveRemover) {
    	
    	int comparacao;
    	
        /// Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e o item, que deveria ser retirado dessa árvore, não foi encontrado.
        /// Nesse caso, deve-se lançar uma exceção.
        if (raizArvore == null) 
        	throw new NoSuchElementException("O item a ser removido não foi localizado na árvore!");
        
        comparacao = comparador.compare(chaveRemover, raizArvore.getChave());
        
        if (comparacao == 0) {
            /// O item armazenado na raiz da árvore corresponde ao item que deve ser retirado dessa árvore.
            /// Ou seja, o item que deve ser retirado da árvore foi encontrado.
        	if (raizArvore.getDireita() == null) {
        		/// O nó da árvore que será retirado não possui descendentes à direita.
                /// Nesse caso, os descendentes à esquerda do nó que está sendo retirado da árvore passarão a ser descendentes do nó-pai do nó que está sendo retirado.
                raizArvore = raizArvore.getEsquerda();
        	} else if (raizArvore.getEsquerda() == null) {
                /// O nó da árvore que será retirado não possui descendentes à esquerda.
                /// Nesse caso, os descendentes à direita do nó que está sendo retirado da árvore passarão a ser descendentes do nó-pai do nó que está sendo retirado.
                raizArvore = raizArvore.getDireita();
        	} else {
            	/// O nó que está sendo retirado da árvore possui descendentes à esquerda e à direita.
                /// Nesse caso, o antecessor do nó que está sendo retirado é localizado na sub-árvore esquerda desse nó. 
                /// O antecessor do nó que está sendo retirado da árvore corresponde
                /// ao nó que armazena o item cuja chave é a maior, 
                /// dentre as chaves menores do que a do item do nó que está sendo retirado.
                /// Depois de ser localizado na sub-árvore esquerda do nó que está sendo retirado, 
                /// o antecessor desse nó o substitui.
                /// A sub-árvore esquerda do nó que foi retirado é atualizada com a remoção do antecessor.
                raizArvore.setEsquerda(removerNoAntecessor(raizArvore, raizArvore.getEsquerda()));
        	}
        } else if (comparacao < 0)
        	/// Se a chave do item que deverá ser localizado e retirado da árvore 
        	/// for menor do que a chave do item armazenado na raiz da árvore:
        	/// pesquise e retire esse item da sub-árvore esquerda.
            raizArvore.setEsquerda(remover(raizArvore.getEsquerda(), chaveRemover));
        else
        	/// Se a chave do item que deverá ser localizado e retirado da árvore
        	/// for maior do que a chave do item armazenado na raiz da árvore:
        	/// pesquise e retire esse item da sub-árvore direita.
            raizArvore.setDireita(remover(raizArvore.getDireita(), chaveRemover));
         
        /// Retorna a raiz atualizada da árvore ou sub-árvore da qual o item foi retirado.
        return raizArvore;
    }

    /**
     * Método recursivo responsável por localizar na árvore ou sub-árvore o antecessor do nó que deverá ser retirado. 
     * O antecessor do nó que deverá ser retirado da árvore corresponde
     * ao nó que armazena o item cuja chave é a maior, 
     * dentre as chaves menores do que a do item que deverá ser retirado.
     * Depois de ser localizado na árvore ou sub-árvore, 
     * o antecessor do nó que deverá ser retirado da árvore o substitui.
     * Adicionalmente, a árvore ou sub-árvore é atualizada com a remoção do antecessor.
     * @param itemRetirar: referência ao nó que armazena o item que deverá ser retirado da árvore.
     * @param raizArvore: raiz da árvore ou sub-árvore em que o antecessor do nó que deverá ser retirado deverá ser localizado.
     * @return a raiz atualizada da árvore ou sub-árvore após a remoção do antecessor do nó que foi retirado da árvore.
     */
    protected No<K, V> removerNoAntecessor(No<K, V> itemRetirar, No<K, V> raizArvore) {
        /// Se o antecessor do nó que deverá ser retirado da árvore ainda não foi encontrado...
        if (raizArvore.getDireita() != null) {
            /// Pesquise o antecessor na sub-árvore direita.
            raizArvore.setDireita(removerNoAntecessor(itemRetirar, raizArvore.getDireita()));
        } else {
        	/// O antecessor do nó que deverá ser retirado da árvore foi encontrado e deverá substitui-lo.
        	itemRetirar.setChave(raizArvore.getChave());
            itemRetirar.setItem(raizArvore.getItem());
            /// A raiz da árvore ou sub-árvore é atualizada com os descendentes à esquerda do antecessor.
            /// Ou seja, retira-se o antecessor da árvore.
            raizArvore = raizArvore.getEsquerda();
        }
        return raizArvore;
    }

	@Override
	public int tamanho() {
		return tamanho;
	}
}
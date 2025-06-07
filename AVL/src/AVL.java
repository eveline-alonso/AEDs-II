import java.util.Comparator;

public class AVL<K, V> extends ABB<K, V> {

	/**
	 *  Construtor da classe.
	 *  Esse construtor cria uma nova árvore binária de busca AVL vazia.
	 */
    public AVL() {
        super();
    }
    
    public AVL(Comparator<K> comparador) {
    	super(comparador);
    }
    
    /**
    * Método recursivo responsável por adicionar um item à árvore.
    * @param raizArvore: raiz da árvore ou sub-árvore em que o item será adicionado.
    * @param item: item que deverá ser adicionado à árvore.
    * @return a raiz atualizada da árvore ou sub-árvore balanceada AVL em que o item foi adicionado.
    */
    @Override
    protected No<K, V> inserir(No<K, V> raizArvore, K chave, V item) {
    	
    	return balancear(super.inserir(raizArvore, chave, item));
    }
    
    /**
    * Método recursivo responsável por localizar na árvore ou sub-árvore o antecessor do nó que deverá ser retirado. 
    * O antecessor do nó que deverá ser retirado da árvore corresponde
    * ao nó que armazena o item que é o maior, 
    * dentre os itens menores do que o item que deverá ser retirado.
    * Depois de ser localizado na árvore ou sub-árvore, 
    * o antecessor do nó que deverá ser retirado da árvore o substitui.
    * Adicionalmente, a árvore ou sub-árvore balanceada AVL é atualizada com a remoção do antecessor.
    * @param itemRetirar: referência ao nó que armazena o item que deverá ser retirado da árvore.
    * @param raizArvore: raiz da árvore ou sub-árvore em que o antecessor do nó que deverá ser retirado deverá ser localizado.
    * @return a raiz atualizada da árvore ou sub-árvore balanceada AVL após a remoção do antecessor do nó que foi retirado da árvore.
    */
    @Override
    protected No<K, V> removerNoAntecessor(No<K, V> itemRetirar, No<K, V> raizArvore) {
    	
        return balancear(super.removerNoAntecessor(itemRetirar, raizArvore));
    }

    /**
    * Método recursivo responsável por localizar um item na árvore e retirá-lo da árvore.
    * @param raizArvore: raiz da árvore ou sub-árvore da qual o item será retirado.
    * @param itemRemover: item que deverá ser localizado e removido da árvore.
    * @return a raiz atualizada da árvore ou sub-árvore balanceada AVL da qual o item foi retirado.
    */
    @Override
    protected No<K, V> remover(No<K, V> raizArvore, K chaveRemover) {
    	
    	return balancear(super.remover(raizArvore, chaveRemover));
    }

    private No<K, V> balancear(No<K, V> raizArvore) {
		
		int fatorBalanceamento;
		int fatorBalanceamentoFilho;
		
		if (raizArvore != null) {
			
			fatorBalanceamento = raizArvore.getFatorBalanceamento();
			
			if (fatorBalanceamento == 2) {
				// árvore desbalanceada à esquerda.
				fatorBalanceamentoFilho = raizArvore.getEsquerda().getFatorBalanceamento();
				if (fatorBalanceamentoFilho == -1)
					// Rotação dupla
					// Rotação simples à esquerda
					raizArvore.setEsquerda(rotacionarEsquerda(raizArvore.getEsquerda()));
				// Rotação simples à direita
				raizArvore = rotacionarDireita(raizArvore);
			} else if (fatorBalanceamento == -2) {
				// árvore desbalanceada à direita.
				fatorBalanceamentoFilho = raizArvore.getDireita().getFatorBalanceamento();
				if (fatorBalanceamentoFilho == 1)
					// Rotação dupla
					// Rotação simples à direita
					raizArvore.setDireita(rotacionarDireita(raizArvore.getDireita()));
				// Rotação simples à esquerda
				raizArvore = rotacionarEsquerda(raizArvore);
			} else
				raizArvore.setAltura();
		}
		return raizArvore;
	}
	
	private No<K, V> rotacionarDireita(No<K, V> p) {
		
		No<K, V> u;
		No<K, V> filhoEsquerdaDireita;  // triângulo vermelho
		
		u = p.getEsquerda();
		filhoEsquerdaDireita = u.getDireita();
		
		p.setEsquerda(filhoEsquerdaDireita);
		u.setDireita(p);
		
		p.setAltura();
		u.setAltura();
		
		return u;
	}
	
	private No<K, V> rotacionarEsquerda(No<K, V> p) {
	
		No<K, V> z;
		No<K, V> filhoDireitaEsquerda;  // triângulo vermelho
		
		z = p.getDireita();
		filhoDireitaEsquerda = z.getEsquerda();
		
		p.setDireita(filhoDireitaEsquerda);
		z.setEsquerda(p);
		
		p.setAltura();
		z.setAltura();
		
		return z;
	}
}

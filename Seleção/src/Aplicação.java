import java.util.Random;

public class Aplicação {
    static final int[] TAMANHOS_TESTE_GRANDE =  { 31_250_000, 62_500_000, 125_000_000, 250_000_000, 500_000_000 };
    static final int[] TAMANHOS_TESTE_MEDIO =   {     12_500,     25_000,      50_000,     100_000,     200_000 };
    static final int[] TAMANHOS_TESTE_PEQUENO = {          3,          6,          12,          24,          48 };
    static Random aleatorio = new Random(42);
    
    /**
     * Gerador de vetores aleatórios de tamanho pré-definido. 
     * @param tamanho Tamanho do vetor a ser criado.
     * @return Vetor com dados aleatórios, com valores entre 1 e (tamanho/2), desordenado.
     */
    static Integer[] gerarVetor(int tamanho){
        Integer[] vetor = new Integer[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, tamanho/2);
        }
        return vetor;
        
    }
    
    public static void ordenar(int[] vetorTamanhos) {
    	
    	int tamVetor;
    	Integer[] vetor;
    	Seleção<Integer> ordenacao;
    	
    	for (int i = 0; i < vetorTamanhos.length; i++) {
    		tamVetor = vetorTamanhos[i];
    		vetor = gerarVetor(tamVetor);
    		ordenacao = new Seleção<>(vetor);
    		ordenacao.setComparador(Integer::compareTo);
    		vetor = ordenacao.ordenar();
    		
    		System.out.printf("%,d;%,d;%,d;%.2f\n", tamVetor, ordenacao.getComparacoes(), ordenacao.getMovimentacoes(), ordenacao.getTempoOrdenacao());
    	}
    }
    
    public static void main(String[] args) {
        
    	System.out.println("Contagem de operações e medição "
    			+ "do tempo de execução do método de ordenação por Seleção");
    	ordenar(TAMANHOS_TESTE_MEDIO);
    }
}
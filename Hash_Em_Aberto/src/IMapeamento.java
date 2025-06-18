public interface IMapeamento<K, V> {

    public int inserir(K chave, V item);
    public V pesquisar(K chave);
    public V remover(K chave);
    public int tamanho();
    public String percorrer();
}

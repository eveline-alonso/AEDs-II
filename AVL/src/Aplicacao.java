import java.util.Comparator;

public class Aplicacao {

	public static void main(String[] args) {

		AVL<String, Aluno> turma = new AVL<>();
		Aluno alunoNovo;

		// Comparator<String> compNomeReverso = ((a1, a2) -> a1.compareTo(a2)*-1);
		// AVL<String, Aluno> turma = new AVL<>(compNomeReverso);  // para árvore em ordem alfabética reversa
		
        if (turma.vazia())
            System.out.println("A turma está vazia."); /// Essa mensagem deve ser exibida.
        else
            System.out.println("A turma possui alunos matriculados.");

        try {
        	alunoNovo = new Aluno (5, "Amanda", 85.00);
        	turma.inserir(alunoNovo.getNome(), alunoNovo);

        	alunoNovo = new Aluno(8, "Uriel", 87.00);
        	turma.inserir(alunoNovo.getNome(), alunoNovo);

        	alunoNovo = new Aluno(6, "Ivo", 75.00);
        	turma.inserir(alunoNovo.getNome(), alunoNovo);

        	alunoNovo = new Aluno(3, "Olivia", 90.00);
        	turma.inserir(alunoNovo.getNome(), alunoNovo);

        	alunoNovo = new Aluno(9, "Evandro", 85.00);
        	turma.inserir(alunoNovo.getNome(), alunoNovo);

        	alunoNovo = new Aluno(4, "Sônia", 82.00);
        	turma.inserir(alunoNovo.getNome(), alunoNovo);

        	alunoNovo = new Aluno(6, "Ivo", 75.00);
        	turma.inserir(alunoNovo.getNome(), alunoNovo);

        } catch (Exception excecao) {
        	System.out.println(excecao.getMessage());
        	/// Deve ser exibida a mensagem: "O item já foi inserido anteriormente na árvore."
        }

        System.out.println("=============\nCaminhamento Em Ordem");
        System.out.println(turma.caminhamentoEmOrdem()); // Amanda - Evandro - Ivo - Olívia - Sônia - Uriel
        
        System.out.println("=============\nPesquisando");
        System.out.println(turma.pesquisar("Amanda"));
        
        System.out.println("=============\nPesquisando");
        System.out.println(turma.pesquisar("Evandro"));
        
        try {
        	turma.remover("Benício"); 
        } catch (Exception excecao) {
        	System.out.println(excecao.getMessage()); /// Deve ser exibida a mensagem: "O item a ser removido não foi localizado na árvore!"
        }
        
        turma.remover("Amanda");
        turma.remover("Olivia");
        
        System.out.println("=============\nCaminhamento Em Ordem");
        System.out.println(turma.caminhamentoEmOrdem()); // Evandro - Ivo - Sônia - Uriel
        
        turma.remover("Sônia");
        turma.remover("Uriel");
        
        System.out.println("=============\nCaminhamento Em Ordem");
        System.out.println(turma.caminhamentoEmOrdem()); // Evandro - Ivo
        
        turma.remover("Ivo");
        turma.remover("Evandro");
        
        try {
        	turma.remover("Evandro"); 
        } catch (Exception excecao) {
        	System.out.println(excecao.getMessage()); /// Deve ser exibida a mensagem: "O item a ser removido não foi localizado na árvore!"
        }
	}
}
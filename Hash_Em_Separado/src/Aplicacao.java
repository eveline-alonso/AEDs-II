public class Aplicacao {

	public static void main(String[] args) {

		TabelaHash<String, Aluno> turma = new TabelaHash<>(3);
		Aluno alunoNovo, alunoRetirar, alunoProcurado;
		
		try {
			alunoNovo = new Aluno (5, "Amanda", 85.00);
			turma.inserir("Amanda", alunoNovo);

			alunoNovo = new Aluno(8, "Uriel", 87.00);
			turma.inserir("Uriel", alunoNovo);

			alunoNovo = new Aluno(6, "Ivo", 75.00);
			turma.inserir("Ivo", alunoNovo);

			alunoNovo = new Aluno(3, "Olivia", 90.00);
			turma.inserir("Olivia", alunoNovo);

			alunoNovo = new Aluno(9, "Evandro", 85.00);
			turma.inserir("Evandro", alunoNovo);

			alunoNovo = new Aluno(4, "Sônia", 82.00);
			turma.inserir("Sônia", alunoNovo);
        
			alunoNovo = new Aluno(10, "Olivia", 80.00);
			turma.inserir("Olivia", alunoNovo);
			
		} catch (Exception excecao) {
			System.out.println(excecao.getMessage());
		}
		
        System.out.println("Alunos matriculados na turma:");
        turma.imprimir(); // Uriel - Sônia - Olívia - Amanda - Ivo - Evandro  
        
        System.out.println("Pesquisando por Amanda:");
        System.out.println(turma.pesquisar("Amanda"));
        
        System.out.println("Pesquisando por Evandro:");
        System.out.println(turma.pesquisar("Evandro"));
        
        try {
        	System.out.println("Removendo Benício:");
        	turma.remover("Benício"); 
        } catch (Exception excecao) {
        	System.out.println(excecao.getMessage()); /// Deve ser exibida a mensagem: "O item a ser removido não foi localizado na árvore!"

        }
        
        turma.remover("Amanda");
        
        turma.remover("Olivia");
        
        System.out.println("Alunos matriculados na turma:");
        turma.imprimir(); // Uriel - Sônia - Ivo - Evandro  
	}
}

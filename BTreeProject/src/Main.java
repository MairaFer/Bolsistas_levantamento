import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int t = 3; // Ordem mínima da Árvore B
        ArvoreB arvoreB = new ArvoreB(t);

        // Inserir 5000 dados na Árvore B
        for (int i = 1; i <= 500000; i++) {
            int chave = i; // Gera uma chave aleatória entre 0 e 9999
            arvoreB.inserir(chave);
        }

        // Salvar a Árvore B em um arquivo
        try {
            arvoreB.salvarParaArquivo("arvoreb_saida.txt");
            System.out.println("Árvore B salva em arvoreb_saida.txt");
        } catch (IOException e) {
            System.out.println("Erro ao salvar a Árvore B: " + e.getMessage());
        }

        // Realizar testes de busca
        int chaveBusca = 2392;
        NoArvoreB resultado = arvoreB.buscar(chaveBusca);
        if (resultado != null) {
            System.out.println("Chave " + chaveBusca + " encontrada na Árvore B.");
        } else {
            System.out.println("Chave " + chaveBusca + " não encontrada na Árvore B.");
        }

        // Realizar testes de remoção
        int chaveRemover = 5000;
        arvoreB.remover(chaveRemover);
        System.out.println("Chave " + chaveRemover + " removida da Árvore B.");

        // Salvar o estado da Árvore B após a remoção
        try {
            arvoreB.salvarParaArquivo("arvoreb_saida_apos_remocao.txt");
            System.out.println("Árvore B após remoção salva em arvoreb_saida_apos_remocao.txt");
        } catch (IOException e) {
            System.out.println("Erro ao salvar a Árvore B após remoção: " + e.getMessage());
        }
    }
}

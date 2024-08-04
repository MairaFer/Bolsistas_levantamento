import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArvoreB {
    NoArvoreB raiz = null;
    int t;

    public ArvoreB(int t) {
        this.t = t;
    }

    public NoArvoreB buscar(int chave) {
        return this.raiz == null ? null : this.raiz.buscar(chave);
    }

    public void inserir(int chave) {
        if (this.raiz == null) {
            this.raiz = new NoArvoreB(this.t, true);
            this.raiz.chaves[0] = chave;
            this.raiz.n = 1;
        } else if (this.raiz.n == 2 * this.t - 1) {
            NoArvoreB s = new NoArvoreB(this.t, false);
            s.filhos[0] = this.raiz;
            s.dividirFilho(0, this.raiz);
            int i = 0;
            if (s.chaves[0] < chave) {
                i++;
            }
            s.filhos[i].inserirNaoCheio(chave);
            this.raiz = s;
        } else {
            this.raiz.inserirNaoCheio(chave);
        }
    }

    public void remover(int chave) {
        if (this.raiz == null) {
            System.out.println("A árvore está vazia.");
        } else {
            this.raiz.remover(chave);
            if (this.raiz.n == 0) {
                if (this.raiz.folha) {
                    this.raiz = null;
                } else {
                    this.raiz = this.raiz.filhos[0];
                }
            }
        }
    }
    public void salvarParaArquivo(String nomeArquivo) throws IOException {
        try (FileWriter escritorArquivo = new FileWriter(nomeArquivo);
             BufferedWriter escritorBuffer = new BufferedWriter(escritorArquivo)) {
            if (this.raiz != null) {
                this.salvarNoParaArquivo(this.raiz, escritorBuffer);
            }
        }
    }

    private void salvarNoParaArquivo(NoArvoreB no, BufferedWriter escritor) throws IOException {
        if (no == null) return;

        // Imprimir chaves dos filhos
        for (int i = 0; i < no.n; i++) {
            if (!no.folha) {
                salvarNoParaArquivo(no.filhos[i], escritor);
            }
            escritor.write(no.chaves[i] + "\n"); // Imprime cada chave em uma nova linha
        }

        if (!no.folha) {
            salvarNoParaArquivo(no.filhos[no.n], escritor);
        }
    }

    public void carregarDeArquivo(String nomeArquivo) throws IOException {
        try (FileReader leitorArquivo = new FileReader(nomeArquivo);
             BufferedReader leitorBuffer = new BufferedReader(leitorArquivo)) {
            List<Integer> chaves = new ArrayList<>();
            String linha;
            while ((linha = leitorBuffer.readLine()) != null) {
                chaves.add(Integer.parseInt(linha));
            }

            // Reconstruir a árvore B a partir da lista de chaves
            this.raiz = construirArvoreB(chaves);
        }
    }

    private NoArvoreB construirArvoreB(List<Integer> chaves) {
        if (chaves.isEmpty()) return null;

        NoArvoreB raiz = new NoArvoreB(this.t, true);

        for (int chave : chaves) {
            if (raiz.n == 2 * this.t - 1) {
                NoArvoreB novaRaiz = new NoArvoreB(this.t, false);
                novaRaiz.filhos[0] = raiz;
                novaRaiz.dividirFilho(0, raiz);
                raiz = novaRaiz;
            }
            raiz.inserirNaoCheio(chave);
        }

        return raiz;
    }


    private NoArvoreB carregarNoDeArquivo(BufferedReader leitor) throws IOException {
        String linha = leitor.readLine();
        if (linha == null) {
            return null;
        }

        // Ler a quantidade de chaves e se o nó é folha
        String[] partes = linha.split(" ");
        int n = Integer.parseInt(partes[0]);
        boolean folha = partes[1].equals("1");

        NoArvoreB no = new NoArvoreB(this.t, folha);
        no.n = n;

        // Ler as chaves
        linha = leitor.readLine();
        partes = linha.split(" ");
        for (int i = 0; i < n; ++i) {
            no.chaves[i] = Integer.parseInt(partes[i]);
        }

        // Ler os filhos se não for folha
        if (!folha) {
            for (int i = 0; i <= n; ++i) {
                no.filhos[i] = carregarNoDeArquivo(leitor);
            }
        }

        return no;
    }

    // Novo método de impressão
    public void imprimirArvore() {
        if (this.raiz != null) {
            this.raiz.imprimirNo();
        }
    }


}

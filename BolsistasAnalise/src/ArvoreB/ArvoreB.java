package ArvoreB;

import java.io.*;
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

    public void inserir(Elemento elemento) {
        if (this.raiz == null) {
            this.raiz = new NoArvoreB(this.t, true);
            this.raiz.inserirNaoCheio(elemento);
        } else if (this.raiz.n == 2 * this.t - 1) {
            NoArvoreB s = new NoArvoreB(this.t, false);
            s.filhos[0] = this.raiz;
            s.dividirFilho(0, this.raiz);
            int i = 0;
            if (s.chaves[0] < elemento.getChave()) {
                i++;
            }
            s.filhos[i].inserirNaoCheio(elemento);
            this.raiz = s;
        } else {
            this.raiz.inserirNaoCheio(elemento);
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

        for (int i = 0; i < no.n; i++) {
            if (!no.folha) {
                salvarNoParaArquivo(no.filhos[i], escritor);
            }
            escritor.write(no.elementos[i].getChave() + "\n");
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
            raiz.inserirNaoCheio(new Elemento(chave, "", "", "", "", "", "", "", "")); // Insere um Elemento com chave
        }

        return raiz;
    }

    private void obterTodosElementosRec(NoArvoreB no, List<Elemento> elementos) {
        int i;
        for (i = 0; i < no.n; i++) {
            if (!no.folha) {
                obterTodosElementosRec(no.filhos[i], elementos);
            }
            elementos.add(no.elementos[i]);
        }
        if (!no.folha) {
            obterTodosElementosRec(no.filhos[i], elementos);
        }
    }

    public List<Elemento> obterTodosElementos() {
        List<Elemento> elementos = new ArrayList<>();
        if (this.raiz != null) {
            obterTodosElementosRec(this.raiz, elementos);
        }
        return elementos;
    }

    public double calcularTotalGasto() {
        double totalGasto = 0.0;
        List<Elemento> elementos = obterTodosElementos();
        for (Elemento elemento : elementos) {
            totalGasto += elemento.getValorMensalDouble();
        }
        return totalGasto;
    }

    public void imprimirArvore() {
        if (this.raiz != null) {
            this.raiz.imprimirNo();
        }
    }
}

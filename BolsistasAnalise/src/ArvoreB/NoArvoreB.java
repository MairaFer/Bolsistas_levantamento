package ArvoreB;

public class NoArvoreB {
    int[] chaves;
    int t;
    NoArvoreB[] filhos;
    int n;
    boolean folha;

    public NoArvoreB(int t, boolean folha) {
        this.t = t;
        this.folha = folha;
        this.chaves = new int[2 * t - 1];
        this.filhos = new NoArvoreB[2 * t];
        this.n = 0;
    }

    public void percorrer() {
        int i;
        for (i = 0; i < this.n; i++) {
            if (!this.folha) {
                this.filhos[i].percorrer();
            }
            System.out.print(this.chaves[i] + " ");
        }

        if (!this.folha) {
            this.filhos[i].percorrer();
        }
    }

    public void imprimirNo() {
        int i;
        for (i = 0; i < this.n; i++) {
            if (!this.folha) {
                this.filhos[i].imprimirNo();
            }
            System.out.print(this.chaves[i] + " ");
        }

        if (!this.folha) {
            this.filhos[i].imprimirNo();
        }
    }

    public NoArvoreB buscar(int chave) {
        int i = 0;
        while (i < this.n && chave > this.chaves[i]) {
            i++;
        }

        if (i < this.n && chave == this.chaves[i]) {
            return this;
        }

        if (this.folha) {
            return null;
        } else {
            return this.filhos[i].buscar(chave);
        }
    }

    public void inserirNaoCheio(int chave) {
        int i = this.n - 1;
        if (this.folha) {
            while (i >= 0 && this.chaves[i] > chave) {
                this.chaves[i + 1] = this.chaves[i];
                i--;
            }
            this.chaves[i + 1] = chave;
            this.n++;
        } else {
            while (i >= 0 && this.chaves[i] > chave) {
                i--;
            }
            if (this.filhos[i + 1].n == 2 * this.t - 1) {
                this.dividirFilho(i + 1, this.filhos[i + 1]);
                if (this.chaves[i + 1] < chave) {
                    i++;
                }
            }
            this.filhos[i + 1].inserirNaoCheio(chave);
        }
    }

    public void dividirFilho(int i, NoArvoreB y) {
        NoArvoreB z = new NoArvoreB(y.t, y.folha);
        z.n = this.t - 1;

        for (int j = 0; j < this.t - 1; j++) {
            z.chaves[j] = y.chaves[j + this.t];
        }

        if (!y.folha) {
            for (int j = 0; j < this.t; j++) {
                z.filhos[j] = y.filhos[j + this.t];
            }
        }

        y.n = this.t - 1;

        for (int j = this.n; j >= i + 1; j--) {
            this.filhos[j + 1] = this.filhos[j];
        }

        this.filhos[i + 1] = z;

        for (int j = this.n - 1; j >= i; j--) {
            this.chaves[j + 1] = this.chaves[j];
        }

        this.chaves[i] = y.chaves[this.t - 1];
        this.n++;
    }

    public void remover(int chave) {
        int idx = this.encontrarChave(chave);

        if (idx < this.n && this.chaves[idx] == chave) {
            if (this.folha) {
                this.removerDeFolha(idx);
            } else {
                this.removerDeNaoFolha(idx);
            }
        } else {
            if (this.folha) {
                System.out.println("A chave " + chave + " não está na árvore.");
                return;
            }

            boolean flag = idx == this.n;

            if (this.filhos[idx].n < this.t) {
                this.preencher(idx);
            }

            if (flag && idx > this.n) {
                this.filhos[idx - 1].remover(chave);
            } else {
                this.filhos[idx].remover(chave);
            }
        }
    }

    private int encontrarChave(int chave) {
        int idx = 0;
        while (idx < this.n && this.chaves[idx] < chave) {
            idx++;
        }
        return idx;
    }

    private void removerDeFolha(int idx) {
        for (int i = idx + 1; i < this.n; i++) {
            this.chaves[i - 1] = this.chaves[i];
        }
        this.n--;
    }

    private void removerDeNaoFolha(int idx) {
        int chave = this.chaves[idx];

        if (this.filhos[idx].n >= this.t) {
            int pred = this.getPredecessor(idx);
            this.chaves[idx] = pred;
            this.filhos[idx].remover(pred);
        } else if (this.filhos[idx + 1].n >= this.t) {
            int suc = this.getSucessor(idx);
            this.chaves[idx] = suc;
            this.filhos[idx + 1].remover(suc);
        } else {
            this.juntar(idx);
            this.filhos[idx].remover(chave);
        }
    }

    private int getPredecessor(int idx) {
        NoArvoreB atual = this.filhos[idx];
        while (!atual.folha) {
            atual = atual.filhos[atual.n];
        }
        return atual.chaves[atual.n - 1];
    }

    private int getSucessor(int idx) {
        NoArvoreB atual = this.filhos[idx + 1];
        while (!atual.folha) {
            atual = atual.filhos[0];
        }
        return atual.chaves[0];
    }

    private void preencher(int idx) {
        if (idx != 0 && this.filhos[idx - 1].n >= this.t) {
            this.pegarEmprestadoAnterior(idx);
        } else if (idx != this.n && this.filhos[idx + 1].n >= this.t) {
            this.pegarEmprestadoProximo(idx);
        } else {
            if (idx != this.n) {
                this.juntar(idx);
            } else {
                this.juntar(idx - 1);
            }
        }
    }

    private void pegarEmprestadoAnterior(int idx) {
        NoArvoreB filho = this.filhos[idx];
        NoArvoreB irmao = this.filhos[idx - 1];

        for (int i = filho.n - 1; i >= 0; i--) {
            filho.chaves[i + 1] = filho.chaves[i];
        }

        if (!filho.folha) {
            for (int i = filho.n; i >= 0; i--) {
                filho.filhos[i + 1] = filho.filhos[i];
            }
        }

        filho.chaves[0] = this.chaves[idx - 1];

        if (!filho.folha) {
            filho.filhos[0] = irmao.filhos[irmao.n];
        }

        this.chaves[idx - 1] = irmao.chaves[irmao.n - 1];

        filho.n++;
        irmao.n--;
    }

    private void pegarEmprestadoProximo(int idx) {
        NoArvoreB filho = this.filhos[idx];
        NoArvoreB irmao = this.filhos[idx + 1];

        filho.chaves[filho.n] = this.chaves[idx];

        if (!filho.folha) {
            filho.filhos[filho.n + 1] = irmao.filhos[0];
        }

        this.chaves[idx] = irmao.chaves[0];

        for (int i = 1; i < irmao.n; i++) {
            irmao.chaves[i - 1] = irmao.chaves[i];
        }

        if (!irmao.folha) {
            for (int i = 1; i <= irmao.n; i++) {
                irmao.filhos[i - 1] = irmao.filhos[i];
            }
        }

        filho.n++;
        irmao.n--;
    }

    private void juntar(int idx) {
        NoArvoreB filho = this.filhos[idx];
        NoArvoreB irmao = this.filhos[idx + 1];

        filho.chaves[this.t - 1] = this.chaves[idx];

        for (int i = 0; i < irmao.n; i++) {
            filho.chaves[i + this.t] = irmao.chaves[i];
        }

        if (!filho.folha) {
            for (int i = 0; i <= irmao.n; i++) {
                filho.filhos[i + this.t] = irmao.filhos[i];
            }
        }

        for (int i = idx + 1; i < this.n; i++) {
            this.chaves[i - 1] = this.chaves[i];
        }

        for (int i = idx + 2; i <= this.n; i++) {
            this.filhos[i - 1] = this.filhos[i];
        }

        filho.n += irmao.n + 1;
        this.n--;
    }
}

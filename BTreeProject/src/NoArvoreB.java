class NoArvoreB {
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

    // Método de impressão
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
        int idx = encontrarChave(chave);

        if (idx < n && chaves[idx] == chave) {
            if (folha) {
                removerDeFolha(idx);
            } else {
                removerDeNaoFolha(idx);
            }
        } else {
            if (folha) {
                System.out.println("A chave " + chave + " não existe na árvore.");
                return;
            }

            boolean ultimoFilho = (idx == n);

            if (filhos[idx].n < t) {
                preencher(idx);
            }

            if (ultimoFilho && idx > n) {
                filhos[idx - 1].remover(chave);
            } else {
                filhos[idx].remover(chave);
            }
        }
    }

    private int encontrarChave(int chave) {
        int idx = 0;
        while (idx < n && chaves[idx] < chave) {
            idx++;
        }
        return idx;
    }

    private void removerDeFolha(int idx) {
        for (int i = idx + 1; i < n; ++i) {
            chaves[i - 1] = chaves[i];
        }
        n--;
    }

    private void removerDeNaoFolha(int idx) {
        int chave = chaves[idx];

        if (filhos[idx].n >= t) {
            int pred = obterPredecessor(idx);
            chaves[idx] = pred;
            filhos[idx].remover(pred);
        } else if (filhos[idx + 1].n >= t) {
            int succ = obterSucessor(idx);
            chaves[idx] = succ;
            filhos[idx + 1].remover(succ);
        } else {
            fundir(idx);
            filhos[idx].remover(chave);
        }
    }

    private int obterPredecessor(int idx) {
        NoArvoreB atual = filhos[idx];
        while (!atual.folha) {
            atual = atual.filhos[atual.n];
        }
        return atual.chaves[atual.n - 1];
    }

    private int obterSucessor(int idx) {
        NoArvoreB atual = filhos[idx + 1];
        while (!atual.folha) {
            atual = atual.filhos[0];
        }
        return atual.chaves[0];
    }

    private void preencher(int idx) {
        if (idx != 0 && filhos[idx - 1].n >= t) {
            pegarDoAnterior(idx);
        } else if (idx != n && filhos[idx + 1].n >= t) {
            pegarDoProximo(idx);
        } else {
            if (idx != n) {
                fundir(idx);
            } else {
                fundir(idx - 1);
            }
        }
    }

    private void pegarDoAnterior(int idx) {
        NoArvoreB filho = filhos[idx];
        NoArvoreB irmao = filhos[idx - 1];

        for (int i = filho.n - 1; i >= 0; --i) {
            filho.chaves[i + 1] = filho.chaves[i];
        }

        if (!filho.folha) {
            for (int i = filho.n; i >= 0; --i) {
                filho.filhos[i + 1] = filho.filhos[i];
            }
        }

        filho.chaves[0] = chaves[idx - 1];

        if (!filho.folha) {
            filho.filhos[0] = irmao.filhos[irmao.n];
        }

        chaves[idx - 1] = irmao.chaves[irmao.n - 1];
        filho.n += 1;
        irmao.n -= 1;
    }

    private void pegarDoProximo(int idx) {
        NoArvoreB filho = filhos[idx];
        NoArvoreB irmao = filhos[idx + 1];

        filho.chaves[filho.n] = chaves[idx];

        if (!filho.folha) {
            filho.filhos[filho.n + 1] = irmao.filhos[0];
        }

        chaves[idx] = irmao.chaves[0];

        for (int i = 1; i < irmao.n; ++i) {
            irmao.chaves[i - 1] = irmao.chaves[i];
        }

        if (!irmao.folha) {
            for (int i = 1; i <= irmao.n; ++i) {
                irmao.filhos[i - 1] = irmao.filhos[i];
            }
        }

        filho.n += 1;
        irmao.n -= 1;
    }

    private void fundir(int idx) {
        NoArvoreB filho = filhos[idx];
        NoArvoreB irmao = filhos[idx + 1];

        filho.chaves[t - 1] = chaves[idx];

        for (int i = 0; i < irmao.n; ++i) {
            filho.chaves[i + t] = irmao.chaves[i];
        }

        if (!filho.folha) {
            for (int i = 0; i <= irmao.n; ++i) {
                filho.filhos[i + t] = irmao.filhos[i];
            }
        }

        for (int i = idx + 1; i < n; ++i) {
            chaves[i - 1] = chaves[i];
        }

        for (int i = idx + 2; i <= n; ++i) {
            filhos[i - 1] = filhos[i];
        }

        filho.n += irmao.n + 1;
        n--;

        irmao = null;
    }

}
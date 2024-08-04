package ArvoreB;

public class NoArvoreB {
    Elemento[] elementos; // Array para armazenar os Elementos
    int[] chaves;         // Array para armazenar as chaves (números dos Elementos)
    int t;
    NoArvoreB[] filhos;
    int n;
    boolean folha;

    public NoArvoreB(int t, boolean folha) {
        this.t = t;
        this.folha = folha;
        this.chaves = new int[2 * t - 1];
        this.elementos = new Elemento[2 * t - 1]; // Inicializa o array de Elemento
        this.filhos = new NoArvoreB[2 * t];
        this.n = 0;
    }

    public void inserirNaoCheio(Elemento elemento) {
        int i = n - 1;
        int chave = elemento.getChave();
        if (folha) {
            while (i >= 0 && chaves[i] > chave) {
                chaves[i + 1] = chaves[i];
                elementos[i + 1] = elementos[i];
                i--;
            }
            chaves[i + 1] = chave;
            elementos[i + 1] = elemento;
            n++;
        } else {
            while (i >= 0 && chaves[i] > chave) {
                i--;
            }
            if (filhos[i + 1].n == 2 * t - 1) {
                dividirFilho(i + 1, filhos[i + 1]);
                if (chaves[i + 1] < chave) {
                    i++;
                }
            }
            filhos[i + 1].inserirNaoCheio(elemento);
        }
    }

    public void dividirFilho(int i, NoArvoreB y) {
        NoArvoreB z = new NoArvoreB(y.t, y.folha);
        z.n = t - 1;

        for (int j = 0; j < t - 1; j++) {
            z.chaves[j] = y.chaves[j + t];
            z.elementos[j] = y.elementos[j + t]; // Copia os elementos
        }

        if (!y.folha) {
            for (int j = 0; j < t; j++) {
                z.filhos[j] = y.filhos[j + t];
            }
        }

        y.n = t - 1;

        for (int j = n; j >= i + 1; j--) {
            filhos[j + 1] = filhos[j];
        }

        filhos[i + 1] = z;

        for (int j = n - 1; j >= i; j--) {
            chaves[j + 1] = chaves[j];
            elementos[j + 1] = elementos[j]; // Move os elementos
        }

        chaves[i] = y.chaves[t - 1];
        elementos[i] = y.elementos[t - 1]; // Adiciona o elemento no meio

        n++;
    }

    public NoArvoreB buscar(int chave) {
        int i = 0;
        while (i < n && chave > chaves[i]) {
            i++;
        }

        if (i < n && chaves[i] == chave) {
            return this;
        }

        return folha ? null : filhos[i].buscar(chave);
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

            boolean flag = idx == n;
            if (filhos[idx].n < t) {
                preencher(idx);
            }

            if (flag && idx > n) {
                filhos[idx - 1].remover(chave);
            } else {
                filhos[idx].remover(chave);
            }
        }
    }

    private int encontrarChave(int chave) {
        int idx = 0;
        while (idx < n && chaves[idx] < chave) {
            ++idx;
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
        int k = chaves[idx];

        if (filhos[idx].n >= t) {
            int pred = obterPredecessor(idx);
            chaves[idx] = pred;
            filhos[idx].remover(pred);
        } else if (filhos[idx + 1].n >= t) {
            int succ = obterSucessor(idx);
            chaves[idx] = succ;
            filhos[idx + 1].remover(succ);
        } else {
            juntar(idx);
            filhos[idx].remover(k);
        }
    }

    private int obterPredecessor(int idx) {
        NoArvoreB cur = filhos[idx];
        while (!cur.folha) {
            cur = cur.filhos[cur.n];
        }
        return cur.chaves[cur.n - 1];
    }

    private int obterSucessor(int idx) {
        NoArvoreB cur = filhos[idx + 1];
        while (!cur.folha) {
            cur = cur.filhos[0];
        }
        return cur.chaves[0];
    }

    private void preencher(int idx) {
        if (idx != 0 && filhos[idx - 1].n >= t) {
            pegarEmprestadoAnterior(idx);
        } else if (idx != n && filhos[idx + 1].n >= t) {
            pegarEmprestadoProximo(idx);
        } else {
            if (idx != n) {
                juntar(idx);
            } else {
                juntar(idx - 1);
            }
        }
    }

    private void pegarEmprestadoAnterior(int idx) {
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

    private void pegarEmprestadoProximo(int idx) {
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

    private void juntar(int idx) {
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
    }

    public void imprimirNo() {
        for (int i = 0; i < this.n; i++) {
            System.out.print(this.chaves[i] + " ");
        }
        if (!this.folha) {
            for (int i = 0; i <= this.n; i++) {
                if (this.filhos[i] != null) {
                    this.filhos[i].imprimirNo();
                }
            }
        }
    }
}

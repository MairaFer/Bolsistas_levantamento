package btree;

public class BTreeNode {
    int[] keys;  // Array de chaves
    int t;  // Grau mínimo
    BTreeNode[] children;  // Array de ponteiros para filhos
    int n;  // Número de chaves atualmente no nó
    boolean leaf;  // Verdadeiro se o nó é folha

    // Construtor
    public BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new int[2 * t - 1];
        this.children = new BTreeNode[2 * t];
        this.n = 0;
    }

    // Função para percorrer todos os nós em uma subárvore enraizada neste nó
    public void traverse() {
        int i;
        for (i = 0; i < n; i++) {
            if (!leaf) {
                children[i].traverse();
            }
            System.out.print(" " + keys[i]);
        }
        if (!leaf) {
            children[i].traverse();
        }
    }

    // Função para buscar uma chave em uma subárvore enraizada neste nó
    public BTreeNode search(int key) {
        int i = 0;
        while (i < n && key > keys[i]) {
            i++;
        }
        if (keys[i] == key) {
            return this;
        }
        if (leaf) {
            return null;
        }
        return children[i].search(key);
    }

    // Função para inserir uma nova chave neste nó não cheio
    public void insertNonFull(int key) {
        int i = n - 1;

        if (leaf) {
            while (i >= 0 && keys[i] > key) {
                keys[i + 1] = keys[i];
                i--;
            }
            keys[i + 1] = key;
            n = n + 1;
        } else {
            while (i >= 0 && keys[i] > key) {
                i--;
            }
            if (children[i + 1].n == 2 * t - 1) {
                splitChild(i + 1, children[i + 1]);

                if (keys[i + 1] < key) {
                    i++;
                }
            }
            children[i + 1].insertNonFull(key);
        }
    }

    // Função para dividir o filho y deste nó
    public void splitChild(int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(y.t, y.leaf);
        z.n = t - 1;

        for (int j = 0; j < t - 1; j++) {
            z.keys[j] = y.keys[j + t];
        }

        if (!y.leaf) {
            for (int j = 0; j < t; j++) {
                z.children[j] = y.children[j + t];
            }
        }

        y.n = t - 1;

        for (int j = n; j >= i + 1; j--) {
            children[j + 1] = children[j];
        }

        children[i + 1] = z;

        for (int j = n - 1; j >= i; j--) {
            keys[j + 1] = keys[j];
        }

        keys[i] = y.keys[t - 1];

        n = n + 1;
    }
}

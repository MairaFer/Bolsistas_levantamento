package btree;

public class BTree {
    BTreeNode root;  
    int t;  // Grau mínimo

    // Construtor
    public BTree(int t) {
        this.root = null;
        this.t = t;
    }

    // Função para percorrer a árvore
    public void traverse() {
        if (root != null) {
            root.traverse();
            System.out.println();
        }
    }

    // Função para buscar uma chave na árvore
    public BTreeNode search(int key) {
        return (root == null) ? null : root.search(key);
    }

    // Função para inserir uma nova chave na árvore
    public void insert(int key) {
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = key;
            root.n = 1;
        } else {
            if (root.n == 2 * t - 1) {
                BTreeNode s = new BTreeNode(t, false);
                s.children[0] = root;
                s.splitChild(0, root);
                int i = 0;
                if (s.keys[0] < key) {
                    i++;
                }
                s.children[i].insertNonFull(key);
                root = s;
            } else {
                root.insertNonFull(key);
            }
        }
    }

    // Função para remover uma chave na árvore
    public void remove(int key) {
        if (root == null) {
            System.out.println("The tree is empty\n");
            return;
        }

        root.remove(key);

        if (root.n == 0) {
            if (root.leaf) {
                root = null;
            } else {
                root = root.children[0];
            }
        }
    }
}

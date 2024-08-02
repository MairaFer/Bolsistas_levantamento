package btree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
            System.out.println("A árvore está vazia.");
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

    // Função para salvar a árvore em um arquivo
    public void saveToFile(String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        if (root != null) {
            saveNodeToFile(root, bufferedWriter);
        }

        bufferedWriter.close();
        fileWriter.close();
    }

    private void saveNodeToFile(BTreeNode node, BufferedWriter writer) throws IOException {
        writer.write(node.n + " ");
        for (int i = 0; i < node.n; i++) {
            writer.write(node.keys[i] + " ");
        }
        writer.newLine();
        if (!node.leaf) {
            for (int i = 0; i <= node.n; i++) {
                saveNodeToFile(node.children[i], writer);
            }
        }
    }

    // Função para ler a árvore de um arquivo
    public void loadFromFile(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        root = loadNodeFromFile(bufferedReader);

        bufferedReader.close();
        fileReader.close();
    }

    private BTreeNode loadNodeFromFile(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return null;
        }

        String[] parts = line.split(" ");
        int n = Integer.parseInt(parts[0]);
        BTreeNode node = new BTreeNode(t, n == 0);

        for (int i = 0; i < n; i++) {
            node.keys[i] = Integer.parseInt(parts[i + 1]);
        }
        node.n = n;

        if (!node.leaf) {
            for (int i = 0; i <= n; i++) {
                node.children[i] = loadNodeFromFile(reader);
            }
        }

        return node;
    }
}

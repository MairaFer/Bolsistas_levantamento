package teste;

import btree.BTree;

public class Main {
    public static void main(String[] args) {
        BTree t = new BTree(3); // Cria uma Árvore B com grau mínimo 3

        t.insert(10);
        t.insert(20);
        t.insert(5);
        t.insert(6);
        t.insert(12);
        t.insert(30);
        t.insert(7);
        t.insert(17);

        System.out.println("Traversal of the constructed tree is ");
        t.traverse();

        int key = 6;
        if (t.search(key) != null) {
            System.out.println("\nKey " + key + " is present in the tree.");
        } else {
            System.out.println("\nKey " + key + " is not present in the tree.");
        }
    }
}

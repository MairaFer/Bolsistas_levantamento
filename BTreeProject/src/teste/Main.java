package teste;

import btree.BTree;

public class Main {
    public static void main(String[] args) {
        BTree t = new BTree(3);

        t.insert(10);
        t.insert(20);
        t.insert(5);
        t.insert(6);
        t.insert(12);
        t.insert(30);
        t.insert(7);
        t.insert(17);

        System.out.println("Traversal of the constructed tree is:");
        t.traverse();

        t.remove(6);
        System.out.println("Traversal after removing 6:");
        t.traverse();

        t.remove(13);
        System.out.println("Traversal after removing 13:");
        t.traverse();

        t.remove(7);
        System.out.println("Traversal after removing 7:");
        t.traverse();

        t.remove(4);
        System.out.println("Traversal after removing 4:");
        t.traverse();

        t.remove(2);
        System.out.println("Traversal after removing 2:");
        t.traverse();

        t.remove(16);
        System.out.println("Traversal after removing 16:");
        t.traverse();
    }
}

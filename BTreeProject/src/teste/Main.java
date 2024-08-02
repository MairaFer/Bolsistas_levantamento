package teste;

import btree.BTree;
import btree.BTreeNode;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o grau mínimo da árvore B: ");
        int t = scanner.nextInt();
        BTree btree = new BTree(t);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Inserir chave");
            System.out.println("2. Remover chave");
            System.out.println("3. Buscar chave");
            System.out.println("4. Imprimir a árvore");
            System.out.println("5. Salvar árvore em arquivo");
            System.out.println("6. Carregar árvore de arquivo");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Digite a chave a ser inserida: ");
                    int keyToInsert = scanner.nextInt();
                    btree.insert(keyToInsert);
                    System.out.println("Chave inserida.");
                    break;
                case 2:
                    System.out.print("Digite a chave a ser removida: ");
                    int keyToRemove = scanner.nextInt();
                    btree.remove(keyToRemove);
                    System.out.println("Chave removida.");
                    break;
                case 3:
                    System.out.print("Digite a chave a ser buscada: ");
                    int keyToSearch = scanner.nextInt();
                    BTreeNode result = btree.search(keyToSearch);
                    if (result != null) {
                        System.out.println("Chave encontrada.");
                    } else {
                        System.out.println("Chave não encontrada.");
                    }
                    break;
                case 4:
                    System.out.println("Percorrendo a árvore:");
                    btree.traverse();
                    System.out.println();
                    break;
                case 5:
                    System.out.print("Digite o nome do arquivo para salvar a árvore: ");
                    String saveFilename = scanner.next();
                    try {
                        btree.saveToFile(saveFilename);
                        System.out.println("Árvore salva no arquivo " + saveFilename);
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar a árvore no arquivo.");
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    System.out.print("Digite o nome do arquivo para carregar a árvore: ");
                    String loadFilename = scanner.next();
                    try {
                        btree.loadFromFile(loadFilename);
                        System.out.println("Árvore carregada do arquivo " + loadFilename);
                    } catch (IOException e) {
                        System.out.println("Erro ao carregar a árvore do arquivo.");
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}

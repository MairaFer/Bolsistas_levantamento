package Analise;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import ArvoreB.ArvoreB;
import ArvoreB.Elemento;
import Util.LeitorCsv;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        // Crie uma instância da árvore B com uma ordem t adequada
        ArvoreB arvore = new ArvoreB(3); // Por exemplo, ordem 3

        // Leia os dados do CSV
        List<Elemento> elementos = LeitorCsv.lerDadosCsv("C:\\Users\\ferna\\OneDrive\\Área de Trabalho\\Atv05_MairaFernanda\\BolsistasAnalise\\src\\ENTRADA\\BolsistasDados2020-01.csv");

        // Insira os dados na árvore
        for (Elemento elemento : elementos) {
            arvore.inserir(elemento);
        }

        // Calcule o total gasto
        double totalGasto = arvore.calcularTotalGasto();
        System.out.println("Total gasto em apoio à extensão universitária: R$ " + totalGasto);

        // Imprimir a árvore (opcional)
        arvore.imprimirArvore();
    }
}

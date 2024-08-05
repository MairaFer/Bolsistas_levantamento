package Analise;

import java.io.File;
import ArvoreB.ArvoreB;
import ArvoreB.Elemento;
import Util.LeitorCsv;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        // Crie uma instância da árvore B com uma ordem t adequada
        ArvoreB arvore = new ArvoreB(3); // Por exemplo, ordem 3
        LeitorCsv leitor = new LeitorCsv();


        // Leia os dados do CSV
        File file = new File("src\\ENTRADA"); //Variavel File que recebe caminho de todos os arquivos
        for (final File fileEntrada : file.listFiles()){ //Le todos os arquivos no caminho indicado e retorna o nome de cada um a cada interação
            List<Elemento> elementos = leitor.lerDadosCsv("src\\ENTRADA\\"+fileEntrada.getName()); // Abre e le o arquivo
            // Insira os dados na árvore
            for (Elemento elemento : elementos) {
                arvore.inserir(elemento);
            }
        }

        // Calcule o total gasto
        double totalGasto = arvore.calcularTotalGasto();
        System.out.println("Total gasto em apoio à extensão universitária: R$ " + totalGasto);

        // Imprimir a árvore (opcional)
        arvore.imprimirArvore();
    }
}

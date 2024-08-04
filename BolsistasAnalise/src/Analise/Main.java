package Analise;

import ArvoreB.ArvoreB;
import ArvoreB.Elemento;
import Util.LeitorCsv;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crie uma instância da Árvore B
        ArvoreB arvore = new ArvoreB(4); // Supondo ordem 4, ajuste conforme necessário

        // Ler dados do CSV
        List<Elemento> elementos = LeitorCsv.lerDadosCsv("C:\\Users\\ferna\\OneDrive\\Área de Trabalho\\Atv05_MairaFernanda\\BolsistasAnalise\\src\\ENTRADA\\BolsistasDados2020-01.csv");

        // Inserir cada elemento na árvore
        for (Elemento elemento : elementos) {
            arvore.inserir(elemento); // Supondo que você tenha um método inserir na árvore
        }

        // Exibir a árvore para verificar a inserção
        arvore.imprimirArvore();
    }
}



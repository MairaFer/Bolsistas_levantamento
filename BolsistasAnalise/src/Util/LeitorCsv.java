package Util;

import ArvoreB.Elemento;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorCsv {
    public List<Elemento> lerDadosCsv(String caminhoArquivo) {
        List<Elemento> elementos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            String[] dados;
            // Ignorar o cabeçalho
            br.readLine();
            while ((linha = br.readLine()) != null) {
                dados = linha.split(";");
                try {
                    //tenta separar os dados por ponto e virgula
                    elementos=alimentaElemento(elementos,dados);
                }catch(NumberFormatException e) {
                    // caso seja lançado erro, tenta separar os dados pela virgula
                    dados = linha.split(",");
                    elementos=alimentaElemento(elementos,dados);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return elementos;
    }
    //Função que insere os dados de cada elemento, caso ocorra erro ao separar os dados lança exceção
    public List<Elemento> alimentaElemento(List<Elemento> elementos, String[] dados) throws NumberFormatException{

        Elemento elemento = new Elemento(
                Integer.parseInt(dados[0].trim()),
                dados[1].trim(),
                dados[2].trim(),
                dados[3].trim(),
                dados[4].trim(),
                dados[5].trim(),
                dados[6].trim(),
                dados[7].trim(),
                dados[8].trim()
        );
        elementos.add(elemento);

        return elementos;
    }

}

package Util;

import ArvoreB.Elemento;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorCsv {
    public static List<Elemento> lerDadosCsv(String caminhoArquivo) {
        List<Elemento> elementos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            // Ignorar o cabeçalho
            br.readLine();
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return elementos;
    }
}

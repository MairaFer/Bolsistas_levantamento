package ArvoreB;

public class Elemento {
    private int numero;
    private String nome;
    private String cpf;
    private String curso;
    private String numRegistroAtividade;
    private String edital;
    private String valorMensal;
    private String periodoRecebimento;
    private String instituicaoResponsavel;

    public Elemento(int numero, String nome, String cpf, String curso, String numRegistroAtividade,
                    String edital, String valorMensal, String periodoRecebimento, String instituicaoResponsavel) {
        this.numero = numero;
        this.nome = nome;
        this.cpf = cpf;
        this.curso = curso;
        this.numRegistroAtividade = numRegistroAtividade;
        this.edital = edital;
        this.valorMensal = valorMensal;
        this.periodoRecebimento = periodoRecebimento;
        this.instituicaoResponsavel = instituicaoResponsavel;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCurso() {
        return curso;
    }

    public String getNumRegistroAtividade() {
        return numRegistroAtividade;
    }

    public String getEdital() {
        return edital;
    }

    public String getValorMensal() {
        return valorMensal;
    }

    public String getPeriodoRecebimento() {
        return periodoRecebimento;
    }

    public String getInstituicaoResponsavel() {
        return instituicaoResponsavel;
    }

    public double getValorMensalDouble() {
        try {
            // Remove o símbolo de aspas (""), simbolo de real "R$" e espaços, também substitui a vírgula por ponto
            String valorSemFormato = valorMensal.replace("\"","").replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");
            return Double.parseDouble(valorSemFormato);
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter valor mensal: " + valorMensal);
            return 0.0;
        }
    }

    public int getChave() {
        return numero; // chave
    }
}

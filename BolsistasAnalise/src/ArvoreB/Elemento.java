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

    // Construtor
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

    // Getters e Setters (opcional, mas recomendado)
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public String getNumRegistroAtividade() { return numRegistroAtividade; }
    public void setNumRegistroAtividade(String numRegistroAtividade) { this.numRegistroAtividade = numRegistroAtividade; }

    public String getEdital() { return edital; }
    public void setEdital(String edital) { this.edital = edital; }

    public String getValorMensal() { return valorMensal; }
    public void setValorMensal(String valorMensal) { this.valorMensal = valorMensal; }

    public String getPeriodoRecebimento() { return periodoRecebimento; }
    public void setPeriodoRecebimento(String periodoRecebimento) { this.periodoRecebimento = periodoRecebimento; }

    public String getInstituicaoResponsavel() { return instituicaoResponsavel; }
    public void setInstituicaoResponsavel(String instituicaoResponsavel) { this.instituicaoResponsavel = instituicaoResponsavel; }

    @Override
    public String toString() {
        return "Elemento{" +
                "numero=" + numero +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", curso='" + curso + '\'' +
                ", numRegistroAtividade='" + numRegistroAtividade + '\'' +
                ", edital='" + edital + '\'' +
                ", valorMensal='" + valorMensal + '\'' +
                ", periodoRecebimento='" + periodoRecebimento + '\'' +
                ", instituicaoResponsavel='" + instituicaoResponsavel + '\'' +
                '}';
    }
    public int getChave() {
        return numero; // chave
    }

}

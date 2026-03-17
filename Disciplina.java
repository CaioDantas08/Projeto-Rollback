import java.util.ArrayList;

enum Status_disciplina{
    aprovado,
    reprovado,
    cursando,
    trancada
}

public class Disciplina {
    private String nome;
    private Status_disciplina status;
    private int periodo_ideal = 0;
    private int carga_horaria = 0;
    private String professor;
    private ArrayList<Double> notas = new ArrayList<Double>();
    private double media = 0.0;
    private int faltas = 0;

    // SETTERS
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setStatus(Status_disciplina status) {
        this.status = status;
    }

    public void setPeriodo_ideal(int periodo_ideal) {
        this.periodo_ideal = periodo_ideal;
    }

    public void setCarga_horaria(int carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setNotas(ArrayList<Double> notas) {
        this.notas = notas;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    // GETTERS
    public String getNome() {
        return nome;
    }

    public Status_disciplina getStatus() {
        return status;
    }

    public int getPeriodo_ideal() {
        return periodo_ideal;
    }

    public int getCarga_horaria() {
        return carga_horaria;
    }

    public String getProfessor() {
        return professor;
    }

    public ArrayList<Double> getNotas() {
        return notas;
    }

    public double getMedia() {
        return media;
    }

    public int getFaltas() {
        return faltas;
    }
    ///////
    
    public void addNota(double nota) {
        this.notas.add(nota);
    }

    public double calcularMedia() {

        if (notas.isEmpty()) return 0.0;
        double soma = 0;
        for (double n : notas) {
            soma += n;
        }
        return soma / notas.size();
    }
}

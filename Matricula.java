import java.util.ArrayList;

enum situacao_matricula{
    ativa,
    trancada,
    cancelada,
    aprovada,
    reprovada,
    nao_matriculado,
    concluido
}

public class Matricula {
    private double ira = 0.0;
    private int semestre = 0;
    private int faltas_totais = 0;    
    private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>(); 
    private situacao_matricula situacao;

    // SETTERS
    public void setIra(double ira) {
        this.ira = ira;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public void setFaltas_totais(int faltas_totais) {
        this.faltas_totais = faltas_totais;
    }

    public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public void setSituacao(situacao_matricula situacao) {
        this.situacao = situacao;
    }

    // GETTERS
    public double getIra() {
        return ira;
    }

    public int getSemestre() {
        return semestre;
    }

    public int getFaltas_totais() {
        return faltas_totais;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public situacao_matricula getSituacao() {
        return situacao;
    }

    //////
    
    public void addDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

    public void removerDisciplina(Disciplina disciplina) {
        this.disciplinas.remove(disciplina);
    }

}

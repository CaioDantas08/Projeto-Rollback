import java.util.ArrayList;


enum NivelRisco{
    alto,
    medio,
    baixo
}

public class PerfilRisco{

    private double scoreRisco = 0.0;
    private NivelRisco nivel;
    private ArrayList<String> fatores = new ArrayList<String>(); //Fatores que aumentam o risco
    private ArrayList<String> recomendacoes = new ArrayList<String>(); 

    //SETTERS

    public void setScoreRisco(double scoreRisco) {
        this.scoreRisco = scoreRisco;
    }

    public void setNivel(NivelRisco nivel) {
        this.nivel = nivel;
    }

    public void setFatores(ArrayList<String> fatores) {
        this.fatores = fatores;
    }

    public void setRecomendacoes(ArrayList<String> recomendacoes) {
        this.recomendacoes = recomendacoes;
    }

    //GETTERS

    public double getScoreRisco() {
        return scoreRisco;
    }

    public NivelRisco getNivel() {
        return nivel;
    }

    public ArrayList<String> getFatores() {
        return fatores;
    }

    public ArrayList<String> getRecomendacoes() {
        return recomendacoes;
    }

    //////
    
    public void addFator(String fator) {
        this.fatores.add(fator);
    }

    public void addRecomendacao(String recomendacao) {
        this.recomendacoes.add(recomendacao);
    }


}
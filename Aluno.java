import java.util.ArrayList;

enum Status{
    ativo,
    trancado,
    formado, 
    desvinculado,
    cancelado, 
    jubilado,
    transferido
}

public class Aluno{
    private Status status;
    private String semestre_ingresso;
    private String nome;
    private int idade = 0;
    private ArrayList<Matricula> matriculas = new ArrayList<Matricula>(); 

    public Aluno() {
    }

    //SETTERS 
    public void setStatus(Status status){
        this.status = status;
    }
    public void setSemestre_ingresso(String semestre_ingresso){
        this.semestre_ingresso = semestre_ingresso;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setIdade(int idade){
        this.idade = idade;
    }
    public void setMatriculas(ArrayList<Matricula> matriculas){
        this.matriculas = matriculas;
    }

    //GETTERS 
    public Status getStatus(){
        return status;
    }
    public String getSemestre_ingresso(){
        return semestre_ingresso;
    }
    public String getNome(){
        return nome;
    }
    public int getIdade(){
        return idade;
    }
    public ArrayList<Matricula> getMatriculas(){
        return matriculas;
    }

    //////
    
public void addMatricula(Matricula matricula) {
    this.matriculas.add(matricula);
}

public void removerMatricula(Matricula matricula) {
    this.matriculas.remove(matricula);
}

   
}
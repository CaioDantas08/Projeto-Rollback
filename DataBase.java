import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase {

    private List<Aluno> alunos = new ArrayList<>();
    private Map<Aluno, PerfilRisco> mapaRisco = new HashMap<>();


    public void addAluno(Aluno aluno) {
        if(aluno.getStatus() == Status.ativo || aluno.getStatus() == Status.trancado){
            alunos.add(aluno);
        }else{
            System.out.println("Aluno não ativo. Impossível adicionar " + aluno.getNome() + ".") ;
        }
    }

    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
        mapaRisco.remove(aluno);
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }
    //Atualiza o risco do aluno
    public void atualizarPerfilRisco(Aluno aluno, PerfilRisco perfil) {
        mapaRisco.put(aluno, perfil);
    }
    //Pega o risco do aluno
    public PerfilRisco getPerfilRisco(Aluno aluno) {
        return mapaRisco.get(aluno);
    }

    public Map<Aluno, PerfilRisco> getMapaRisco() {
        return mapaRisco;
    }
}
# Rollback

API com foco em predição de desistência nos cursos de Tecnologia da UFRN (BTI/IMD).

O sistema tem como objetivo analisar o histórico acadêmico dos alunos para identificar padrões de risco e prever a probabilidade de desistência, permitindo intervenções antes que o aluno abandone o curso.

---

## Tecnologias

- Java 21
- Spring Boot 4.0
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

---

## Como rodar

**Pré-requisitos:** Java 21, PostgreSQL rodando localmente.

1. Clone o repositório:
```bash
git clone https://github.com/CaioDantas08/Projeto-Rollback.git
cd Projeto-Rollback
```

2. Configure o banco no `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/rollback
spring.datasource.username=postgres
spring.datasource.password=postgres
```

3. Rode o projeto:
```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

---

## Endpoints disponíveis

### Alunos
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/api/alunos` | Lista todos os alunos |
| GET | `/api/alunos/{id}` | Busca aluno por ID |
| GET | `/api/alunos/status/{status}` | Lista alunos por status |
| POST | `/api/alunos` | Cadastra um novo aluno |
| DELETE | `/api/alunos/{id}` | Remove um aluno |

### Status disponíveis
`ATIVO` `TRANCADO` `FORMADO` `DESVINCULADO` `CANCELADO` `JUBILADO` `TRANSFERIDO`

---

## Concorrencia e desempenho

O calculo de alunos em risco usa um `ExecutorService` com pool fixo configurado pelo Spring. A busca no banco continua sincronizada com Spring Data JPA; depois que os alunos e seus historicos sao carregados na transacao, o calculo de risco de cada aluno e distribuido entre threads do pool.

Essa escolha evita paralelizar acesso ao `EntityManager`, que nao e seguro para uso concorrente, e aplica concorrencia apenas no processamento em memoria. O codigo tambem usa lambdas para submeter tarefas, filtrar resultados e manter a validacao de campos organizada.

Boas praticas aplicadas:

- pool controlado como bean Spring, com shutdown automatico;
- restauracao da interrupcao da thread em caso de `InterruptedException`;
- separacao entre acesso ao banco e processamento paralelo;
- uso de streams e lambdas onde melhoram clareza sem alterar a regra de negocio.

---

## Estrutura do projeto

```
src/main/java/com/rollback/api_alunos/
├── model/
│   ├── enums/        # StatusAluno, NivelRisco, StatusDisciplina, SituacaoMatricula
│   ├── Aluno.java
│   ├── Disciplina.java
│   ├── Matricula.java
│   └── PerfilRisco.java
├── repository/       # AlunoRepository, PerfilRiscoRepository
├── service/          # AlunoService
├── controller/       # AlunoController
└── analysis/         # (para ser desenvolvido) AnalisadorPadroes, PreditorRisco
```

---

## Licença

MIT License — veja o arquivo [LICENSE](LICENSE) para mais detalhes.

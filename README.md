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
# SPD-BES-2026-2-ORM-PROJETO
Repositório Git com os seguintes artefatos inspirados em um potencial projeto do seu grupo

## Grupo

Nicole - 202302625
Victor Gabriel - 202302631
Matheus Augusto - 202305532
Marcello Ronald - 202302618

## Descrição da atividade 
> Baseado nos tutoriais e slides da aula, forneça um repositório Git com os seguintes artefatos inspirados em um potencial projeto do seu grupo:
> 1. Diagrama das classes: envolva entidades que tenham pelo menos as relações 1:N, 1:1 e N:M. O artefato deve ser criado em PlantUML. Apresente a imagem e o código-fonte puml.
> 2. Diagrama E-R: mapeamento das classes em tabelas (ORM).
> 3. Camada de persistência: código-fonte das classes mapeadas em tabelas, conforme a modelagem realizada nos diagramas;
> 4. Notebook Jupyter: testes interativos da camada de persistência, avaliando o funcionamento da camada de persistência.
> Use ORMLite, JPA ou outro framework compatível com a linguagem utilizada (Python ou Java)
> Este exercício pode ser realizado em laboratório por até 5 membros. Caso seja realizado parcialmente entregue parcial do que foi desenvolvido até no final da aula o arquivo .zip do download do projeto. Caso tenha pendências, o restante deve ser entregue individualmente.

---

# DOMÍNIO - OFICINA MECÂNICA

## LINGUAGEM - JAVA

### Diagrama de classes

#### Entidades Exemplos

> 1:N : Cliente - Veiculo
> 1:1 : ordemService - notaFiscal
> N:M : Mecanico - ordemServico

![Diagrama Classes](https://github.com/mronaldjs/SPD-BES-2026-2-ORM-PROJETO/blob/main/docs/diagrams/Classes/diagramClass.png)

### Diagrama Entidade Relacionamento

![Diagrama Entidade Relacionamento](https://github.com/mronaldjs/SPD-BES-2026-2-ORM-PROJETO/blob/main/docs/diagrams/ER/diagramER.png)

### Camada de persistência (JPA / Hibernate)

Código-fonte das entidades mapeadas conforme o diagrama E-R, com DAOs e banco H2.

```
src/main/java/br/ufg/oficina/
├── model/          # Cliente, Veiculo, OrdemServico, NotaFiscal, Mecanico, Servico, Peca, Item*
├── dao/            # GenericDao, ClienteDao, OrdemServicoDao
├── util/JPAUtil.java
└── Main.java       # demonstração das relações 1:N, 1:1 e N:M
```

Requisitos: **JDK 21+** e **Maven 3.9+**.

```bash
mvn compile exec:java
```

### Notebook Jupyter — testes interativos

Arquivo: [`notebooks/testes_persistencia.ipynb`](notebooks/testes_persistencia.ipynb)

Valida interativamente o mesmo esquema E-R (1:N, 1:1 e N:M) com SQLAlchemy.

```bash
uv venv .venv
uv pip install sqlalchemy jupyter ipykernel
source .venv/bin/activate
jupyter notebook notebooks/testes_persistencia.ipynb
```

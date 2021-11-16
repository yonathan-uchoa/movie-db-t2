# TheMovieDB - Mobile

## Estudantes:
Felipe Sorvenigo
Matheus Neri
Pedro Lopez Novais
Yonathan Uchoa Simao Kaveski

   Projeto referente ao segundo trabalho do curso de Programacao para dispositivos moveis, do segundo semestre de 2021;


## **Visao Geral:**

   Realizar a pesquisa de filmes e series utilizando uma API disponibilizada gratuitamente na internet (TheMovieDB), onde usuario nao registrados podem fazer a pesquisa dentro desse catalogo retornando os valores padroes da API e os cadastrados podem realizar mais funcoes, como favoritar e dar suas proprias notas ao programa.


## **Papeis:**

   Usuario nao cadastrado: Pode realizar pesquisas sobre filmes e series;

   Usuario cadastrado: Alem de realizar as pesquisas padroes, salvar os filmes/series que deseja como favorito, colocando um valor de assistido ou desejo assistir, alem de notas pessoais e uma critica pessoal referente aos filmes/series.

## **Requisitos Funcionais:**

### **Geral:**
- **Pesquisa:** Possibilida a opcao de pesquisar filmes e/ou series pelo nome.

### Usuarios Cadastrados:
- **Favoritar programa:** Possibilida a opcao de salvar o filme como favorito.
- **Visualizado:** Opcao de marcar que um programa ja foi assistido ou que deseja assistir.
- **Avaliar programa:** Possibilida a opcao de dar uma nota pessoal a um programa ja assistido.
- **Realizar critica:** Possibilida a opcao de realizar uma critica a um programa ja assistido.
- **Catalogo local:** Possui a opcao de salvar localmente o catalogo de programas favoritos.


### **Requisitos Nao-funcionais:**
- **Linguagem:** Java Android
- **Banco de dados:** sera utilizado SQLite, junto a ORM greenDAO.
- **Conexao:** Exige conexao com a internet para efetuar as pesquisas.
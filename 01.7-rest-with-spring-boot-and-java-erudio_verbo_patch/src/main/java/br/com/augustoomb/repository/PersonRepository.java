package br.com.augustoomb.repository;

import br.com.augustoomb.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Long> {

    /*
        Por padrão, o Spring Data JPA espera que as consultas criadas com @Query sejam do tipo SELECT.
        A anotação @Modifying avisa ao Spring que essa consulta irá alterar os dados (INSERT, UPDATE ou DELETE).
        Sem ela, o Spring lançará uma exceção ao tentar executar o metodo.

        --

        @Query("UPDATE Person p SET p.enabled = false WHERE p.id =:id")
            Aqui você está usando JPQL (Java Persistence Query Language).
            Diferente do SQL puro, o JPQL foca nas suas classes Java (Entidades) e não nas tabelas do banco.

            UPDATE Person p: Refere-se à classe Person.

            SET p.enabled = false: Altera o atributo enabled para falso.

            WHERE p.id = :id: Filtra pelo ID. O trecho :id é um named parameter (parâmetro nomeado).

        --

        void disabledPerson(@Param("id") Long id);
            @Param("id"): Faz a ponte entre o argumento do metodo (Long id) e o parâmetro da consulta JPQL (:id).
            void: Como é uma operação de atualização, o metodo geralmente não retorna a entidade, apenas executa a ação.

        --

        Por que usar assim em vez do save()?
            Normalmente, para atualizar um registro no JPA, você faria:

                Buscar o objeto no banco (findById).
                Alterar o valor no objeto Java (person.setEnabled(false)).
                Salvar de volta (repository.save(person)).

            Vantagens dessa abordagem com @Query:

                Performance: Você executa apenas um comando SQL diretamente no banco de dados. Na forma tradicional, seriam dois (um SELECT e um UPDATE).
                Eficiência de Memória: Você não precisa carregar o objeto inteiro para a memória da aplicação apenas para mudar um campo booleano.

        clearAutomatically: evita problemas com cache lá no service. Na linha  var entity = repository.findById(id).get()

            ele não vai no banco buscar. Ele usa o cache. Se eu não usar o clearAutomatically = true, dá problema.
     */

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Person p SET p.enabled = false WHERE p.id =:id")
    void disabledPerson(@Param("id") Long id);
}

package io.github.Alexandregabriell.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity // JPA escaneia a entendidade e resgistre como tabela no banco de dados
@Table( name = "cliente")// utilizar se a tabela do banco nao for o mesmo da classe, define a tabela como esta no banco de dados
public class Cliente {

    @Id // define a primary key da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // para reprensentar o auto incremento do banco de dados
    @Column (name = "id") // definições da coluna que esta sendo mapeada. Obs: utilizar se o nome da coluna for diferente
    private Integer id;

    @Column(name = "nome", length = 100)
    @NotEmpty(message = "{campo.nome.obrigatotorio}")
    private String nome;

    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "{campo.cpf.obrigatotorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

}

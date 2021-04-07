package io.github.Alexandregabriell.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "produto")

public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "{campo.descricao.obrigatotorio}")
    @Column(name = "descricao")
    private String descricao;

    @NotNull(message = "{campo.preco.obrigatotorio}")
    @Column(name = "preco_unitario")
    private BigDecimal preco;
}

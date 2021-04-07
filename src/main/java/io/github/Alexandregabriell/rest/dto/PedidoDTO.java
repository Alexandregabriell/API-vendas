package io.github.Alexandregabriell.rest.dto;

import io.github.Alexandregabriell.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/*{
    "cliente": 1,
    "total": 100,
    "items": [
        {
            "produto": 1,
            "quantidade": 10
        }
    ]
}*/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDTO {

    @NotNull(message = "{campo.codigo-cliente.obrigatotorio}")
    private Integer cliente;

    @NotNull(message = "{campo.total-pedido.obrigatotorio}")
    private BigDecimal total;

    @NotEmptyList(message = "{campo.items-pedido.obrigatotorio}")
    private List<ItemsPedidoDTO> items;

}

package io.github.Alexandregabriell.service;

import io.github.Alexandregabriell.domain.entity.Pedido;
import io.github.Alexandregabriell.domain.enums.StatusPedido;
import io.github.Alexandregabriell.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar (PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);
}

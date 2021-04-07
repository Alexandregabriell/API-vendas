package io.github.Alexandregabriell.repository;

import io.github.Alexandregabriell.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {
}

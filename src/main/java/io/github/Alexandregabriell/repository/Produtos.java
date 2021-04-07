package io.github.Alexandregabriell.repository;

import io.github.Alexandregabriell.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}

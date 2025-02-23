package com.easyestoqueltda.easyestoque.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.easyestoqueltda.easyestoque.model.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
    ProdutoModel findByName(String name);

    List<ProdutoModel> findAllByName(String name);

    Page<ProdutoModel> findAll(Pageable pageable);
}

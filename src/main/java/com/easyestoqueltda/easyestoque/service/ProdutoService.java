package com.easyestoqueltda.easyestoque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.easyestoqueltda.easyestoque.model.DTOCreateProductRequest;
import com.easyestoqueltda.easyestoque.model.ProdutoModel;
import com.easyestoqueltda.easyestoque.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoModel createProduct(DTOCreateProductRequest produtoRequest) {
        // Converte o DTO em uma entidade JPA
        ProdutoModel produto = new ProdutoModel();
        produto.setName(produtoRequest.getName());
        produto.setPrice(produtoRequest.getPrice());
        produto.setQuantity(produtoRequest.getQuantity());
        produto.setDescription(produtoRequest.getDescription());
        produto.setCode(produtoRequest.getCode());
        

        // Salva a entidade no banco de dados
        return this.produtoRepository.save(produto);
    }

    public List<ProdutoModel> findProductsByName(String name) {
        return produtoRepository.findAllByName(name);
    }

    public ProdutoModel findProductById(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public void deleteProduct(Long id) {
        produtoRepository.deleteById(id);
    }

    public ProdutoModel updateProduct(Long id, DTOCreateProductRequest produtoRequest) {
        Optional<ProdutoModel> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isEmpty()) {
            throw new RuntimeException("Produto não encontrado");
        }
        ProdutoModel produto = produtoOptional.get();

        if (produtoRequest.getName() != null) {
            produto.setName(produtoRequest.getName());
        }
        if (produtoRequest.getDescription() != null) {
            produto.setDescription(produtoRequest.getDescription());
        }
        if (produtoRequest.getPrice() != 0) {
            produto.setPrice(produtoRequest.getPrice());
        }
        if (produtoRequest.getQuantity() != 0) {
            produto.setQuantity(produtoRequest.getQuantity());
        }


        return this.produtoRepository.save(produto);
    }

    public Page<ProdutoModel> findAll(Pageable pageable) {
        return this.produtoRepository.findAll(pageable);
    }
}

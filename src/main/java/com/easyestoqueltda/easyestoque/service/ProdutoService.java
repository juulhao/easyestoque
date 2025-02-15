package com.easyestoqueltda.easyestoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easyestoqueltda.easyestoque.model.DTOCreateProductRequest;
import com.easyestoqueltda.easyestoque.model.DTOUpdateProductRequest;
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
        return produtoRepository.save(produto);
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

    public ProdutoModel updateProduct(Long id, DTOUpdateProductRequest produtoRequest) {
        ProdutoModel produto = produtoRepository.findById(id).orElse(null);
        if (produto == null) {
            return null;
        }

        produto.setName(produtoRequest.getName());
        produto.setPrice(produtoRequest.getPrice());
        produto.setQuantity(produtoRequest.getQuantity());
        produto.setDescription(produtoRequest.getDescription());
        produto.setCode(produtoRequest.getCode());

        return produtoRepository.save(produto);
    }

}

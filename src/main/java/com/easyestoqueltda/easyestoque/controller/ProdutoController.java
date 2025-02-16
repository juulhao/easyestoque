package com.easyestoqueltda.easyestoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.easyestoqueltda.easyestoque.model.DTOCreateProductRequest;
import com.easyestoqueltda.easyestoque.model.ProdutoModel;
import com.easyestoqueltda.easyestoque.service.ProdutoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{productName}")
    public ResponseEntity<List<ProdutoModel>> getProduto(@RequestParam String productName) {
        List<ProdutoModel> produtos = produtoService.findProductsByName(productName);
        if (produtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModel> getProdutoById(@PathVariable Long id) {
        var produto = produtoService.findProductById(id);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(produto);
    }

    @PostMapping
    public ResponseEntity<ProdutoModel> createProduct(@RequestBody DTOCreateProductRequest produtoRquest) {
        ProdutoModel produto = produtoService.createProduct(produtoRquest);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        produtoService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoModel> updateProduct(@PathVariable Long id, @RequestBody DTOCreateProductRequest produtoRequest) {
        ProdutoModel produto = produtoService.updateProduct(id, produtoRequest);
        return ResponseEntity.ok().body(produto);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ProdutoModel>> getAllProdutos(
            @PageableDefault(size = 10, sort = "id") Pageable pageable
    ) {
        Page<ProdutoModel> produtos = produtoService.findAll(pageable);
        return ResponseEntity.ok().body(produtos);
    }

}

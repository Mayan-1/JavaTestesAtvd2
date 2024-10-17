package com.snack.repositories;

import com.snack.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {
    private ProductRepository productRepository;
    private Product product1;

    @BeforeEach
    public void setup() {
        productRepository = new ProductRepository();
        product1 = new Product(1, "Hot Dog", 10.4f, "");
        productRepository.append(product1);
    }

    @Test
    public void verificarSeOProdutoEAdicionadoCorretamenteAoArray(){
        assertTrue(productRepository.getAll().contains(product1));
    }

    @Test
    public void verificarSeEPossivelRecuperarProdutoPeloId(){
        Product produtoBuscado = productRepository.getById(1);
        assertNotNull(produtoBuscado);
    }

    @Test
    public void verificarSeOProdutoEstaNoArray() {
        Product productId1 = productRepository.getById(1);
        assertNotNull(productId1);
    }

    @Test
    public void testarSeProdutoERemovidoCorretamenteDoArray(){
        productRepository.remove(1);
        assertFalse(productRepository.exists(1));
    }

    @Test
    public void testarSeProdutoEAtualizadoCorretamenteDoArray(){
        Product produtoAtualizado = new Product(1, "Hamburguer", 10.5f, "");
        productRepository.update(1, produtoAtualizado);
        assertEquals(produtoAtualizado.getDescription(), product1.getDescription());
    }

    @Test
    public void testarSeTodosOsProdutosArmazenadosSaoRecuperados(){
        assertNotNull(productRepository.getAll());
    }

    @Test
    public void verificarComportamentoAoRemoverProdutoQueNaoExiste(){
        int idInexistente = 2;
        assertFalse(productRepository.getAll().stream().anyMatch(product -> product.getId() == idInexistente));
        productRepository.remove(idInexistente);
        assertFalse(productRepository.getAll().stream().anyMatch(product -> product.getId() == idInexistente));
    }

    @Test
    public void testarAtualizacaoDeProdutoQueNaoExiste() {
        int idInexistente = 5;

        Product updatedProduct = new Product(idInexistente, "Produto Atualizado", 100.0f, "");

        assertFalse(productRepository.getAll().stream().anyMatch(product -> product.getId() == idInexistente));

        assertThrows(NoSuchElementException.class, () -> {
            productRepository.update(idInexistente, updatedProduct);
        });
    }

    @Test
    public void verificarSeRepositorioAceitaProdutosComIdsDuplicados(){
        Product product2 = new Product(1, "Teste", 15.5f, "");

        productRepository.append(product2);

        long count = productRepository.getAll()
                .stream()
                .filter(product -> product.getId() == 1)
                .count();
        assertEquals(1, count);
    }

    @Test
    public void confirmarQueORepositorioRetornaUmaListaVaziaAoInicializar(){
        assertTrue(productRepository.getAll().isEmpty());
    }


}
package com.snack.repositories;

import com.snack.entities.Product;
import com.snack.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoServiceTest {
    private ProductService productService;
    private String validImgPath;
    private String invalidImgPath;



    @BeforeEach
    public void setup(){
        productService = new ProductService();
        validImgPath = "C:\\imgs\\images.jpeg";
        invalidImgPath = "C:\\imgs\\foto.jpeg";
    }

    @Test
    public void testeSalvarProdutoComImagemValida(){
        Product product = new Product(1, "Hot-dog", 10.5f, validImgPath);
        boolean result = productService.save(product);
        assertTrue(result);
    }

    @Test
    public void testeSalvarProdutoComImagemInexistente(){
        Product product = new Product(1, "Teste", 10.5f, invalidImgPath);
        boolean result = productService.save(product);
        assertFalse(result);
    }

    @Test
    public void testeAtualizarUmProdutoExistente(){
        Product produtoCriado = new Product(1, "Hot-dog", 10.5f, validImgPath);
        productService.save(produtoCriado);
        Product produtoAtualizado = new Product(1, "Novo Hot-dog", 10.1f, validImgPath);
        productService.update(produtoAtualizado);
        assertEquals("Novo Hot-dog", produtoAtualizado.getDescription());
    }

    @Test
    public void testeRemoverProdutoExistente(){
        Product produtoCriado = new Product(1, "Hot-dog", 10.5f, validImgPath);
        productService.save(produtoCriado);
        Path pathImg = Paths.get(productService.getImagePathById(1));
        productService.remove(produtoCriado.getId());
        assertFalse(Files.exists(pathImg));
    }

    @Test
    public void testeObterCaminhoDaImagemPorId(){
        Product produtoCriado = new Product(1, "Hot-dog", 10.5f, validImgPath);
        productService.save(produtoCriado);
        Path pathImg = Paths.get(productService.getImagePathById(1));
        assertNotNull(pathImg);
    }

}

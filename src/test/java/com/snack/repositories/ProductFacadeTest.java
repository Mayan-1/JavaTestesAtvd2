package com.snack.repositories;

import com.snack.applications.ProductApplication;
import com.snack.entities.Product;
import com.snack.facade.ProductFacade;
import com.snack.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductFacadeTest {
    private ProductFacade productFacade;
    private ProductApplication productApplication;
    private ProductRepository productRepository;
    private ProductService productService;
    private Product product;
    private String validImgPath;

    @BeforeEach
    public void setUp(){
        productRepository = new ProductRepository();
        productService = new ProductService();
        productApplication = new ProductApplication(productRepository, productService);
        productFacade = new ProductFacade(productApplication);
        validImgPath = "C:\\imgs\\images.jpeg";
        product = new Product(1, "Teste", 10.5f, validImgPath);
        productFacade.append(product);
    }

    @Test
    public void retornarAListaCompletaDeProdutosAoChamarGetAll(){
        assertNotNull(productFacade.getAll());
    }

    @Test
    public void retornarOProdutoCorretoAoFornecerUmIdValido(){
        assertEquals(product, productFacade.getById(1));
    }

    @Test
    public void retornarTrueParaUmIdExistenteEFalseParaInexistente(){
        boolean exists = productFacade.exists(1);
        assertTrue(exists);
        exists = productFacade.exists(2);
        assertFalse(exists);
    }

    @Test
    public void adicionaUmNovoProdutoCorretamenteAoChamarMetodoAppend(){
        Product product1 = new Product(2, "Novo", 10.4f, validImgPath);
        productFacade.append(product1);
        assertNotNull(productFacade.getById(1));
    }

    @Test
    public void removerUmProdutoExistenteAoFornecerUmIdValido(){
        productFacade.remove(1);
        assertFalse(productFacade.exists(1));
    }
}

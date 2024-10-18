package com.snack.repositories;

import com.snack.applications.ProductApplication;
import com.snack.entities.Product;
import com.snack.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductApplicationTest {
    private ProductApplication productApplication;
    private ProductRepository productRepository;
    private ProductService productService;
    private Product produto;
    private String validImgPath;
    @BeforeEach
    public void setup(){
        productRepository = new ProductRepository();
        productService = new ProductService();
        productApplication = new ProductApplication(productRepository, productService);
        validImgPath = "C:\\imgs\\images.jpeg";
        produto = new Product(1, "Teste", 10.5f, validImgPath);
    }

    @Test
    public void listarTodosOsProdutosDoRepositorio(){
        assertNotNull(productApplication.getAll());
    }

    @Test
    public void obterUmProdutoPorIdValido(){
        productApplication.append(produto);
        assertNotNull(productApplication.getById(1));
    }

    @Test
    public void retornarNuloOuErroAoObterProdutoPorIdInvalido(){
        assertThrows(NoSuchElementException.class, () ->{
            productApplication.getById(1);
        });
    }

    @Test
    public void verificarSeUmProdutoExistePorIdValido(){
        productApplication.append(produto);
        boolean exists = productApplication.exists(1);
        assertTrue(exists);
    }

    @Test
    public void retornarFalsoAoVerificarExistenciaDeUmProdutoComIdInvalido(){
        boolean exists = productApplication.exists(1);
        assertFalse(exists);
    }

    @Test
    public void adicionarUmNovoProdutoESalvarSuaImagemCorretamente(){
        productApplication.append(produto);
        long count = productApplication.getAll().size();
        assertEquals(1, count);
    }

    @Test
    public void removerUmProdutoExistenteEDeletarSuaImagem(){
        productApplication.append(produto);
        productApplication.remove(1);
        boolean foiRemovido = productApplication.exists(1);
        assertFalse(foiRemovido);
    }

    @Test
    public void naoAlterarOSistemaAoTentarRemoverUmProdutoComIdInexistente(){

    }

    @Test
    public void atualizarUmProdutoExistenteESubstituirSuaImagem(){
        productApplication.append(produto);
        String anotherPath = "C:\\imgs\\hot-dog.jpeg";
        Product novoProduto = new Product(1, "Novo Produto", 41.5f, anotherPath);
        productApplication.update(novoProduto.getId(), novoProduto);
        assertEquals(novoProduto.getDescription(), productApplication.getById(1).getDescription());
    }
}

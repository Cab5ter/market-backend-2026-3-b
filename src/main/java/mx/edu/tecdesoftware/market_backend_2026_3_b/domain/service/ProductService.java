package mx.edu.tecdesoftware.market_backend_2026_3_b.domain.service;


import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Product;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll()
    {
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId)
    {
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId)
    {
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product)
    {
        return productRepository.save(product);
    }

    public boolean delete(int productId)
    {
        //Verificar que exista antes de borrar
        if(getProduct(productId).isPresent())
        {
            productRepository.delete(productId);
            return true;
        } else {
            return false;
        }
    }

}

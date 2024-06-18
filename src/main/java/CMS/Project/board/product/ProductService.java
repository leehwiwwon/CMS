package CMS.Project.board.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long code) {
        return productRepository.findById(code);
    }

    public void save(Product product) { productRepository.save(product); }

    public void deleteById(Long id, Long code) {
        productRepository.deleteById(id);
    }
}
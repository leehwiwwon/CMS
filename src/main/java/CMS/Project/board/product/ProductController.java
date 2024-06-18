package CMS.Project.board.product;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "products/form";
    }

    @PostMapping("/save")
    public String createProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{code}")
    public String showEditForm(@PathVariable Long code, Model model) {
        Optional<Product> product = productService.findById(code);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "products/form";
        } else {
            return "redirect:/products";
        }
    }

    @PostMapping("/update/{code}")
    public String updateProduct(@PathVariable Long code, @ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{code}")
    public String deleteProduct(@PathVariable Long code) {
        productService.deleteById(code);
        return "redirect:/products";
    }
}

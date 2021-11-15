package ecommerce.orders.service;

import ecommerce.orders.dao.ProductRepository;
import ecommerce.orders.data.ProductData;
import ecommerce.orders.data.ProductPriceData;
import ecommerce.orders.entity.Product;
import ecommerce.orders.entity.ProductPrice;
import ecommerce.orders.exception.AlreadyExist;
import ecommerce.orders.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductData newProduct(ProductData data) {

        if (productRepository.findByName(data.getName()).isPresent()) {
            throw new AlreadyExist(data.getName() + " exists");
        }
        Product product = convert(data);

        product = productRepository.save(product);
        LOG.info("Product id {} and {}", product.getId(), product);
        return convert(product);
    }

    @Override
    public ProductData updateProductDescription(Long id, ProductData data) {
        Product oldData = productRepository.findById(id).orElseThrow(() -> new NotFoundException(id + " not found"));

        oldData.setName(data.getName());
        oldData.setDescription(data.getDescription());
        Product newData = productRepository.save(oldData);
        data.setId(newData.getId());
        return data;
    }


    @Override
    public List<ProductPriceData> newProductPrice(Long productId, BigDecimal amount, String currency) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("could not found id:" + productId));

        ProductPrice np = new ProductPrice();
        np.setProduct(product);
        np.setCurrency(currency);
        np.setAmount(amount);
        np.setStartDate(Date.from(Instant.now()));
        np.setActive(true);
        product.getPrices().add(np);
        product = productRepository.save(product);
        return product.getPrices().stream().map(this::convert).collect(Collectors.toList());
    }



    public List<ProductData> getAll() {
        List<ProductData> dataList = new ArrayList<>();
        productRepository.findAll().forEach(a -> dataList.add(convert(a)));
        return dataList;
    }

    private ProductData convert(Product p) {
        ProductData data = new ProductData();
        data.setId(p.getId());
        data.setDescription(p.getDescription());
        data.setName(p.getName());
        List<ProductPriceData> ppList = new ArrayList<>();

        p.getPrices().forEach(c ->
                ppList.add(convert(c)));
        data.setPrices(ppList);

        return data;
    }

    private Product convert(ProductData p) {
        Product data = new Product();
        data.setDescription(p.getDescription());
        data.setName(p.getName());
        data.setId(p.getId());
        Set<ProductPrice> ppList = new HashSet<>();

        p.getPrices().forEach(c -> ppList.add(convert(c, data)));

        data.setPrices(ppList);

        return data;
    }


    private ProductPriceData convert(ProductPrice pp) {
        return new ProductPriceData(pp.getId(), pp.getAmount(), pp.getCurrency());
    }

    private ProductPrice convert(ProductPriceData data, Product product) {
        ProductPrice price = new ProductPrice();
        price.setProduct(product);
        price.setActive(true);
        price.setAmount(data.getMoney().getAmount());
        price.setCurrency(data.getMoney().getCurrency());
        price.setStartDate(Date.from(Instant.now()));

        return price;
    }

}

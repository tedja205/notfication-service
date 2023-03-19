package com.andreas.services;

import com.andreas.entities.Product;
import com.andreas.request.ProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    public String createProductWithIndex(ProductRequest productRequest) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(productRequest, Product.class);

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(product.getId())
                .withObject(product).build();

        //return documentId
        return elasticsearchOperations.index(indexQuery, IndexCoordinates.of("productindex"));
    }

    public List<Product> findProductBySupplier(final String supplierName) {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("supplier", supplierName);

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<Product> productHits = elasticsearchOperations.search(searchQuery, Product.class, IndexCoordinates.of("productindex"));

        //return List of Product(after convert from SearchHit)
        return productHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }
}

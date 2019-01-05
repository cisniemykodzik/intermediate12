package pl.sda.intermediate12;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import pl.sda.intermediate12.products.ProductDAO;
import pl.sda.intermediate12.products.ProductDTO;

import java.io.File;
import java.util.List;


public class ProductDAOTest {
    private ProductDAO productDAO = new ProductDAO();
    File fXMLfile = new File("src/main/resources/books.txt");

    @Test
    public void shouldParseXMLFileWithProductsAndPrintIt (){
        List<ProductDTO> productDTOList = productDAO.parseXMLToProductDTOList(fXMLfile);
        for (ProductDTO productDTO : productDTOList) {
            System.out.println(productDTO.toString());
        }
    }
}

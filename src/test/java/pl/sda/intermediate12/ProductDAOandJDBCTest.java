package pl.sda.intermediate12;

import org.junit.jupiter.api.Test;
import pl.sda.intermediate12.products.Product;
import pl.sda.intermediate12.products.ProductDAO;
import pl.sda.intermediate12.products.ProductDTO;

public class ProductDAOandJDBCTest {
    ProductDAO productDAO = new ProductDAO();
    ProductDTO testProductDTO = new ProductDTO();
    Product testProduct = new Product();
    {
        //TODO
        // ustawić setterami jakieś dane do testowego produktu oraz DTOsa\
        // najlepiej z jednym polem losowym, aby przy kolejnym wywołaniu metod testowych nie napotkać w bazie danych
        // produktu z poprzedniego wywołania
    }


    @Test
    public void shouldAddAndGetProductDTOFromDatabase (){
        // TODO sprawdzic czy możemy dodać do bazy danych testProductDTO metodami z klasy ProductDAO
        //  i selectem otrzymać ten sam ProductDTO

    }

    @Test
    public void shouldAddAndGetProductFromDatabase (){
        // TODO sprawdzic czy możemy dodać do bazy danych testProduct metodami z klasy ProductDAO
        //  i selectem otrzymać ten sam Product

    }
}

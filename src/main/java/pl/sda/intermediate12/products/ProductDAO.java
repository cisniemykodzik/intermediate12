package pl.sda.intermediate12.products;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDAO {
    private static DataSource datasource;

    public List<ProductDTO> parseXMLToProductDTOList (File fXMLfile){
        List<ProductDTO> productDTOList = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXMLfile);
            NodeList nodeList = doc.getElementsByTagName("book");

            for (int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setId(element.getAttribute("id"));
                    productDTO.setAuthor(element.getElementsByTagName("author").item(0).getTextContent());
                    productDTO.setTitle(element.getElementsByTagName("title").item(0).getTextContent());
                    productDTO.setGenre(element.getElementsByTagName("genre").item(0).getTextContent());
                    productDTO.setPrice(element.getElementsByTagName("price").item(0).getTextContent());
                    productDTO.setPublish_date(element.getElementsByTagName("publish_date").item(0).getTextContent());
                    productDTO.setDescription(element.getElementsByTagName("description").item(0).getTextContent());
                    productDTOList.add(productDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productDTOList;
    }

    private static Connection getConnection() throws SQLException {
        if (datasource == null) {
            String connectionString = "jdbc:mysql://127.0.0.1:3306/cisniemykodzik?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String username = "cisniemykodzik";
            String password = "sda2018cisniemy";
            BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setUrl(connectionString);
            basicDataSource.setUsername(username);
            basicDataSource.setPassword(password);
            basicDataSource.setMaxTotal(5);
            basicDataSource.setInitialSize(3);
            basicDataSource.setMaxWaitMillis(5000);

            datasource = basicDataSource;
        }
        return datasource.getConnection();
    }
}

package pl.sda.intermediate12;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class JsonExample {
    @Test
    public void serializeToJson() {
        SomeObject someObject = new SomeObject();
        someObject.setName("Adam");
        someObject.setAge(25);
        someObject.setSalary(BigDecimal.valueOf(3000.2));
        List<OtherObject> otherObjects = new ArrayList<>();
        OtherObject otherObject = new OtherObject();
        otherObject.setId(1);
        otherObject.setText("Text");
        otherObjects.add(otherObject);
        OtherObject otherObject2 = new OtherObject();
        otherObject2.setId(2);
        otherObject2.setText("Text2");
        otherObjects.add(otherObject2);

        someObject.setOtherObjects(otherObjects);

        Gson gson = new Gson();
        String json = gson.toJson(someObject);
        System.out.println(json);
        try {
            FileWriter writer = new FileWriter("c:\\projects\\someobject.txt");
            gson.toJson(someObject, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deSerializeFromJson() {
        Gson gson = new Gson();
        try {
            SomeObject someObject = gson.fromJson(new FileReader("c:\\projects\\someobject.txt"), SomeObject.class);
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Getter
    @Setter
    private class SomeObject {
        private String name;
        private Integer age;
        private BigDecimal salary;
        private List<OtherObject> otherObjects;
    }

    @Getter
    @Setter
    private class OtherObject {
        private Integer id;
        private String text;
    }

    @Test
    public void table() {
        try {
            URL url = new URL("http://api.nbp.pl/api/exchangerates/tables/A/last?format=json");
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);

            String inputLine;
            String result = "";
            while ((inputLine = br.readLine()) != null) {
                result = result + inputLine;
            }
            br.close();
            System.out.println(result);

            Gson gson = new Gson();
            RatesWrapper[] ratesWrappers = gson.fromJson(result, RatesWrapper[].class);
            System.out.println();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Getter
    @Setter
    private class RatesWrapper {
        private String table;
        private String no;
        private String effectiveDate;
        private List<Rate> rates;
    }

    @Getter
    @Setter
    private class Rate {
        private String currency;
        private String code;
        private Double mid;
    }

}

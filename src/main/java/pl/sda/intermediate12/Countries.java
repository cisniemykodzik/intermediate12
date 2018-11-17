package pl.sda.intermediate12;

import lombok.Getter;
@Getter
// jakos trzeba sie dostac do danych
public enum Countries {
    USA("Stany zjednoczone", "US"),
    POLAND("Polska", "PL"),
    UKRAINE("Ukraina", "UA"),
    FRANCE("Francja", "FR");

    private String name;
    private String symbol;

    Countries(String name, String symbol) {//Fixme -> opowiedziec czemu nie powinno to byc "name"
        this.name = name;
        this.symbol = symbol;
    }
}

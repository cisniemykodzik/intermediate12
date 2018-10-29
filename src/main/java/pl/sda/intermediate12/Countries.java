package pl.sda.intermediate12;

public enum Countries {
    USA("Stany zjednoczone", "US"),
    POLAND("Polska", "PL"),
    UKRAINE("Ukraina", "UA"),
    FRANCE("Francja", "FR");

    private String name;
    private String symbol;

    Countries(String name, String symbol) {//Fixme
        this.name = name;
        this.symbol = symbol;
    }
}

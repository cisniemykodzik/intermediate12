package pl.sda.intermediate12;

import lombok.Data;
import org.apache.commons.lang3.NotImplementedException;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

@Data
public class CollectionComparisonResult<A,B> {
    private LinkedHashSet<A> onlyInFirst = new LinkedHashSet<>();
    private LinkedHashSet<B> onlyInSecond = new LinkedHashSet<>();
    private Map<A,B> common = new LinkedHashMap<>();

    public boolean isSame() {
        throw new NotImplementedException("Do dzieła!"); //todo
    }
}

package pl.sda.intermediate12;

import org.apache.commons.lang3.NotImplementedException;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class CollectionComparisonUtil {

    public static <A, B> CollectionComparisonResult<A, B> compareCollections(Collection<A> collectionA, Collection<B> collectionB, CollectionComparisonComparator<A, B> comparator) {
        throw new NotImplementedException("Do dzieła!"); //todo
    }


    public static <ELEMENT, KEY> CollectionComparisonResult<ELEMENT, ELEMENT> compareCollections(Collection<ELEMENT> collectionA, Collection<ELEMENT> collectionB, Function<ELEMENT, KEY> keyProvider) {
        throw new NotImplementedException("Do dzieła!"); //todo
    }
}

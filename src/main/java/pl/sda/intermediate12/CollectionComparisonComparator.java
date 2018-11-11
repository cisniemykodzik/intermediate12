package pl.sda.intermediate12;

@FunctionalInterface
public interface CollectionComparisonComparator<A, B> {
    boolean isEqual(A elementA, B elementB);
}

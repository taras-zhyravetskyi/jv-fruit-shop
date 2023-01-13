package core.basesyntax.dao;

import java.util.Map;
import java.util.Set;

public interface FruitAccountingDao {
    Set<Map.Entry<String, Integer>> getMapEntry();

    Integer getQuantity(String fruit);

    void updateData(String fruit, Integer quantity);
}

package core.basesyntax.service.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.FruitTransactionParser;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FruitTransactionParserImpl implements FruitTransactionParser {
    private static final String ELEMENT_SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int FRUIT_INDEX = 1;
    private static final int QUANTITY_INDEX = 2;

    @Override
    public List<FruitTransaction> transformToTransaction(List<String> lines) {
        return lines.stream()
                .filter(line -> lineValidator(line))
                .map(line -> lineToTransaction(line))
                .collect(Collectors.toList());
    }

    private boolean lineValidator(String line) {
        for (FruitTransaction.Operation operation : FruitTransaction.Operation.values()) {
            if (operation.getOperation()
                    .equals(line.split(ELEMENT_SEPARATOR)[OPERATION_TYPE_INDEX])) {
                return true;
            }
        }
        return false;
    }

    private FruitTransaction lineToTransaction(String line) {
        String[] fields = line.split(ELEMENT_SEPARATOR);
        FruitTransaction fruitTransaction = new FruitTransaction();
        fruitTransaction.setOperation(parceOperation(fields[OPERATION_TYPE_INDEX]));
        fruitTransaction.setFruit(fields[FRUIT_INDEX]);
        fruitTransaction.setQuantity(Integer.parseInt(fields[QUANTITY_INDEX]));
        return fruitTransaction;
    }

    private FruitTransaction.Operation parceOperation(String operation) {
        return Arrays.stream(FruitTransaction.Operation.values())
                .filter(o -> o.getOperation().equals(operation))
                .findFirst()
                .orElseThrow();
    }
}

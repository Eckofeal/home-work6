package bank;

import bankoperation.BankOperation;

public interface Operationable {

    void add(BankOperation operation);

    boolean remove(BankOperation operation);

}

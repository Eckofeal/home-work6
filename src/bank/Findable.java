package bank;

import bankoperation.BankOperation;
import human.Human;

import java.util.List;

public interface Findable {

    List<BankOperation> find(Human human);

}

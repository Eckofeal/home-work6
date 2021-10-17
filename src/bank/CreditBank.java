package bank;

import address.Address;
import bank.currency.Currency;
import bank.organization.TaxPayable;
import bankoperation.BankOperation;
import bankoperation.Credit;
import bankoperation.CreditType;
import human.Human;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreditBank extends Bank {

    private List<CreditType> creditTypes;
    private List<Credit> credits;

    public CreditBank(String name, Address address, LocalDateTime foundedAt) {
        super(name, address, foundedAt);
    }

    public CreditBank(String nameOfBank, Address address, LocalDateTime foundedAt, Currency usd, Currency eur, Currency rub, Currency byn) {
        super(nameOfBank, address, foundedAt, usd, eur, rub, byn);
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCreditTypes(List<CreditType> creditTypes) {
        this.creditTypes = creditTypes;
    }

    public List<CreditType> getCreditTypes() {
        return creditTypes;
    }

    public void addCreditType(CreditType creditType) {
        if (creditType == null) {
            return;
        }
        if (this.creditTypes == null) {
            this.creditTypes = new ArrayList<>();
        }
        if (!this.creditTypes.contains(creditType)) {
            this.creditTypes.add(creditType);
        } else {
            System.out.println("Credit type already exist.");
        }
    }

    public boolean removeCreditType(CreditType creditType) {
        if (creditType == null || this.creditTypes == null || this.creditTypes.isEmpty()) {
            return false;
        }
        return this.creditTypes.remove(creditType);
    }

    public List<CreditType> findCreditType(String moneyType) {
        List<CreditType> result = null;
        if (this.creditTypes != null && this.creditTypes.size() > 0) {
            result = new ArrayList<>();
            for (CreditType element : this.creditTypes) {
                if (element.getMoneyType().equals(moneyType)) {
                    result.add(element);
                }
            }
        }
        return result;
    }

    public List<CreditType> findCreditType(String moneyType, double moneyAmount) {
        List<CreditType> result = null;
        if (this.creditTypes != null && this.creditTypes.size() > 0) {
            result = new ArrayList<>();
            for (CreditType element : this.creditTypes) {
                if (element.getMoneyType().equals(moneyType) &&
                        (moneyAmount >= element.getMinMoneyAmount() &&
                                moneyAmount <= element.getMaxMoneyAmount())) {
                    result.add(element);
                }
            }
        }
        return result;
    }

    @Override
    public void add(BankOperation operation) {
        if (operation == null) {
            return;
        }
        if (this.credits != null) {
            if (!this.credits.contains(operation)) {
                this.credits.add((Credit) operation);
            } else {
                System.out.println("Credit already exist.");
            }
        } else {
            this.credits = new ArrayList<>();
            this.credits.add((Credit) operation);
        }
    }

    @Override
    public boolean remove(BankOperation operation) {
        if (operation == null || this.credits == null || !this.credits.isEmpty()) {
            return false;
        }
        return this.credits.remove((Credit) operation);
    }

    @Override
    public List<BankOperation> find(Human human) {
        List<BankOperation> result = null;
        if (human == null) {
            return null;
        }
        if (this.credits != null && this.credits.size() > 0) {
            result = new ArrayList<>();
            for (BankOperation element : this.credits) {
                if (element.getClient().getFirstName().equals(human.getFirstName()) &&
                        element.getClient().getLastName().equals(human.getLastName())) {
                    result.add(element);
                }
            }
        }
        return result;
    }

    /*private BankOperation[] addAlgorithm(BankOperation[] operations, BankOperation operation) {
        if (operation == null) {
            return operations;
        }
        BankOperation[] result;
        if (operations == null) {
            result = new Credit[1];
            result[0] = operation;
        } else {
            result = new BankOperation[operations.length + 1];
            result = copyThenInsert(operations, result, operation);
        }
        return result;
    }

    private BankOperation[] removeAlgorithm(BankOperation[] bankOperations, BankOperation operation) {
        BankOperation[] result = new BankOperation[bankOperations.length - 1];
        int index = indexOfOperation(bankOperations, operation);
        for (int i = 0, j = 0; i < bankOperations.length; i++, j++) {
            if (i == index) {
                j--;
            } else {
                result[j] = bankOperations[i];
            }
        }
        return result;
    }

    private int indexOfOperation(BankOperation[] operations, BankOperation operation) {
        int result = -1;
        if (operations.length != 0) {
            int flag = 0;
            for (int i = 0; i < operations.length; i++) {
                flag++;
                if (operations[i].equals(operation)) {
                    result = i;
                    return result;
                }
            }
            if (flag == operations.length) {
                result = -1;
            }
        } else {
            result = -1;
        }
        return result;
    }

    private BankOperation[] copyThenInsert(BankOperation[] copyThis, BankOperation[] intoThis, BankOperation thenInsertThis) {
        for (int i = 0; i < intoThis.length; i++) {
            if (i != intoThis.length - 1) {
                intoThis[i] = copyThis[i];
            } else {
                intoThis[i] = thenInsertThis;
            }
        }
        return intoThis;
    }

    public Credit[] transform(BankOperation[] bankOperations) {
        Credit[] credits = new Credit[bankOperations.length];
        for (int i = 0; i < bankOperations.length; i++) {
            credits[i] = (Credit) bankOperations[i];
        }
        return credits;
    }*/

    @Override
    public void payTax() {
        System.out.printf("%-60s%s%s", "\n", "CREDIT BANK TAX:", "\n");
        System.out.println(super.getName() + " bank: ");
        if (super.getUsd().getAmount() > 0) {
            double tax = super.getUsd().getAmount() * TaxPayable.taxPercent;
            super.getUsd().setAmount(super.getUsd().getAmount() - tax);
            System.out.println("USD tax paid: " + tax);
        }
        if (super.getEur().getAmount() > 0) {
            double tax = super.getEur().getAmount() * TaxPayable.taxPercent;
            super.getEur().setAmount(super.getEur().getAmount() - tax);
            System.out.println("EURO tax paid: " + tax);
        }
        if (super.getRub().getAmount() > 0) {
            double tax = super.getUsd().getAmount() * TaxPayable.taxPercent;
            super.getUsd().setAmount(super.getUsd().getAmount() - tax);
            System.out.println("RUB tax paid: " + tax);
        }
        if (super.getByn().getAmount() > 0) {
            double tax = super.getUsd().getAmount() * TaxPayable.taxPercent;
            super.getUsd().setAmount(super.getUsd().getAmount() - tax);
            System.out.println("BYN tax paid: " + tax);
        }
    }

    @Override
    public void print() {
        System.out.printf("%-60s%s%s", "\n", "CREDIT BANK ININFORMATION:", "\n");
        System.out.println(super.getName() + ", located on " + super.getAddress().getCity() +
                ", " + super.getAddress().getStreet() + " " + super.getAddress().getHouseNumber() + " street, founded in " +
                super.getFoundedAt().getDayOfMonth() + "." + super.getFoundedAt().getMonth() + "." + super.getFoundedAt().getYear());
        System.out.println("Number of credit types in bank: " + this.creditTypes.size());
        System.out.println("Number of credits issued: " + credits.size());
        System.out.println("Bank capital:");
        System.out.println(super.getUsd().getAmount() + " " + Currency.USD);
        System.out.println(super.getEur().getAmount() + " " + Currency.EURO);
        System.out.println(super.getRub().getAmount() + " " + Currency.RUB);
        System.out.println(super.getByn().getAmount() + " " + Currency.BYN);
    }

    @Override
    public String toString() {
        return "Class CreditBank [name = " + getName() + ", address = " + getAddress() + ", foundedAt = "
                + getFoundedAt().getDayOfMonth() + "." + getFoundedAt().getMonth() + "."
                + getFoundedAt().getYear() + ", count = " + count + ", usd = " + getUsd() + ", eur = "
                + getEur() + ", rub = " + getRub() + ", byn = " + getByn() + ", employees = " + getEmployees()
                + ", creditTypes = " + creditTypes + ", credits = " + credits + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        CreditBank creditBank = (CreditBank) object;
        return getUsd().equals(creditBank.getUsd()) && getEur().equals(creditBank.getEur()) && getRub().equals(creditBank.getRub())
                && getByn().equals(creditBank.getByn()) && getEmployees().equals(creditBank.getEmployees())
                && (getName() != null && getName().equals(creditBank.getName()))
                && getAddress().equals(creditBank.getAddress()) && getFoundedAt().equals(creditBank.getFoundedAt())
                && creditTypes.equals(creditBank.getCreditTypes()) && credits.equals(creditBank.getCredits());
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, getUsd(), getEur(), getRub(), getByn(), getEmployees(), getName(), getAddress(), getFoundedAt(),
                creditTypes, credits);
    }
}

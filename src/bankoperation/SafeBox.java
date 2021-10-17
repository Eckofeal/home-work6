package bankoperation;

import bank.currency.Currency;
import bank.currency.Value;
import print.Printable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class SafeBox<T extends Value> implements Printable {

    private T deposit;
    private LocalDate opened;
    private int storageTimeInMonth;
    private LocalDate closed;
    private Currency pricePerMonth = new Currency(100, Currency.USD);

    public SafeBox(T deposit, int storageTimeInMonth) {
        this.deposit = deposit;
        opened = LocalDate.now();
        this.storageTimeInMonth = storageTimeInMonth;
        closed = LocalDate.now().plusMonths(storageTimeInMonth);
    }

    public T getDeposit() {
        return deposit;
    }

    public LocalDate getOpened() {
        return opened;
    }

    public int getStorageTimeInMonth() {
        return storageTimeInMonth;
    }

    public LocalDate getClosed() {
        return closed;
    }

    public void setPricePerMonth(Currency pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public Currency getPricePerMonth() {
        return pricePerMonth;
    }

    @Override
    public void print() {
        System.out.println("Deposit:");
        deposit.print();
        System.out.println("\nStorage period: " + opened.getDayOfMonth() + "." + opened.getMonth() + "." + opened.getYear()
                            + " - " + closed.getDayOfMonth() + "." + closed.getMonth() + "." + closed.getYear() + ".");
        System.out.println("Storage cost: " + pricePerMonth.getAmount() * storageTimeInMonth + " " + pricePerMonth.getType());
    }

    @Override
    public String toString() {
        return "Class SafeBox [deposit = " + deposit + ", opened = " +
                opened + ", storageTimeInMonth = " + storageTimeInMonth + ", closed" + closed
                + ", pricePerMonth" + pricePerMonth +"]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
      SafeBox<T> safeBox = (SafeBox<T>) object;
        return deposit.equals(safeBox.getDeposit()) && opened.equals(safeBox.opened)
                && storageTimeInMonth == safeBox.getStorageTimeInMonth() && closed.equals(safeBox.closed)
                && pricePerMonth.equals(safeBox.getPricePerMonth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(deposit, opened, storageTimeInMonth, closed, pricePerMonth);
    }
}

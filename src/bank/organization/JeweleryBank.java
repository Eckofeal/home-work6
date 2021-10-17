package bank.organization;

import address.Address;
import bank.Exchangable;
import bank.currency.Currency;
import bank.currency.Value;
import bankoperation.Contribution;
import bankoperation.сlient.Client;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class JeweleryBank<T extends Value> extends Organization {

    private HashMap<Client, Contribution<T>> contributions;

    public JeweleryBank(String nameOfBank, Address address, LocalDateTime foundedAt) {
        super(nameOfBank, address, foundedAt);
        contributions = new HashMap<>();
    }

    public HashMap<Client, Contribution<T>> getContributions() {
        return contributions;
    }

    public void addContribution(Client client, Contribution<T> contribution) {
        contributions.put(client, contribution);
    }

    public Contribution<T> findContribution(Client client) {
        return contributions.get(client);
    }

    public Contribution<T> removeContribution(Client client) {
        return contributions.remove(client);
    }

    public double amountOfDeposits() {
        double resultAmount = 0;
        for (Map.Entry<Client,Contribution<T>> entry : contributions.entrySet()) {
            if(entry.getValue().getInvested().getValue().getType().equals(Currency.USD)) {
                resultAmount += entry.getValue().getInvested().getValue().getAmount();
            } else if(entry.getValue().getInvested().getValue().getType().equals(Currency.EURO)) {
                double converted =  entry.getValue().getInvested().getValue().getAmount() * Exchangable.EURO_TO_USD;
                resultAmount += converted;
            } else if(entry.getValue().getInvested().getValue().getType().equals(Currency.RUB)) {
                double converted =  entry.getValue().getInvested().getValue().getAmount() * Exchangable.RUB_TO_USD;
                resultAmount += converted;
            } else if(entry.getValue().getInvested().getValue().getType().equals(Currency.BYN)) {
                double converted =  entry.getValue().getInvested().getValue().getAmount() / Exchangable.USD_BUY;
                resultAmount += converted;
            }
        }
        return resultAmount;
    }

    @Override
    public void payTax() {
        double tax = amountOfDeposits() * TaxPayable.taxPercent;
        System.out.println("Tax paid: " + tax + " USD.");
    }

    @Override
    public void print() {
        super.print();
    }

}

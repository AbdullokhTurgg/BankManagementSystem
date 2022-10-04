package BankManagement;

import BankManagement.Account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank    implements Serializable{
    private String bankName;
    private final List<User> customers;
    private final List<Account> accounts;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Bank(String bankName, List<User> customers, List<Account> accounts, ArrayList<Transaction> transactions) {
        this.customers = customers;
        this.accounts = accounts;
        this.transactions = transactions;

    }

    public boolean isHasCheckDuplicates(String login) {
        for (int i = 0; i < this.getCustomers().size(); i++) {
            if (this.customers.get(i).getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }
    public void addCustomer(User user) {
        this.customers.add(user);
    }


    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }


    public String getBankName() {
        return bankName;
    }

    public List<User> getCustomers() {
        return customers;
    }


    public List<Account> getAccounts() {
        return accounts;
    }


}

package BankManagement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private final int id;
    private double balance;
    private final String name;
    private final User accountHolder;
    static ArrayList<Transaction> transactions = new ArrayList<>();

    public Account(double balance, String name, User accountHolder) {
        this.id = User.genUniqueId();
        this.balance = balance;
        this.name = name;
        this.accountHolder = accountHolder;
    }

    static void getAllAccount(ArrayList<Account> accountList) {
        for (Account a : accountList) {
            a.getInfo();

        }
    }

    void getInfo() {
        System.out.println("-----------------> ID счета: " + this.id + " <-----------------");
        System.out.println("-----------------> Тип счета: " + this.name + " <-----------------");
        System.out.println("-----------------> Владелец счета: " + accountHolder.getFirstName() + " " + accountHolder.getLastName() + "-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("__________________________________________________________________________________________________________________________________");
    }

    static void accaunts() {
        for (Account a : Main.loggedUser.getAccountList()) {
            System.out.println("-----------------> ID счета: " + a.getId() + " <-----------------");
            System.out.println("-----------------> Тип счета: " + a.getName() + " <-----------------");
            System.out.println("-----------------> Баланс: " + a.getBalance() + " <-----------------");
            System.out.println("-----------------> Владелец счета: " + a.accountHolder.getFirstName() + " " + a.accountHolder.getLastName());
            System.out.println("__________________________________________________________________________________________________________________________________");
        }
    }

   public static void usIdInfo() {
        for (Account a : Main.loggedUser.getAccountList()) {
            System.out.println("-----------------> ID счета: " +a.getId());
        }
    }
    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }


    public User getAccountHolder() {
        return accountHolder;
    }


}

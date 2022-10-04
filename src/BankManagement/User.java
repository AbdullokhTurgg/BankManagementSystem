package BankManagement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User   {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final ArrayList<Account> accountList;
    static ArrayList<Integer> ids = new ArrayList<>();
    private  String login;
    private  String password;

    public User(String firstName, String lastName, String login, String password, ArrayList<Account> accounts) {
        this.id = genUniqueId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.accountList = accounts;
    }

    static void getAllUsers() {
        for (User user : Main.bank.getCustomers()) {
            user.getInfo();

        }

    }

    void getInfo() {
        System.out.println(" Пользватель: " + this.firstName + " " + this.lastName);
        System.out.println(" ID: " + this.id  );
        System.out.println(" Данные о счете");
        System.out.println("----------------------------------------------------------------------------");
        for (Account a : Main.bank.getAccounts()) {
            if (a.getAccountHolder().getId() == this.id) {
                a.getInfo();

            }

        }
    }

    public static int genUniqueId() {
        int id;
        while (true) {
            id = new Random().nextInt(100000, 999999);
            if (checkForDuplicates(id)) {
                ids.add(id);
                break;
            }
        }
        return id;
    }

    private static boolean checkForDuplicates(int id) {
        for (int i : ids) {
            if (i == id) {
                return false;
            }
        }
        return true;


    }

    public ArrayList<Account> getAccountList() {
        return accountList;
    }


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }


    public String getPassword() {
        return password;
    }

    public static void setIds(ArrayList<Integer> ids) {
        User.ids = ids;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

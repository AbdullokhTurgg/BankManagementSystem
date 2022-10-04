package BankManagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Transaction {

    private final String getTransactions;
    private final String timestamp;
    static Scanner scanner = new Scanner(System.in);
    private final double amount;
    private final Account account;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Date date = new Date();


    public Transaction(String typeTransaction, double amount, String timestamp, Account account) {
        this.getTransactions = typeTransaction;
        this.amount = amount;
        this.timestamp = timestamp;
        this.account = account;

    }

    // -----------------------------------------------------    ИНФОРМАЦИЯ О ТРАНЗАКЦИИ    -----------------------------------------------------
    void infoUsers() {
        System.out.println("Пользватель: " + getAccount().getAccountHolder().getFirstName() + " " + getAccount().getAccountHolder().getLastName());
        System.out.println("Тип операции: " + getTransactions);
        System.out.println("Сумма транзакции: " + getAmount());
        System.out.println("Баланс после транзакции: " + (getAccount().getBalance()));
        System.out.println("Дата операции: " + getTimestamp());
        System.out.println("Точное дата операции: " + date);
        System.out.println("Тип счета на котором была совершена операция: " + getAccount().getName());
        System.out.println("Id счета: " + getAccount().getId());
        System.out.println("__________________________________________________________________________________________________________________________________");
    }

    static void infoAboutTrans() {
        boolean info = false;
        while (true) {
            for (Transaction t : Main.bank.getTransactions()) {
                if (Main.loggedUser.getId() == t.getAccount().getAccountHolder().getId()) {
                    t.infoUsers();
                    info = true;

                }

            }
            if (info) {
                break;
            }
            if (!info) {
                System.err.println("У вас пока что нет транзакциий");
                break;
            }
        }


    }

    static void withMoney() {
        while (true) try {
            System.out.println("Введите id своего счета:");
            int id = scanner.nextInt();
            for (Account account : Main.loggedUser.getAccountList()) {
                if (id == account.getId() && account.getName().equals("KGZ")) {
                    withMoneySetKg(account);
                } else if (id == account.getId() && account.getName().equals("USD")) {
                    withMoneySet(account);
                }
            }
        } catch (Exception e) {
            errorForm();

        }


    }

    private static void withMoneyKg(Account account) {
        while (true) {
            try {
                System.out.println("Введите сумму: ");
                double summa = scanner.nextDouble();
                System.out.println("Вы снимате деньги с USD на KGZ валюту");
                System.out.println("Сумма будет умножена согласно по курсу USD");
                summa = summa * Main.courseOfUs;
                summa = Math.round(summa);
                if (summa > account.getBalance()) {
                    System.out.println("У вас не достаточно денег на балансе чтобы снять денги в таком количестве");
                    Main.signInMainMenu();
                } else {
                    double newBalanse = account.getBalance() - summa;
                    account.setBalance(newBalanse);
                    Transaction transaction = new Transaction("Снятие денег со счета.", summa, sdf.format(new Date()), account);
                    Main.bank.getTransactions().add(transaction);
                    transaction.infoUsers();
                    Main.signInMainMenu();

                }


            } catch (Exception e) {
                errorForm();

            }
        }


    }

    private static void withMoneyErr(Account account) {
        while (true) {
            try {
                System.out.println("Введите сумму:");
                double summa = scanner.nextDouble();
                if (summa > account.getBalance()) {
                    System.out.println("У вас не достаточно денег на балансе чтобы снять денги в таком количестве");
                    Main.signInMainMenu();
                } else {
                    double newBalanse = account.getBalance() - summa;
                    account.setBalance(newBalanse);
                    Transaction transaction = new Transaction("Снятие денег со счета.", summa, sdf.format(new Date()), account);
                    Main.bank.getTransactions().add(transaction);
                    transaction.infoUsers();
                    Main.signInMainMenu();

                }


            } catch (Exception e) {
                errorForm();

            }
        }

    }

    private static void withMoneyUs(Account account) {
        while (true) {
            try {
                System.out.println("Введите сумму:");
                double summa = scanner.nextDouble();
                System.out.println("Вы снимате деньги с KGZ счета на USD валюту");
                System.out.println("Сумма будет уменшена согласно по курсу USD");
                summa = summa / Main.courseOfUs;
                summa = Math.round(summa);
                if (summa > account.getBalance()) {
                    System.out.println("У вас не достаточно денег на балансе чтобы снять денги в таком количестве");
                    Main.signInMainMenu();
                } else {
                    double newBalanse = account.getBalance() - summa;
                    account.setBalance(newBalanse);
                    Transaction transaction = new Transaction("Снятие денег со счета.", summa, sdf.format(new Date()), account);
                    Main.bank.getTransactions().add(transaction);
                    transaction.infoUsers();
                    Main.signInMainMenu();

                }
            } catch (Exception e) {
                errorForm();
            }
        }


    }


    private static void withMoneySet(Account account) {
        while (true) {
            try {
                account.getInfo();
                System.out.println("Баланс: " + account.getBalance() + "");
                System.out.println("____________________________________________________________________________________");
                System.out.println("-------> 1 вывести деньги на USD");
                System.out.println("-------> 2 вывести деньги на KGZ");
                int num = scanner.nextInt();
                switch (num) {
                    case 1:
                        withMoneyErr(account);

                    case 2:
                        withMoneyKg(account);


                }

            } catch (Exception e) {
                errorForm();
            }


        }

    }

    public static void errorForm() {
        System.err.println("Ошибка!!!" + "\nНеверный формат ввода!!");
        scanner.next();
    }

    private static void withMoneySetKg(Account account) {

        while (true) {
            try {
                account.getInfo();
                System.out.println("Баланс: " + account.getBalance() + "");
                System.out.println("____________________________________________________________________________________");
                System.out.println("-------> 1: Вывести деньги на USD");
                System.out.println("-------> 2: Вывести деньги на KGZ");
                int num = scanner.nextInt();
                switch (num) {
                    case 1:
                        withMoneyUs(account);

                    case 2:
                        withMoneyErr(account);


                }

            } catch (Exception e) {
                errorForm();
            }

        }

    }


    private static void transfKg(Account account) {
        System.out.println("-------> Введите сумму поплнение:");
        double summa = scanner.nextDouble();
        System.out.println("-------> Вы пополняете USD счет с KGZ валюты");
        System.out.println("-------> Сумма будет уменшена согласно по курсу USD");

        summa = summa / Main.courseOfUs;
        summa = Math.round(summa);
        double f = account.getBalance() + summa;
        account.setBalance(f);
        Transaction transaction = new Transaction("-------> Пополнение счета.", summa, sdf.format(new Date()), account);
        Main.bank.getTransactions().add(transaction);
        transaction.infoUsers();
        Main.signInMainMenu();

    }

    private static void transOfUs(Account account) {
        System.out.println("-------> Введите сумму поплнение:");
        double summa = scanner.nextDouble();
        System.out.println("-------> Вы пополняете KGZ счет с USD валюты");
        System.out.println("-------> Сумма будет умножена согласно по курсу USD");
        summa = summa * Main.courseOfUs;
        summa = Math.round(summa);
        double f = account.getBalance() + summa;
        account.setBalance(f);
        Transaction transaction = new Transaction("-------> Пополнение счета.", summa, sdf.format(new Date()), account);
        Main.bank.getTransactions().add(transaction);
        transaction.infoUsers();
        Main.signInMainMenu();

    }

    private static void transErr(Account account) {
        System.out.println("-------> Введите сумму поплнение:");
        double summa = scanner.nextDouble();
        double f = account.getBalance() + summa;
        account.setBalance(f);
        Transaction transaction = new Transaction("-------> Пополнение счета.", summa, sdf.format(new Date()), account);
        Main.bank.getTransactions().add(transaction);
        transaction.infoUsers();
        Main.signInMainMenu();

    }

    private static void transFunsUs(Account account) {
        while (true) {
            try {
                account.getInfo();
                System.out.println("-------> Баланс: " + account.getBalance());
                System.out.println("____________________________________________________________________________________");
                System.out.println("-------> 1: пополнить деньги на USD");
                System.out.println("-------> 2: попольнить деньги на KGZ");
                int num = scanner.nextInt();
                switch (num) {
                    case 1:
                        transErr(account);

                    case 2:
                        transfKg(account);
                }

            } catch (Exception e) {
                errorForm();
            }


        }

    }

    private static void transferOfFundsKGZ(Account account) {
        while (true) {
            try {
                account.getInfo();
                System.out.println("Баланс: " + account.getBalance());
                System.out.println("____________________________________________________________________________________");
                System.out.println("Нажмите 1 чтобы пополнить деньги на USD");
                System.out.println("Нажмите 2 чтобы попольнить деньги на KGZ");
                int num = scanner.nextInt();
                switch (num) {
                    case 1:
                        transOfUs(account);

                    case 2:
                        transErr(account);
                }

            } catch (Exception e) {
                errorForm();
            }


        }

    }


    static void transferOfFunds() {
        while (true) {
            try {
                System.out.println("Введите id своего счета: ");
                int id = scanner.nextInt();
                for (Account account : Main.loggedUser.getAccountList()) {
                    if (id == account.getId() && account.getName().equals("KGZ")) {
                        transferOfFundsKGZ(account);
                    } else if (id == account.getId() && account.getName().equals("USD")) {
                        transFunsUs(account);
                    }
                }
            } catch (Exception e) {
                errorForm();

            }
        }

    }

    static void allTransaction() {
        while (true) {
            try {

                System.out.println("Введите id своего счета:");
                int i = scanner.nextInt();
                for (Account account1 : Main.loggedUser.getAccountList()) {
                    if (i == account1.getId()) {
                        Account.getAllAccount((ArrayList<Account>) Main.bank.getAccounts());
                        System.out.println("Введите id счета на которую хотите перевести деньги:");
                        Account.accaunts();
                        int id = scanner.nextInt();
                        for (Account account : Main.bank.getAccounts()) {
                            if (id == account.getId() && id != account1.getId()) {
                                account.getInfo();
                                System.out.println("Введите сумму для перевода: ");
                                double usSum = scanner.nextDouble();
                                if (usSum > account1.getBalance()) {
                                    System.err.println("Не достаточно денег на счету чтобы сделать перевод");
                                    Main.signInMainMenu();
                                } else {
                                    if (account1.getName().equals("KGZ") && account.getName().equals("USD")) {
                                        System.out.println("Вы переводите с KGZ баланса на USD баланс");
                                        System.out.println("Сумма перевода будет выполнятся по крусу доллара");
                                        usSum = usSum / Main.courseOfUs;
                                        usSum = Math.round(usSum);
                                        if (usSum > account1.getBalance()) {
                                            System.err.println("Не достаточно денег на счету чтобы сделать перевод");
                                            Main.signInMainMenu();
                                        }
                                        double minus = account1.getBalance() - usSum;
                                        double popolnenie = account.getBalance() + usSum;
                                        account1.setBalance(minus);
                                        account.setBalance(popolnenie);
                                        Transaction transactionM = new Transaction("Перевод средств(Отпрвление).", usSum, sdf.format(new Date()), account1);
                                        Transaction transactionP = new Transaction("Перевод средств(Принято).", usSum, sdf.format(new Date()), account);
                                        Main.bank.getTransactions().add(transactionM);
                                        Main.bank.getTransactions().add(transactionP);
                                        System.out.println("Переведено деньги");
                                        transactionM.infoUsers();
                                        System.out.println("Принято деньги");
                                        transactionP.infoUsers();
                                        Main.bank.getTransactions().add(transactionM);
                                        Main.bank.getTransactions().add(transactionP);
                                        Main.signInMainMenu();
                                    } else if (account1.getName().equals("USD") && account.getName().equals("KGZ")) {
                                        System.out.println("Вы переводите с USD баланса на KGZ баланс");
                                        System.out.println("Сумма перевода будет увеличен согласно по курсу USD");
                                        usSum = usSum * Main.courseOfUs;
                                        usSum = Math.round(usSum);
                                        double f = account1.getBalance() - usSum;
                                        double popolnenie = account.getBalance() + usSum;
                                        account1.setBalance(f);
                                        account.setBalance(popolnenie);
                                        Transaction transactionM = new Transaction("Перевод средств(Отпрвление).", usSum, sdf.format(new Date()), account1);
                                        Transaction transactionP = new Transaction("Перевод средств(Принято).", usSum, sdf.format(new Date()), account);
                                        System.out.println("Переведено деньги");
                                        transactionM.infoUsers();
                                        System.out.println("Принято деньги");
                                        transactionP.infoUsers();
                                        Main.bank.getTransactions().add(transactionM);
                                        Main.bank.getTransactions().add(transactionP);
                                        Main.signInMainMenu();
                                    } else {
                                        double f = account1.getBalance() - usSum;
                                        double popolnenie = account.getBalance() + usSum;
                                        account1.setBalance(f);
                                        account.setBalance(popolnenie);
                                        Transaction transactionM = new Transaction("Перевод средств(Отпрвление).", usSum, sdf.format(new Date()), account1);
                                        Transaction transactionP = new Transaction("Перевод средств(Принято).", usSum, sdf.format(new Date()), account);
                                        System.out.println("Переведено деньги");
                                        transactionM.infoUsers();
                                        System.out.println("Принято деньги");
                                        transactionP.infoUsers();
                                        Main.bank.getTransactions().add(transactionM);
                                        Main.bank.getTransactions().add(transactionP);
                                        Main.signInMainMenu();

                                    }
                                }

                            }
                        }

                    }

                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                errorForm();

            }
        }


    }


    public double getAmount() {
        return amount;
    }


    public String getTimestamp() {
        return timestamp;
    }


    public Account getAccount() {
        return account;
    }

}










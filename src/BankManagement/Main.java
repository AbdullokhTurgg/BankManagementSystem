package BankManagement;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Bank bank;
    static User loggedUser;
    static double courseOfUs = 80;
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Account> myAzi = new ArrayList<>();


    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Account> accounts = new ArrayList<>();
        ArrayList<Transaction> transactions = new ArrayList<>();

// -----------------------------------------------------    АККАУНТЫ    -----------------------------------------------------
        User abdullokh = new User("Abdullokh", "Azimov", "azi", "0101", myAzi);
        Account kgAbdullokh = new Account(2500, "KGS", abdullokh);
        Account usAbdullokh = new Account(1500, "USD", abdullokh);
        myAzi.add(kgAbdullokh);
        myAzi.add(usAbdullokh);
        accounts.addAll(myAzi);
        users.add(abdullokh);


        User ruslan = new User("Ruslan", "Amonullaev", "rus", "0101", myAzi);
        Account kgRuslan = new Account(3000, "KGS", ruslan);
        Account usRuslan = new Account(1000, "USD", ruslan);
        myAzi.add(kgRuslan);
        myAzi.add(usRuslan);
        accounts.addAll(myAzi);
        users.add(ruslan);


        User adilet = new User("Adilet", "Isakov", "adi", "0101", myAzi);
        Account kgAdilet = new Account(12000, "KGS", adilet);
        Account usAdilet = new Account(1500, "USD", adilet);
        myAzi.add(kgAdilet);
        myAzi.add(usAdilet);
        accounts.addAll(myAzi);
        users.add(adilet);


        User bahtiyar = new User("Bahtiyar", "Kanatbekov", "baha", "0101", myAzi);
        Account kgBahtiyar = new Account(1900, "KGS", bahtiyar);
        Account usBahtiyar = new Account(3200, "USD", bahtiyar);
        myAzi.add(kgBahtiyar);
        myAzi.add(usBahtiyar);
        accounts.addAll(myAzi);
        users.add(bahtiyar);


        User shokh = new User("Shokh", "Hajibaev", "shoh", "0101", myAzi);
        Account kgShoh = new Account(1200, "KGS", shokh);
        Account usShoh = new Account(800, "USD", shokh);
        myAzi.add(kgShoh);
        myAzi.add(usShoh);
        accounts.addAll(myAzi);
        users.add(shokh);


        bank = new Bank("DOS CREDO BANK", users, accounts, transactions);

        mainMenu();
        login();
    }

    // -----------------------------------------------------    ГЛАВНОЕ МЕНЮ    -----------------------------------------------------
    public static void mainMenu() {
        System.out.println("                   --------------------------------------------------------------------------");
        System.out.println("                                       ПРИВЕТСТВУЕМ ВАС В DOS CREDO BANK");
        System.out.println("                   --------------------------------------------------------------------------");
        System.out.println("-------> 1: Зарегистрироваться");
        System.out.println("-------> 2: Уже есть аккаунт");
        System.out.print("       > ");
        int usChoice = 0;
        try {
            usChoice = scanner.nextInt();
            if (usChoice >= 1 && usChoice <= 2) {
                switch (usChoice) {
                    case 1:
                        signIn();
                    case 2:
                        login();


                }

            }
            else {
                choiceRightNum();
                scanner.next();
            }


        } catch (InputMismatchException e) {
            choiceRightNum();
            scanner.next();

        }
    }

    // -----------------------------------------------------    РЕГИСТРАЦИЯ    -----------------------------------------------------
    private static void signIn() {
        System.out.println("-------> Придумайте себе логин");
        System.out.print("       > ");
        String login = scanner.next();
        if (!bank.isHasCheckDuplicates(login)) {
            System.out.println("-------> Ваше имя");
            System.out.print("       > ");
            String name = scanner.next();
            System.out.println("-------> Ваше фамилия");
            System.out.print("       > ");
            String lastname = scanner.next();
            while (true) {
                System.out.println("-------> Придумайте себе пароль");
                System.out.print("       > ");
                String password = scanner.next();
                if (password.length() > 6) {
                    loggedUser = new User(name, lastname, login, password, myAzi);
                    bank.addCustomer(loggedUser);
                    System.out.println();
                    System.out.println("-------> Поздравляем регистрация прошла успешно!");
                    System.out.println();
                    System.out.println("-------> Выберите команду");
                    signInMainMenu();
                    break;
                } else {
                    System.err.println("Пароль должен быть больше шести символов");
                    password = scanner.nextLine();
                }
            }
        } else {
            System.err.println("-------> У нас уже аккаунт с таким логином есть!");
            System.out.println("-------> Попробуйте заново");
            signIn();
        }


    }

    // -----------------------------------------------------    ВХОД В АККАУНТ    -----------------------------------------------------
    private static void login() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        while (true) {
            try {
                System.out.println("-------> Введите свой логин: ");
                System.out.print("       > ");
                String login = scanner.next();
                System.out.println("-------> Введите свой пароль: ");
                System.out.print("       > ");
                String password = scanner.next();
                while (true) {
                    for (User user : bank.getCustomers()) {
                        if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                            loggedUser = user;
                            System.out.println("    Это может занять несколько секунд ...");
                            System.out.println("    Проверка ...");
                            Thread.sleep(2500);
                            signInMainMenu();
                            break;
                        }
                    }
                    if (count < 2) {
                        count++;
                        System.err.println("-------> Неправильный пароль или логин!!  " + "\n-------> Осталось попыток: " + (3 - count) + "  ");
                        System.out.println("-------> Введите свой логин: ");
                        System.out.print("       > ");
                        login = scanner.nextLine();
                        System.out.println("-------> Введите свой пароль:  ");
                        System.out.print("       > ");
                        password = scanner.nextLine();

                    } else {
                        System.err.println("-------> Использовано 3 попыток ввода.  ");
                        System.err.println("-------> Вы не смогли войти программа завершена , поробуйте заново. ");
                        System.exit(0);

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("-------> Ошибка");
            }
        }
    }

    // -----------------------------------------------------    МЕНЮ    -----------------------------------------------------
    public static void signInMainMenu() {
        while (true) {
            try {
                boolean n = false;
                while (true) {
                    System.out.println("-------> 0: Выйти в главное меню");
                    System.out.println("-------> 1: попольнить счет");
                    System.out.println("-------> 2: снять деньги со счета");
                    System.out.println("-------> 3: Перевести деньги на другой счет");
                    System.out.println("-------> 4: История");
                    System.out.println("-------> 5: Войти в другой аккаунт");
                    System.out.println("-------> 6: Настройки");
                    System.out.println("-------> 7: Id своего счета");
                    System.out.print("       > ");
                    int num = scanner.nextInt();
                    if (num >= 0 && num <= 7) {
                        switch (num) {
                            case 0: mainMenu();
                            case 1:
                                Transaction.transferOfFunds();
                                n = true;
                            case 2:
                                Transaction.withMoney();
                                n = true;
                            case 3:
                                Transaction.allTransaction();
                                n = true;
                            case 4:
                                Transaction.infoAboutTrans();
                                n = true;
                            case 5:
                                login();
                                n = true;
                            case 6:
                                userSettings();
                            case 7:{
                                GenId.genId();
                                signInMainMenu();
                            }


                        }
                        if (n) {
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("xxxxxxxx - Ошибка!!! - xxxxxxxx");
                scanner.next();
            }
        }
    }
// -----------------------------------------------------    НАСТРОЙКИ ПОЛЬЗОВАТЕЛЯ    -----------------------------------------------------

    private static void userSettings() {
        System.out.println("-------> 0: Назад");
        System.out.println("-------> 1: Изменить логин");
        System.out.println("-------> 2: Изменить пароль");
        int usChoice = scanner.nextInt();;
            if (usChoice == 0 || usChoice == 1 || usChoice == 2) {
                switch (usChoice) {
                    case 0:
                        signInMainMenu();
                    case 1:
                        changeLogin();
                    case 2:
                        changePassword();
                }
            } else {
                Transaction.errorForm();
                userSettings();
            }
    }
    private static void changePassword() {
        System.out.println("-------> Введите старый пароль");
        String chPass = scanner.next();
        if (chPass.equals(loggedUser.getPassword())) {
            System.out.println("-------> Введите новый пароль");
            String newPass = scanner.next();
            loggedUser.setPassword(newPass);
            System.out.println("-------> Вы успешно изменили свой пароль");
            signInMainMenu();

        }
        else {
            System.err.println("-------> Старый пароль введен неправильно");
            changePassword();
        }
    }

    private static void changeLogin() {
            System.out.println("-------> Введите пароль");
            String chPass = scanner.next();
        if (chPass.equals(loggedUser.getPassword())) {
            System.out.println("-------> Введите новый логин");
            String newLogin = scanner.next();
            loggedUser.setLogin(newLogin);
            System.out.println("-------> Вы успешно изменили свой логин");
            signInMainMenu();
        }
        else {
            System.err.println("-------> пароль введен неправильно");
            changeLogin();
        }



    }
    // -----------------------------------------------------    НЕВЕРНЫЕ ЧИСЛА    -----------------------------------------------------
    private static void choiceRightNum() {
        System.err.println("xxxxxxxx - Выберите существующее число! - xxxxxxxx");
    }
}




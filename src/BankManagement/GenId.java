package BankManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenId {
    public static final List<Integer> accountId = new ArrayList<>();
    private static final List<Integer> somAccount = new ArrayList<>();
    private static final List<Integer> dollarAccount = new ArrayList<>();

    public static int genId() {
        int id;
        while (true) {
            id = new Random().nextInt(100000, 999999);
            if (checkForDuplicates(id)) {
                accountId.add(id);
                break;
            }
        }
        return id;
    }

    private static boolean checkForDuplicates(int id) {
        for (int i : accountId) {
            if (i == id) {
                return false;
            }
        }
        return true;


    }

}

package user;

import java.util.Scanner;

public class IOHelper {

    private IOHelper() {

    }


    //INPUT
    public static String getString() {
        return new Scanner(System.in).next();
    }


    //OUTPUT

    public static void out(String out) {
        System.out.print(out);
    }

    public static String confirm() {
        return new Scanner(System.in).next();
    }

    public static void sep(char c) {
        System.out.println();
        for (int n = 0; n < 70; n++) {
            System.out.print(c);
        }
        System.out.println();
    }

    public static void displayList(String[] list) {
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static void displayListIndex(String[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.println(">" + i + " " + list[i]);
        }

    }
}

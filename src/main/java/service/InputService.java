package service;

import java.util.Scanner;

public class InputService {
    private final Scanner scanner;

    public InputService(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readString() {
        return scanner.nextLine();
    }

    public int readInt() {
        while (true) {
            String number = scanner.nextLine();
            try {
                return Integer.parseInt(number);
            } catch (NumberFormatException e) {
                System.out.println("Введите число: ");
            }
        }
    }
}

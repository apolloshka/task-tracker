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
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }
}

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] product = {"Молоко", "Хлеб", "Гречневая крупа"};
        int[] price = {50, 14, 80};
        int[] quantity = new int[3];
        int total = 0;
        while (true) {
            if (total == 0) {
                System.out.println("Список возможных товаров для покупки");
                for (int i = 0; i < product.length; i++) {
                    System.out.println((i + 1) + ". " + product[i] + " " + price[i] + " руб/шт");
                }
            }
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }
            String[] parts = input.split(" ");
            int num = 0;
            int lot = 0;
            if (parts.length != 2) {
                System.out.println("Введите корректно: номер товара, пробел, количество товара");
                continue;
            }
            try {
                num = Integer.parseInt(parts[0]);
                lot = Integer.parseInt(parts[1]);


            } catch (RuntimeException a) {
                System.out.println("Введена некорректная информация");
            }


            if (num < 0 || num > product.length) {
                System.out.println("Нет такого товара");
                continue;
            } else if (lot < 0) {
                System.out.println("Введено некорректное количество товара");
                continue;
            }

            if (num > 0 && num <= product.length) {
                quantity[num - 1] += lot;
                total += price[num - 1] * lot;
            }
        }

        System.out.println("Ваша корзина:");
        for (int i = 0; i < quantity.length; i++) {
            if (quantity[i] != 0) {
                System.out.println(product[i] + " " + quantity[i] + " шт " + price[i] + " руб/шт " + (quantity[i] * price[i]) + " руб в сумме");
            }
        }
        System.out.println("Итого: " + total + " руб");
    }
}
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] product = {"Молоко", "Хлеб", "Гречневая крупа"};
        String[] productSale = {"Шоколадка", "Чупа-чупс", "Авокадо"};
        String[] allProduct = new String[product.length + productSale.length];

        int[] price = {50, 14, 80};
        int[] priceSale = {100, 5, 50};
        int[] allPrice = new int[price.length + priceSale.length];

        int[] quantity = new int[3];

        int total = 0;
        int num = 0;
        int lot = 0;

        for (int i = 0; i < product.length; i++) {
            allProduct[i] = product[i];
            allProduct[i + product.length] = productSale[i];
            allPrice[i] = price[i];
            allPrice[i + price.length] = priceSale[i];
        }
        while (true) {
            if (total == 0) {
                System.out.println("Список возможных товаров для покупки");
                for (int i = 0; i < allProduct.length; i++) {
                    System.out.println((i + 1) + ". " + allProduct[i] + " " + allPrice[i] + " руб/шт");
                }
            }
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }
            String[] parts = input.split(" ");
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

            if (num < 0 || num > allProduct.length) {
                System.out.println("Нет такого товара");
                continue;
            }
            
            int saveValue = 0;
            if (lot == 0 || (lot < 0 && Math.abs(lot) >= quantity[num - 1])) {
                saveValue = quantity[num - 1];
                quantity[num - 1] = 0;
                total -= allPrice[num - 1] * saveValue;
            }
            if (lot < 0 && Math.abs(lot) < quantity[num - 1]) {
                quantity[num - 1] += lot;
                total += allPrice[num - 1] * lot;
            }
            if (lot > 0) {
                quantity[num - 1] += lot;
                total += allPrice[num - 1] * lot;
            }
        }
        System.out.println("Ваша корзина:");
        int sale = 0;
        for (int i = 0; i < quantity.length; i++) {
            if (quantity[i] != 0) {
                for (int j = 0; j < productSale.length; j++) {
                    if (productSale[j].equals(allProduct[i])) {
                        sale = sale + ((quantity[i] / 3) * allPrice[i]);
                    }
                }
                System.out.println(allProduct[i] + " " + quantity[i] + " шт " + allPrice[i] + " руб/шт " + (quantity[i] * allPrice[i]) + " руб в сумме");
            }
        }
        System.out.println("Итого: " + (total - sale) + " руб");
    }
}
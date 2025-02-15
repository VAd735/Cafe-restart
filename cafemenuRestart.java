package com.Cafe;

import java.util.*;

class OrderItem {
    private final String name; // Назва страви
    private final double price; // Ціна страви

    public OrderItem(String name, double price) {
        this.name = name; // Ініціалізація назви страви
        this.price = price; // Ініціалізація ціни страви
    }

    public String getName() {
        return name; // Отримання назви страви
    }

    public double getPrice() {
        return price; // Отримання ціни страви
    }

    public String toString() {
        return name + " - " + price + " zł"; // Форматування відображення пункту меню
    }
}

public class cafemenuRestart { // Основний клас програми
    private static final Map<Integer, OrderItem> menu = new HashMap<>(); // Меню кафе, ключ - ID страви
    private static final Scanner scan = new Scanner(System.in); // Сканер для вводу даних користувачем
    private static final Random random = new Random(); // Генератор випадкових чисел

    public static void main(String[] args) {  // Основний метод програми
        initializeDefaultMenu(); // Ініціалізація стандартного меню

        while (true) { // Нескінченний цикл для роботи меню
            System.out.println("\nWitaj w kawiarni <<Ptak>>!"); // Вітання користувача
            System.out.println("1. Pokaż menu"); // Вибір для відображення меню
            System.out.println("2. Dodaj pozycję do menu"); // Вибір для додавання пункту в меню
            System.out.println("3. Usuń pozycję z menu"); // Вибір для видалення пункту з меню
            System.out.println("4. Zrób zamówienie"); // Вибір для оформлення замовлення
            System.out.println("5. Wyjście"); // Вибір для виходу з програми
            System.out.println("6. Poleć mi potrawę"); // Вибір для випадкового вибору страви
            System.out.print("Wybierz opcję: "); // Підказка для вибору опції

            int choice = scan.nextInt(); // Зчитування вибору користувача
            scan.nextLine(); // Споживання символу нової лінії після числа

            switch (choice) { // Виконання дії відповідно до вибору користувача
                case 1:
                    viewMenu(); // Відображення меню
                    break;
                case 2:
                    WorkingWithTheMenu(); // Додавання нового пункту до меню
                    break;
                case 3:
                    RemoveItemFromMenu(); // Видалення пункту з меню
                    break;
                case 4:
                    FinalWork(); // Оформлення замовлення
                    break;
                case 5:
                    scan.close(); // Закриття сканера
                    System.out.println("Dziękujemy za odwiedzenie naszej kawiarni <<Ptak>>. Do widzenia!"); // Прощання
                    return; // Вихід з програми
                case 6:
                    suggestRandomItem(); // Випадковий вибір страви
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie."); // Повідомлення про некоректний вибір
            }
        }
    }

    private static void initializeDefaultMenu() {  // Метод для ініціалізації стандартного меню
        menu.put(1, new OrderItem("Kawa", 19));
        menu.put(2, new OrderItem("Herbata", 13));
        menu.put(3, new OrderItem("Sok", 15));
        menu.put(4, new OrderItem("Lody", 10));
        menu.put(5, new OrderItem("Lemoniada", 10));
        menu.put(6, new OrderItem("Hot-dog", 28));
        menu.put(7, new OrderItem("Hamburger", 25));
        menu.put(8, new OrderItem("Naleśniki", 24));
        menu.put(9, new OrderItem("Rogal", 7));
        menu.put(10, new OrderItem("Frytki", 17));
        menu.put(11, new OrderItem("Milk-shake", 20));
    }

    private static void viewMenu() {  // Метод для відображення меню
        System.out.println("\n----- Menu -----");
        if (menu.isEmpty()) { // Перевірка, чи меню не порожнє
            System.out.println("Menu jest puste."); // Якщо порожнє, вивести повідомлення
        } else {
            menu.forEach((key, value) -> System.out.printf("%d. %s\n", key, value)); // Виведення кожного пункту меню
        }
    }
    private static void suggestRandomItem() {  // Метод для випадкового вибору страви з меню
        if (menu.isEmpty()) {  // Перевірка, чи меню не порожнє
            System.out.println("Menu jest puste."); // Якщо порожнє, вивести повідомлення
            return; // Повернення з методу
        }

        List<Integer> keys = new ArrayList<>(menu.keySet()); // Перетворення ключів меню в список
        int randomKey = keys.get(random.nextInt(keys.size())); // Вибір випадкового ключа
        OrderItem randomItem = menu.get(randomKey); // Отримання випадкового пункту меню

        System.out.println("Możesz spróbować: " + randomItem.getName() + " za " + randomItem.getPrice() + " zł!"); // Виведення рекомендованої страви
    }

    private static void WorkingWithTheMenu() {  // Метод для додавання нового пункту до меню
        System.out.print("Wprowadź nazwę potrawy: "); // Підказка для вводу назви страви
        String name = scan.nextLine(); // Зчитування назви страви

        System.out.print("Wprowadź cenę potrawy: "); // Підказка для вводу ціни страви
        double price = scan.nextDouble(); // Зчитування ціни страви
        scan.nextLine(); // Споживання символу нової лінії після числа

        if (price < 0) {  // Перевірка на некоректну (від'ємну) ціну
            System.out.println("Cena nie może być ujemna. Spróbuj ponownie."); // Повідомлення про некоректну ціну
            return; // Повернення з методу
        }

        int newId = menu.isEmpty() ? 1 : Collections.max(menu.keySet()) + 1; // Генерація нового ID для страви
        menu.put(newId, new OrderItem(name, price)); // Додавання нової страви в меню
        System.out.println("Potrawa została dodana do menu!"); // Повідомлення про успішне додавання
    }

    private static void RemoveItemFromMenu() {  // Метод для видалення пункту з меню
        viewMenu(); // Виклик методу для відображення меню
        System.out.print("Wprowadź ID potrawy do usunięcia: "); // Підказка для вводу ID
        int ID = scan.nextInt(); // Зчитування ID страви для видалення
        scan.nextLine(); // Споживання символу нової лінії після числа

        if (menu.containsKey(ID)) {  // Перевірка, чи існує пункт з таким ID
            menu.remove(ID); // Видалення пункту з меню
            System.out.println("Potrawa została usunięta z menu!"); // Повідомлення про успішне видалення
        } else {
            System.out.println("Nieprawidłowe ID. Spróbuj ponownie."); // Повідомлення про некоректний ID
        }
    }

    private static void FinalWork() {  // Метод для оформлення замовлення
        if (menu.isEmpty()) {
            System.out.println("Menu jest puste. Proszę dodać dania.");
            return;
        }

        System.out.print("Wprowadź liczbę dań w zamówieniu: ");
        int itemCount = scan.nextInt(); // Зчитування кількості страв
        scan.nextLine(); // Споживання символу нової лінії

        double suma = 0; // Загальна сума
        StringBuilder bill = new StringBuilder(); // Формування рахунку

        for (int i = 0; i < itemCount; i++) {
            System.out.print("Wprowadź ID dania #" + (i + 1) + ": ");
            int itemId = scan.nextInt();
            scan.nextLine();

            if (menu.containsKey(itemId)) {
                OrderItem item = menu.get(itemId);

                System.out.print("Podaj ilość dla " + item.getName() + ": ");
                int quantity = scan.nextInt();
                scan.nextLine();

                double cost = item.getPrice() * quantity;
                suma += cost;
                bill.append(quantity).append(" x ").append(item.getName())
                        .append(" - ").append(cost).append(" zł\n");
            } else {
                System.out.println("Nieprawidłowe ID dania. Spróbuj ponownie.");
                i--; // Повторний ввід для неправильного ID
            }
        }

        System.out.println("\n--- Twoje konto ---");
        System.out.println(bill); // Виведення рахунку
        System.out.printf("Całkowita kwota: %.2f грн\n", suma);

        System.out.print("Wprowadź podatek (%): ");
        double podatek = scan.nextDouble(); // Зчитування податку
        if (podatek < 0) {
            System.out.println("Podatek nie może być ujemny. Spróbuj ponownie..");
            return;
        }
        double podt = CalculatePercentage(suma, podatek); // Обчислення податку
        System.out.printf("Podatek (%.2f%%): %.2f грн\n", podatek, podt);

        System.out.print("Podaj wskazówkę (%): ");
        double napiwek = scan.nextDouble(); // Зчитування чайових
        if (napiwek < 0) {
            System.out.println("Napiwki nie mogą być ujemne. Spróbuj ponownie..");
            return;
        }
        double napw = CalculatePercentage(suma, napiwek); // Обчислення чайових
        System.out.printf("Napiwki (%.2f%%): %.2f грн\n", napiwek, napw);

        double Total = suma + podt + napw; // Обчислення загальної суми
        System.out.printf("\nRazem z podatkiem i napiwkiem: %.2f грн\n", Total);

        System.out.println("\tDziękujemy za zamówienie! Miłego dnia!");
    }
    private static double CalculatePercentage(double base, double rate) {  // Метод для обчислення відсотків
        return base * (rate / 100);
    }
}

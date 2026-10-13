package org.example.service;

import org.example.dto.Item;

import java.util.List;

public class OrderService {

    private static final String VIP_CUSTOMER = "VIP";
    private static final String NEW_CUSTOMER = "NEW";

    private static final double VIP_DISCOUNT = 0.10;
    private static final double NEW_CUSTOMER_DISCOUNT = 0.05;

    private static final double ADDITIONAL_DISCOUNT_THRESHOLD = 1000.0;
    private static final double ADDITIONAL_DISCOUNT_AMOUNT = 50.0;

    /**
     * Сервис расчитывает итоговую стоимость заказа с учетом скидок.
     * Суммирует стоимость всех товаров с учетом их количества,
     * применяет скидку в зависимости от статуса клиента (10% для ВИП и 5% для новых)
     * и вычитает дополнительную скидку 50, если итоговая сумма превышает 1000.
     *
     * @param items список товаров в заказе
     * @param type тип клиента: VIP, NEW или другой тип без скидки
     * @return итоговая стоимость заказа с учетом примененных скидок
     */
    public double calc(List<Item> items, String type) {
        double total = calculateTotal(items);
        total = applyCustomerDiscount(total, type);
        total = applyAdditionalDiscount(total);

        return total;
    }

    private double calculateTotal(List<Item> items) {
        double total = 0.0;

        for (Item item : items) {
            total += item.getPrice() * item.getQuantity();
        }

        return total;
    }

    private double applyCustomerDiscount(double total, String type) {
        if (VIP_CUSTOMER.equals(type)) {
            return total * (1 - VIP_DISCOUNT);
        } else if (NEW_CUSTOMER.equals(type)) {
            return total * (1 - NEW_CUSTOMER_DISCOUNT);
        }

        return total;
    }

    private double applyAdditionalDiscount(double total) {
        if (total > ADDITIONAL_DISCOUNT_THRESHOLD) {
            return total - ADDITIONAL_DISCOUNT_AMOUNT;
        }

        return total;
    }
}
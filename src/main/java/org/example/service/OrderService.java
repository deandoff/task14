package org.example.service;

import org.example.dto.Item;

import java.util.List;

public class OrderService {

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
        double s = 0;
        for (Item i : items) {
            s += i.getPrice() * i.getQuantity();
        }

        if (type.equals("VIP")) {
            s = s * 0.9;
        }

        if (type.equals("NEW")) {
            s = s * 0.95;
        }

        if (s > 1000) {
            s = s - 50;
        }

        return s;
    }
}
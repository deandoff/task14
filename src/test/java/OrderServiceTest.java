import org.example.dto.Item;
import org.example.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
    }

    @Test
    void shouldCalculateTotalWithoutDiscount() {
        List<Item> items = List.of(
                new Item("Товар 1", 100, 2),
                new Item("Товар 2", 50, 3)
        );

        double result = orderService.calc(items, "REGULAR");

        assertEquals(350.0, result, 0.001);
    }

    @Test
    void shouldApplyVipDiscount() {
        List<Item> items = List.of(
                new Item("Товар", 100, 2)
        );

        double result = orderService.calc(items, "VIP");

        assertEquals(180.0, result, 0.001);
    }

    @Test
    void shouldApplyNewCustomerDiscount() {
        List<Item> items = List.of(
                new Item("Товар", 100, 2)
        );

        double result = orderService.calc(items, "NEW");

        assertEquals(190.0, result, 0.001);
    }

    @Test
    void shouldApplyAdditionalDiscountForTotalAbove1000() {
        List<Item> items = List.of(
                new Item("Товар", 600, 2)
        );

        double result = orderService.calc(items, "REGULAR");

        assertEquals(1150.0, result, 0.001);
    }

    @Test
    void shouldNotApplyAdditionalDiscountForTotalExactly1000() {
        List<Item> items = List.of(
                new Item("Товар", 500, 2)
        );

        double result = orderService.calc(items, "REGULAR");

        assertEquals(1000.0, result, 0.001);
    }

    @Test
    void shouldApplyCustomerDiscountBeforeAdditionalDiscount() {
        List<Item> items = List.of(
                new Item("Товар", 600, 2)
        );

        double result = orderService.calc(items, "VIP");

        assertEquals(1030.0, result, 0.001);
    }

    @Test
    void shouldReturnZeroForEmptyCart() {
        double result = orderService.calc(List.of(), "REGULAR");

        assertEquals(0.0, result, 0.001);
    }

    @Test
    void shouldCalculateQuantityCorrectly() {
        List<Item> items = List.of(
                new Item("Товар", 25, 4)
        );

        double result = orderService.calc(items, "REGULAR");

        assertEquals(100.0, result, 0.001);
    }


    @Test
    void shouldApplyOnePercentDiscountWhenOrderContainsMoreThanTenItems() {
        List<Item> items = List.of(
                new Item("Товар 1", 100, 6),
                new Item("Товар 2", 100, 5)
        );

        double result = orderService.calc(items, "REGULAR");

        assertEquals(1039.0, result, 0.001);
    }

    @Test
    void shouldNotApplyBulkDiscountWhenOrderContainsExactlyTenItems() {
        List<Item> items = List.of(
                new Item("Товар", 100, 10)
        );

        double result = orderService.calc(items, "REGULAR");

        assertEquals(1000.0, result, 0.001);
    }

}

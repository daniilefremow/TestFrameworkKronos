package business_object;

public class Order {
    public static final int FIELDS_NUMBER = 2;

    private String name;
    private String creditCard;

    private Order() {
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getCreditCard() {
        return creditCard;
    }

    private void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public static Order getOrderDetails(String name, String creditCard) {
        Order order = new Order();
        order.setName(name);
        order.setCreditCard(creditCard);

        return order;
    }
}

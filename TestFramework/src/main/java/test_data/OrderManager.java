package test_data;

import business_object.Order;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import utils.PropertyReader;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static final String CSV_FILEPATH = PropertyReader.getProperty("csvOrderDataPath");

    public static Object[][] getOrderObjectArray(List<Order> orderList) {
        Object[][] testData = new Object[orderList.size()][Order.FIELDS_NUMBER];
        for (int i = 0; i < testData.length; i++) {
            int counter = 0;
            testData[i][counter++] = orderList.get(i).getName();
            testData[i][counter] = orderList.get(i).getCreditCard();
        }

        return testData;
    }

    public static List<Order> getCSVOrderList() {
        CSVParser records = DataCSVParser.getCsvRecords(CSV_FILEPATH);
        List<Order> orderList = new ArrayList<>();
        for (CSVRecord record : records) {
            Order order = Order.getOrderDetails(record.get("name"), record.get("creditCard"));
            orderList.add(order);
        }

        return orderList;
    }
}

package OOP;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookingHistory extends JFrame {
    private JPanel mainPanel;

    public BookingHistory() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane);

        List<Booking> bookings = readBookingsFromFile("booking.txt");
        for (Booking booking : bookings) {
            mainPanel.add(createBookingPanel(booking));
        }

        setTitle("Booking History");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private List<Booking> readBookingsFromFile(String fileName) {
        List<Booking> bookings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                Booking booking = new Booking(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8], parts[9], parts[10]);
                bookings.add(booking);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    private JPanel createBookingPanel(Booking booking) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        panel.add(new JLabel("Booking ID: " + booking.bookingId));
        panel.add(new JLabel("Booking Date: " + booking.bookingDate));
        panel.add(new JLabel("User ID: " + booking.userID));
        panel.add(new JLabel("Check-In Date: " + booking.checkInDate));
        panel.add(new JLabel("Days: " + booking.daysValue));
        panel.add(new JLabel("Cleaning Service: " + booking.cleaningService));
        panel.add(new JLabel("Food and Drink Service: " + booking.foodAndDrinkService));
        panel.add(new JLabel("Laundry Service: " + booking.laundryService));
        panel.add(new JLabel("Price: " + booking.price));
        panel.add(new JLabel("Status: " + booking.status));
        panel.add(new JLabel("Chosen Room: " + booking.chosenRoom));

        return panel;
    }

    public static void main(String[] args) {
        new BookingHistory();
    }
}

class Booking {
    String bookingId, bookingDate, userID, checkInDate, daysValue, cleaningService, foodAndDrinkService, laundryService, price, status, chosenRoom;

    public Booking(String bookingId, String bookingDate, String userID, String checkInDate, String daysValue, String cleaningService, String foodAndDrinkService, String laundryService, String price, String status, String chosenRoom) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.userID = userID;
        this.checkInDate = checkInDate;
        this.daysValue = daysValue;
        this.cleaningService = cleaningService;
        this.foodAndDrinkService = foodAndDrinkService;
        this.laundryService = laundryService;
        this.price = price;
        this.status = status;
        this.chosenRoom = chosenRoom;
    }
}
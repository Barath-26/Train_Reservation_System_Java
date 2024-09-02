import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TrainReservationApp {

    public static void main(String[] args) {
        Train[] trains = {
            new Train(1, "CHENNAI", "AC", 100, new BoardingPoint("MADURAI")),
            new Train(2, "GOA", "AC", 200, new BoardingPoint("CHENNAI")),
            new Train(3, "BANGALORE", "AC", 300, new BoardingPoint("TRICHY")),
            new Train(4, "CHENNAI", "NON-AC", 400, new BoardingPoint("TRICHY")),
            new Train(5, "MADURAI", "NON-AC", 150, new BoardingPoint("CHENNAI"))
        };

        TrainReservationApp app = new TrainReservationApp();

        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("ENTER YOUR NAME: ");
        String name = sc.nextLine().toUpperCase();
        System.out.println();
        System.out.print("ENTER YOUR AGE: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println();
        System.out.println();
        app.displayAvailableTrains(new ArrayList<>(List.of(trains)));
        System.out.println();
        System.out.print("Do you want to book a train ticket? (yes/no): ");
        String bookingChoice = sc.nextLine().toLowerCase();
        System.out.println();
        if (bookingChoice.equals("yes")) {
            System.out.print("ENTER THE STARTING POINT: ");
            String from = sc.nextLine().toUpperCase();
            System.out.println();
            System.out.print("ENTER THE DESTINATION: ");
            String destination = sc.nextLine().toUpperCase();
            List<Train> availableTrains = app.findAvailableTrains(trains, from, destination);
            if (availableTrains.isEmpty()) {
                System.out.println("No trains available between " + from + " and " + destination);
                System.out.println();
                return;
            }
            app.displayAvailableTrains(availableTrains);
            System.out.println();
            System.out.print("ENTER THE TRAIN NUMBER TO BOOK: ");
            int selectedTrainNumber = sc.nextInt();
            System.out.println();
            Train selectedTrain = null;
            for (Train train : availableTrains) {
                if (train.getId() == selectedTrainNumber) {
                    selectedTrain = train;
                    break;
                }
            }

            if (selectedTrain == null) {
                System.out.println("Invalid train number.");
                return;
            }
            System.out.println();
            System.out.print("ENTER NUMBER OF SEATS TO BOOK: ");
            int numSeatsToBook = sc.nextInt();
            if (numSeatsToBook < 1 || numSeatsToBook > selectedTrain.getAvailableSeats()) {
                System.out.println("Invalid number of seats.");
                return;
            }

            List<Passenger> passengers = new ArrayList<>();
            for (int i = 1; i <= numSeatsToBook; i++) {
                System.out.println();
                System.out.print("ENTER PASSENGER " + i + " NAME: ");
                System.out.println();
                String passengerName = sc.next();
                System.out.println();
                System.out.print("ENTER PASSENGER " + i + " AGE: ");
                System.out.println();
                int passengerAge = sc.nextInt();
                passengers.add(new Passenger(passengerName, passengerAge));
            }
            System.out.println();
            app.bookSeats(selectedTrain, numSeatsToBook);
            app.showTicketSummary(name, age, from, selectedTrain, destination, passengers);
	    System.out.println("Thank you!");
        } else {
            System.out.println("Thank you!");
        }
    }

    private List<Train> findAvailableTrains(Train[] trains, String from, String to) {
        List<Train> availableTrains = new ArrayList<>();
        for (Train train : trains) {
            if (train.getBoardingPoint().getBoarding().equals(from) &&
                train.getDestination().equals(to)) {
                availableTrains.add(train);
            }
        }
        return availableTrains;
    }

    private void displayAvailableTrains(List<Train> trains) {
        System.out.println();
        System.out.println("AVAILABLE TRAINS:");
	System.out.println();
        for (Train train : trains) {
            System.out.println("TRAIN ID: " + train.getId() +
                               " \nSTARTING POINT: " + train.getBoardingPoint().getBoarding() +
                               " \nDESTINATION: " + train.getDestination() +
                               " \nTYPE: " + train.getType());
	    System.out.println();
        }
        System.out.println();
    }

    private void bookSeats(Train train, int numSeats) {
        for (int i = 0; i < numSeats; i++) {
            boolean booked = train.bookSeat();
            if (!booked) {
                System.out.println("Booking failed. No available seats.");
                System.out.println();
                break;
            }
        }
    }

    private void showTicketSummary(String name, int age, String from, Train train, String destination, List<Passenger> passengers) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ticket Summary");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLayout(new BorderLayout());

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            StringBuilder sb = new StringBuilder();
            sb.append("BOOKED TICKET DETAILS\n");
            sb.append("NAME: ").append(name).append("\n");
            sb.append("AGE: ").append(age).append("\n");
            sb.append("STARTING POINT: ").append(from).append("\n");
            sb.append("TRAIN NUMBER: ").append(train.getId()).append("\n");
            sb.append("DESTINATION: ").append(destination).append("\n\n");
            sb.append("PASSENGER DETAILS:\n");
            for (Passenger passenger : passengers) {
                sb.append("NAME: ").append(passenger.getName()).append(" AGE: ").append(passenger.getAge()).append("\n");
            }

            textArea.setText(sb.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            frame.add(scrollPane, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());

            JButton downloadButton = new JButton("Download Ticket");
            downloadButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try (FileWriter fileWriter = new FileWriter("TicketSummary.txt")) {
                        fileWriter.write(sb.toString());
                        JOptionPane.showMessageDialog(frame, "Ticket saved as TicketSummary.txt");
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(frame, "Error saving the ticket.");
                    }
                }
            });
            buttonPanel.add(downloadButton);

            frame.add(buttonPanel, BorderLayout.SOUTH);

            frame.getContentPane().setBackground(Color.LIGHT_GRAY);
            textArea.setBackground(Color.WHITE);
            textArea.setForeground(Color.BLACK);
            textArea.setFont(new Font("Arial", Font.PLAIN, 14));
            downloadButton.setBackground(Color.GREEN);
            downloadButton.setForeground(Color.WHITE);
            downloadButton.setFont(new Font("Arial", Font.BOLD, 12));

            frame.setVisible(true);
        });
    }
}

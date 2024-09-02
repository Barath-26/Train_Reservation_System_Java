import javax.swing.*;
import java.awt.event.*;

class TrainReservationSystem extends JFrame {
    JLabel ticketDetails;

    public TrainReservationSystem(String ticketInfo) {
        super("Booked Ticket Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(null);

        ticketDetails = new JLabel();
        ticketDetails.setBounds(50, 50, 300, 100);
        ticketDetails.setText(ticketInfo);
        add(ticketDetails);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(150, 200, 80, 30);
        add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void setTicketDetails(String details) {
        ticketDetails.setText(details);
    }
}

import java.io.Serializable;

class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    String passengerName;
    int passengerAge;
    String startingPoint;
    String destination;
    int trainNumber;
    int seatNumber;

    Ticket(String passengerName, int passengerAge, String startingPoint, String destination, int trainNumber, int seatNumber) {
        this.passengerName = passengerName;
        this.passengerAge = passengerAge;
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.trainNumber = trainNumber;
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "TICKET DETAILS\n" +
               "TRAIN NUMBER: " + trainNumber + "\n" +
               "SEAT NUMBER: " + seatNumber + "\n" +
               "PASSENGER NAME: " + passengerName + "\n" +
               "PASSENGER AGE: " + passengerAge + "\n" +
               "STARTING POINT: " + startingPoint + "\n" +
               "DESTINATION: " + destination + "\n";
    }
}

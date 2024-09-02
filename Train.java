public class Train {
    private int id;
    private String destination;
    private String type;
    private int capacity;
    private int availableSeats;
    private BoardingPoint boardingPoint;

    public Train(int id, String destination, String type, int capacity, BoardingPoint boardingPoint) {
        this.id = id;
        this.destination = destination;
        this.type = type;
        this.capacity = capacity;
        this.availableSeats = capacity;
        this.boardingPoint = boardingPoint;
    }

    public int getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public String getType() {
        return type;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public BoardingPoint getBoardingPoint() {
        return boardingPoint;
    }

    public boolean bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
            return true;
        }
        return false;
    }
}

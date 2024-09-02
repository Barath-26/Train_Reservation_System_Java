import java.io.*;

public class PassengerInfoThread implements Runnable {
    private int trainNumber;
    private int seatNumber;
    private String name;
    private int age;

    public PassengerInfoThread(int trainNumber, int seatNumber, String name, int age) {
        this.trainNumber = trainNumber;
        this.seatNumber = seatNumber;
        this.name = name;
        this.age = age;
    }

    @Override
    public void run() {
        try (FileWriter fw = new FileWriter("PassengerInfo.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("TRAIN NUMBER : " + trainNumber + " SEAT NUMBER : " + seatNumber + " NAME : " + name + " AGE : " + age);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

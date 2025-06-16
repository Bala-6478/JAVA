import java.io.*;
import java.util.*;

class MovieTicketBooking {

    static Scanner sc = new Scanner(System.in);
    static String fileName = "bookings.txt";

    // Sample data
    static String[] movies = {"1. Inception", "2. Interstellar", "3. Joker"};
    static String[] times = {"1. 10:00 AM", "2. 1:00 PM", "3. 6:00 PM"};

    static Set<String> bookedSeats = new HashSet<>();

    public static void main(String[] args) throws IOException {
        loadBookedSeats();

        System.out.println("🎬 Welcome to Movie Ticket Booking System");

        System.out.print("\nEnter your name: ");
        String name = sc.nextLine();

        int movieIndex = chooseOption("Select a Movie:", movies);
        int timeIndex = chooseOption("Select Showtime:", times);

        String seat;
        while (true) {
            System.out.print("Choose seat number (e.g., A1, B2): ");
            seat = sc.nextLine().toUpperCase();

            if (!bookedSeats.contains(seat)) {
                bookedSeats.add(seat);
                break;
            } else {
                System.out.println("⚠ Seat already booked. Choose another one.");
            }
        }

        // Save booking
        saveBooking(name, movies[movieIndex], times[timeIndex], seat);

        System.out.println("\n✅ Booking Confirmed!");
        System.out.println("Name: " + name);
        System.out.println("Movie: " + movies[movieIndex].substring(3));
        System.out.println("Time: " + times[timeIndex].substring(3));
        System.out.println("Seat: " + seat);
    }

    static int chooseOption(String prompt, String[] options) {
        System.out.println("\n" + prompt);
        for (String option : options) {
            System.out.println(option);
        }

        int choice;
        while (true) {
            System.out.print("Enter choice number: ");
            choice = sc.nextInt();
            sc.nextLine(); // Clear newline

            if (choice >= 1 && choice <= options.length) {
                return choice - 1;
            } else {
                System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    static void saveBooking(String name, String movie, String time, String seat) throws IOException {
        FileWriter fw = new FileWriter(fileName, true);
        fw.write(name + "," + movie.substring(3) + "," + time.substring(3) + "," + seat + "\n");
        fw.close();
    }

    static void loadBookedSeats() throws IOException {
        File file = new File(fileName);
        if (!file.exists()) return;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 4) {
                bookedSeats.add(data[3]);
            }
        }
        br.close();
    }
}

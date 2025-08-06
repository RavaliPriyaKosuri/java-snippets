package javaSnippets.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TimeZoneConverter {

    public static ZonedDateTime convertToTimeZone(LocalDateTime dateTime, ZoneId fromZone, String toZone) {
        ZonedDateTime sourceZoned = dateTime.atZone(fromZone);
        return sourceZoned.withZoneSameInstant(ZoneId.of(toZone));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            // Get system time zone
            ZoneId systemZone = ZoneId.systemDefault();
            System.out.println("System time zone detected: " + systemZone);

            // Take date and time input
            System.out.print("Enter date and time (yyyy-MM-dd HH:mm): ");
            String inputDateTime = scanner.nextLine();
            LocalDateTime dateTime = LocalDateTime.parse(inputDateTime, formatter);

            // Take target time zone input
            System.out.print("Enter target time zone (e.g., America/New_York): ");
            String toZone = scanner.nextLine();

            // Perform conversion
            ZonedDateTime converted = convertToTimeZone(dateTime, systemZone, toZone);

            System.out.println("\nOriginal time in " + systemZone + ": " + dateTime);
            System.out.println("Converted time in " + toZone + ": " + converted.format(formatter));
        } catch (Exception e) {
            System.out.println("Invalid input. Please ensure the date format and target time zone are correct.");
        } finally {
            scanner.close();
        }
    }
}

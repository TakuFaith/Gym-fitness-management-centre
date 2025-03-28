package cw2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FitAll {
    private static List<Session> sessions = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeSessions();
        
        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    displaySessionTimetable();
                    break;
                case 2:
                    registerForSession();
                    break;
                case 3:
                    cancelBooking();
                    break;
                case 4:
                    System.out.println("Thank you for using FitAll. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeSessions() {
        try {
            sessions.add(new YogaSession("Morning Yoga", "Low", "Tuesday", "09:00 AM", 
                60, 10, "Hatha", 10));
            sessions.add(new PilatesSession("Pilates Basics", "Medium", "Wednesday", 
                "2:00 PM", 45, 1, "Mat, water", "Beginner"));
            sessions.add(new ZumbaSession("Zumba Dance", "High", "Friday", "6:00 PM", 
                50, 15, "Salsa", 300));
        } catch (SessionException e) {
            System.out.println("Error initializing sessions: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void displayMenu() {
        System.out.println("\nFitAll Fitness Club Management System");
        System.out.println("1. Display session timetable");
        System.out.println("2. Register for a session");
        System.out.println("3. Cancel a booking");
        System.out.println("4. Exit");
    }

    private static void displaySessionTimetable() {
        System.out.println("\nSessionID | SessionName     | Fitness Level  | Day       | Start Time | Duration | Available | Additional Info");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        for (Session session : sessions) {
            session.showDetails();
        }
    }
//handles participant registration
    private static void registerForSession() {
        displaySessionTimetable();
        int sessionID = getIntInput("Enter Session ID: ");
        String participantName = getStringInput("Enter Participant Name: ");
        
        try {
            Session session = findSessionById(sessionID);
            session.book(sessionID, participantName);
            System.out.printf("Registration successful! %s has been added to %s (Session ID: %d).\n",
                participantName, session.getSessionName(), sessionID);
            System.out.println("Remaining spaces: " + session.getSpacesLeft());
        } catch (SessionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
//handles participant cancellation
    private static void cancelBooking() {
        displaySessionTimetable();
        int sessionID = getIntInput("Enter Session ID: ");
        String participantName = getStringInput("Enter Participant Name: ");
        
        try {
            Session session = findSessionById(sessionID);
            session.cancel(sessionID, participantName);
            System.out.printf("Cancellation successful! %s has been removed from %s (Session ID: %d).\n",
                participantName, session.getSessionName(), sessionID);
            System.out.println("Remaining spaces: " + session.getSpacesLeft());
        } catch (SessionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
//This is a helper to find Id else throws exception
    private static Session findSessionById(int sessionID) throws SessionException {
        for (Session session : sessions) {
            if (session.getSessionID() == sessionID) {
                return session;
            }
        }
        throw new SessionException("Session with ID " + sessionID + " not found.");
    }
//gets integer input with validation
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
//gets string input with trimming
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}

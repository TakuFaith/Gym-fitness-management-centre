package cw2;

import java.util.ArrayList;
import java.util.List;
//The class fields are declared as protected to provide subclass-only access while maintaining encapsulation
public abstract class Session implements Bookable {
    private static int nextID = 1;
    protected int sessionID;
    protected String sessionName;
    protected String requiredFitnessLevel;
    protected String day;
    protected String startTime;
    protected int duration;
    protected int spacesLeft;
    protected List<String> participants;

    public Session(String sessionName, String requiredFitnessLevel, String day, 
                  String startTime, int duration, int maxParticipants) throws SessionException {
        if (sessionName == null || sessionName.trim().isEmpty()) {
            throw new SessionException("Session name cannot be empty");
        }
        if (maxParticipants <= 0) {
            throw new SessionException("Max participants must be positive");
        }
        
        this.sessionID = nextID++;
        this.sessionName = sessionName;
        this.requiredFitnessLevel = requiredFitnessLevel;
        this.day = day;
        this.startTime = startTime;
        this.duration = duration;
        this.spacesLeft = maxParticipants;
        this.participants = new ArrayList<>();
    }

    public void showDetails() {
        System.out.printf("%-10d | %-15s | %-15s | %-10s | %-10s | %-8d | %-10d | ",
                sessionID, sessionName, requiredFitnessLevel, day, startTime, duration, spacesLeft);
        showAdditionalInfo();
    }

    protected abstract void showAdditionalInfo();

    protected void updateSpaces(int change) {
        spacesLeft += change;
    }

    public int getSessionID() {
        return sessionID;
    }

    public String getSessionName() {
        return sessionName;
    }

    public int getSpacesLeft() {
        return spacesLeft;
    }

    public List<String> getParticipants() {
        return participants;
    }
}
package cw2;

public class ZumbaSession extends Session {
    private String danceStyle;
    private int energyLevel;

    public ZumbaSession(String sessionName, String requiredFitnessLevel, String day, 
                       String startTime, int duration, int maxParticipants, 
                       String danceStyle, int energyLevel) throws SessionException {
        super(sessionName, requiredFitnessLevel, day, startTime, duration, maxParticipants);
        if (danceStyle == null || danceStyle.trim().isEmpty()) {
            throw new SessionException("Dance style cannot be empty");
        }
        if (energyLevel <= 0) {
            throw new SessionException("Energy level must be positive");
        }
        this.danceStyle = danceStyle;
        this.energyLevel = energyLevel;
    }

    @Override
    protected void showAdditionalInfo() {
        System.out.printf("Dance: %s, Energy: %d cal\n", danceStyle, energyLevel);
    }

    @Override
    public void book(int sessionID, String participantName) throws SessionException {
        validateSessionID(sessionID);
        validateParticipantName(participantName);
        
        if (spacesLeft <= 0) {
            throw new SessionException("Session is full! Cannot register " + participantName + 
                                     " for " + sessionName + " (Session ID: " + sessionID + ").");
        }
        if (participants.contains(participantName)) {
            throw new SessionException(participantName + " is already registered for this session.");
        }
        participants.add(participantName);
        updateSpaces(-1);
    }

    @Override
    public void cancel(int sessionID, String participantName) throws SessionException {
        validateSessionID(sessionID);
        validateParticipantName(participantName);
        
        if (!participants.contains(participantName)) {
            throw new SessionException(participantName + " is not registered for this session.");
        }
        participants.remove(participantName);
        updateSpaces(1);
    }

    private void validateSessionID(int sessionID) throws SessionException {
        if (this.sessionID != sessionID) {
            throw new SessionException("Session ID mismatch");
        }
    }

    private void validateParticipantName(String participantName) throws SessionException {
        if (participantName == null || participantName.trim().isEmpty()) {
            throw new SessionException("Participant name cannot be empty");
        }
    }
}

package cw2;

public class PilatesSession extends Session {
    private String equipmentNeeded;
    private String coreFocusLevel;
    
    public PilatesSession(String sessionName, String requiredFitnessLevel, String day, 
                         String startTime, int duration, int maxParticipants, 
                         String equipmentNeeded, String coreFocusLevel) throws SessionException {
        super(sessionName, requiredFitnessLevel, day, startTime, duration, maxParticipants);
        
        if (equipmentNeeded == null || equipmentNeeded.trim().isEmpty()) {
            throw new SessionException("Equipment needed cannot be empty");
        }
        if (coreFocusLevel == null || coreFocusLevel.trim().isEmpty()) {
            throw new SessionException("Core focus level cannot be empty");
        }
        
        this.equipmentNeeded = equipmentNeeded;
        this.coreFocusLevel = coreFocusLevel;
    }

    @Override
    protected void showAdditionalInfo() {
        System.out.printf("Equipment: %s, Focus: %s\n", equipmentNeeded, coreFocusLevel);
    }

    @Override
    public void book(int sessionID, String participantName) throws SessionException {
        validateSessionID(sessionID);
        validateParticipantName(participantName);
        
        if (spacesLeft <= 0) {
            throw new SessionException(String.format(
                " Sorry!Session is full! Cannot register %s for %s (Session ID: %d).",
                participantName, sessionName, sessionID));
        }
        
        if (participants.contains(participantName)) {
            throw new SessionException(participantName + " is already registered");
        }
        
        participants.add(participantName);
        updateSpaces(-1);
    }

    @Override
    public void cancel(int sessionID, String participantName) throws SessionException {
        validateSessionID(sessionID);
        validateParticipantName(participantName);
        
        if (!participants.contains(participantName)) {
            throw new SessionException(participantName + " is not registered");
        }
        
        participants.remove(participantName);
        updateSpaces(1);
    }

    // Validates session ID matches this instance
    private void validateSessionID(int sessionID) throws SessionException {
        if (this.sessionID != sessionID) {
            throw new SessionException("Invalid session ID for this Pilates session");
        }
    }

    // Ensures participant name meets minimum requirements
    private void validateParticipantName(String participantName) throws SessionException {
        if (participantName == null || participantName.trim().isEmpty()) {
            throw new SessionException("Participant name required for Pilates booking");
        }
    }
}

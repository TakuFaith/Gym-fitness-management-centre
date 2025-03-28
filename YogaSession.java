package cw2;

public class YogaSession extends Session {
    private String yogaStyle;
    private int meditationTime;
//throws a SessionException if validation fails for session parameters
    public YogaSession(String sessionName, String requiredFitnessLevel, String day, 
                      String startTime, int duration, int maxParticipants, 
                      String yogaStyle, int meditationTime) throws SessionException {
        super(sessionName, requiredFitnessLevel, day, startTime, duration, maxParticipants);
        if (yogaStyle == null || yogaStyle.trim().isEmpty()) {
            throw new SessionException("Yoga style cannot be empty");
        }
        if (meditationTime < 0) {
            throw new SessionException("Meditation time cannot be negative");
        }
        this.yogaStyle = yogaStyle;
        this.meditationTime = meditationTime;
    }

    @Override
    protected void showAdditionalInfo() {
        System.out.printf("Style: %s, Meditation: %d min\n", yogaStyle, meditationTime);
    }

    @Override
    public void book(int sessionID, String participantName) throws SessionException {
        validateSessionID(sessionID);
        validateParticipantName(participantName);
        //checks availability before booking
        if (spacesLeft <= 0) {
            throw new SessionException(" Sorry!The session is full! Cannot register " + participantName + " for " + sessionName + " (Session ID: " + sessionID + ").");
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
//this validates that the operation is being performed on the correct session
    private void validateSessionID(int sessionID) throws SessionException {
        if (this.sessionID != sessionID) {
            throw new SessionException("Session ID mismatch");
        }
    }
//Checks that the perticipant's name meets the basic requirement
    private void validateParticipantName(String participantName) throws SessionException {
        if (participantName == null || participantName.trim().isEmpty()) {
            throw new SessionException("Participant name cannot be empty");
        }
    }
}

package cw2;
//Interface defining methods for booking and canceling sessions
	public interface Bookable {
	    void book(int sessionId, String participantName) throws SessionException;
	    void cancel(int sessionId, String participantName) throws SessionException;
	}

# FitAll Fitness Club Management System 💪
A Java application for managing fitness class bookings with object-oriented design principles.

## Features ✨

- **Session Management**
  - Yoga, Pilates, and Zumba session types
  - Auto-generated session IDs
  - Real-time space availability

- **User Operations**
  - Book/Cancel sessions
  - Participant name validation
  - Error handling for full sessions

- **Timetable Display**
  - Formatted session details
  - Specialized info per session type

## Class Diagram 📐
```mermaid
classDiagram
    class Bookable {
        <<interface>>
        +book()
        +cancel()
    }
    
    class Session {
        <<abstract>>
        -sessionID
        -sessionName
        -spacesLeft
        +showDetails()
        #updateSpaces()
    }
    
    class YogaSession
    class PilatesSession
    class ZumbaSession
    
    Bookable <|.. Session
    Session <|-- YogaSession
    Session <|-- PilatesSession
    Session <|-- ZumbaSession

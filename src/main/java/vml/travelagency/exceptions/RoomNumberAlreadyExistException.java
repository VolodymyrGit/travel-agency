package vml.travelagency.exceptions;

public class RoomNumberAlreadyExistException extends RuntimeException{

    public RoomNumberAlreadyExistException() {}

    public RoomNumberAlreadyExistException(Long roomNumber) {
        super(String.format("Room with number - %s already exist", roomNumber));
    }

    public RoomNumberAlreadyExistException(String message) {
        super(message);
    }
}

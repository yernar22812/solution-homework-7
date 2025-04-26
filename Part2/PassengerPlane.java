package Part2;

public class PassengerPlane extends Aircraft {
    public PassengerPlane(String id, int fuelLevel) {
        super(id, fuelLevel);
    }

    @Override
    public void receive(String message) {
        System.out.println("[PassengerPlane " + id + "] Received: " + message);
    }
}
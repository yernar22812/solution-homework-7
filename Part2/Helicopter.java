package Part2;

public class Helicopter extends Aircraft {
    public Helicopter(String id, int fuelLevel) {
        super(id, fuelLevel);
    }

    @Override
    public void receive(String message) {
        System.out.println("[Helicopter " + id + "] Received: " + message);
    }
}
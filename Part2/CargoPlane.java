package Part2;
public class CargoPlane extends Aircraft {
    public CargoPlane(String id, int fuelLevel) {
        super(id, fuelLevel);
    }

    @Override
    public void receive(String message) {
        System.out.println("[CargoPlane " + id + "] Received: " + message);
    }
}
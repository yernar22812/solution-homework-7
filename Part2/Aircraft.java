package Part2;

public abstract class Aircraft {
    protected String id;
    protected int fuelLevel; // 0 = критический, 100 = полный

    public Aircraft(String id, int fuelLevel) {
        this.id = id;
        this.fuelLevel = fuelLevel;
    }

    public abstract void receive(String message);

    public void send(String message, TowerMediator mediator) {
        mediator.broadcast(message, this);
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public String getId() {
        return id;
    }
}
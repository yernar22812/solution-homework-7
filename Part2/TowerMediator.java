package Part2;
public interface TowerMediator {
    void broadcast(String message, Aircraft sender);
    boolean requestRunway(Aircraft a);
}
package Part2;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ControlTower implements TowerMediator {
    private Queue<Aircraft> landingQueue = new ConcurrentLinkedQueue<>();
    private Queue<Aircraft> takeoffQueue = new ConcurrentLinkedQueue<>();
    private List<Aircraft> allAircraft = new ArrayList<>();
    private boolean runwayBusy = false;

    public void registerAircraft(Aircraft a) {
        allAircraft.add(a);
    }

    @Override
    public synchronized void broadcast(String message, Aircraft sender) {
        if (message.equalsIgnoreCase("MAYDAY")) {
            System.out.println("[Tower] EMERGENCY! " + sender.getId() + " needs immediate landing!");
            notifyAllHold(sender);
            clearRunway();
            landingQueue.add(sender);
        } else {
            System.out.println("[Tower] Broadcast from " + sender.getId() + ": " + message);
            for (Aircraft a : allAircraft) {
                if (!a.equals(sender)) {
                    a.receive(message);
                }
            }
        }
    }

    @Override
    public synchronized boolean requestRunway(Aircraft a) {
        if (runwayBusy) {
            System.out.println("[Tower] Runway busy. Adding " + a.getId() + " to queue.");
            if (a.getFuelLevel() <= 10) { // emergency landing due to low fuel
                landingQueue.add(a); // emergency to landing queue
            } else {
                takeoffQueue.add(a);
            }
            return false;
        } else {
            System.out.println("[Tower] Runway cleared for " + a.getId());
            runwayBusy = true;
            return true;
        }
    }

    private void notifyAllHold(Aircraft except) {
        for (Aircraft a : allAircraft) {
            if (!a.equals(except)) {
                a.receive("[Tower] HOLD POSITION - Emergency in progress.");
            }
        }
    }

    private void clearRunway() {
        landingQueue.clear();
        takeoffQueue.clear();
        runwayBusy = false;
    }

    public synchronized void releaseRunway() {
        runwayBusy = false;
        System.out.println("[Tower] Runway is now free.");
    }
}

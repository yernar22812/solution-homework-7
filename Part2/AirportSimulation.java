package Part2;

import java.util.*;
import java.util.concurrent.*;

public class AirportSimulation {
    public static void main(String[] args) {
        ControlTower tower = new ControlTower();
        Random random = new Random();

        List<Aircraft> aircrafts = new ArrayList<>();

        // Генерация 10 случайных самолётов
        for (int i = 0; i < 10; i++) {
            int fuel = random.nextInt(100);
            Aircraft a;
            switch (random.nextInt(3)) {
                case 0:
                    a = new PassengerPlane("Passenger-" + i, fuel);
                    break;
                case 1:
                    a = new CargoPlane("Cargo-" + i, fuel);
                    break;
                default:
                    a = new Helicopter("Helicopter-" + i, fuel);
            }
            aircrafts.add(a);
            tower.registerAircraft(a);
        }

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(aircrafts.size());

        for (Aircraft a : aircrafts) {
            executor.scheduleAtFixedRate(() -> {
                if (a.getFuelLevel() < 5) {
                    a.send("MAYDAY", tower);
                } else {
                    if (tower.requestRunway(a)) {
                        System.out.println(a.getId() + " is using the runway...");
                        try {
                            Thread.sleep(1000); // simulate runway use
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        tower.releaseRunway();
                    }
                }
            }, 0, 2, TimeUnit.SECONDS);
        }

        executor.schedule(() -> {
            System.out.println("Simulation ending...");
            executor.shutdown();
        }, 30, TimeUnit.SECONDS);
    }
}

package core;

import academic.Request;
import users.Employee;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class DataStore {
    private static final String STATE_FILE = "./data/university.ser";

    private DataStore() {}

    public static void saveState() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STATE_FILE))) {
            Snapshot snapshot = new Snapshot(University.getInstance(), Employee.getRequestInstance());
            oos.writeObject(snapshot);
        } catch (Exception e) {
            System.out.println("Cannot save serialized state");
        }
    }

    public static boolean loadState() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STATE_FILE))) {
            Snapshot snapshot = (Snapshot) ois.readObject();
            University.setInstance(snapshot.university);
            Employee.setRequestInstance(snapshot.requests);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static class Snapshot implements Serializable {
        private final University university;
        private final List<Request> requests;

        private Snapshot(University university, List<Request> requests) {
            this.university = university;
            this.requests = requests == null ? new ArrayList<Request>() : new ArrayList<Request>(requests);
        }
    }
}

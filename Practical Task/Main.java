import java.util.*;
class Student {
    String uid;
    String name;
    int fineAmount;
    int currentBorrowCount;

    public Student(String uid, String name, int fineAmount, int currentBorrowCount) {
        this.uid = uid;
        this.name = name;
        this.fineAmount = fineAmount;
        this.currentBorrowCount = currentBorrowCount;
    }
}

class Asset {
    String assetId;
    String assetName;
    boolean available;
    int securityLevel; 

    public Asset(String assetId, String assetName, boolean available, int securityLevel) {
        this.assetId = assetId;
        this.assetName = assetName;
        this.available = available;
        this.securityLevel = securityLevel;
    }
}

class CheckoutRequest {
    String uid;
    String assetId;
    int hoursRequested;

    public CheckoutRequest(String uid, String assetId, int hoursRequested) {
        this.uid = uid;
        this.assetId = assetId;
        this.hoursRequested = hoursRequested;
    }
}

class ValidationUtil {

    public static void validateUid(String uid) throws IllegalArgumentException {
        if (uid == null || uid.length() < 8 || uid.length() > 12 || uid.contains(" ")) {
            throw new IllegalArgumentException("Invalid UID format");
        }
    }

    public static void validateAssetId(String assetId) throws IllegalArgumentException {
        if (assetId == null || !assetId.matches("LAB-\\d+")) {
            throw new IllegalArgumentException("Invalid Asset ID format");
        }
    }

    public static void validateHours(int hrs) throws IllegalArgumentException {
        if (hrs < 1 || hrs > 6) {
            throw new IllegalArgumentException("Hours must be between 1 and 6");
        }
    }
}


class AssetStore {
    HashMap<String, Asset> assets = new HashMap<>();

    public void addAsset(Asset a) {
        assets.put(a.assetId, a);
    }

    public Asset findAsset(String assetId) throws NullPointerException {
        Asset a = assets.get(assetId);
        if (a == null) {
            throw new NullPointerException("Asset not found: " + assetId);
        }
        return a;
    }

    public void markBorrowed(Asset a) throws IllegalStateException {
        if (!a.available) {
            throw new IllegalStateException("Asset already borrowed");
        }
        a.available = false;
    }
}


class AuditLogger {
    public static void log(String msg) {
        System.out.println("AUDIT: " + msg);
    }

    public static void logError(Exception e) {
        System.out.println("ERROR: " + e.getClass().getSimpleName() + " - " + e.getMessage());
    }
}


class CheckoutService {

    HashMap<String, Student> students;
    AssetStore store;

    public CheckoutService(HashMap<String, Student> students, AssetStore store) {
        this.students = students;
        this.store = store;
    }

    public String checkout(CheckoutRequest req)
            throws IllegalArgumentException, IllegalStateException,
                   SecurityException, NullPointerException {

        
        ValidationUtil.validateUid(req.uid);
        ValidationUtil.validateAssetId(req.assetId);
        ValidationUtil.validateHours(req.hoursRequested);

        
        Student s = students.get(req.uid);
        if (s == null) {
            throw new NullPointerException("Student not found: " + req.uid);
        }

        Asset a = store.findAsset(req.assetId);

     

        if (s.fineAmount > 0) {
            throw new IllegalStateException("Outstanding fine exists");
        }

        if (s.currentBorrowCount >= 2) {
            throw new IllegalStateException("Borrow limit exceeded");
        }

        if (!a.available) {
            throw new IllegalStateException("Asset not available");
        }

        if (a.securityLevel == 3 && !req.uid.startsWith("KRG")) {
            throw new SecurityException("Restricted asset. KRG UID required.");
        }

      

        int hours = req.hoursRequested;

        if (hours == 6) {
            System.out.println("Note: Max duration selected. Return strictly on time.");
        }

        if (a.assetName.contains("Cable") && hours > 3) {
            hours = 3;
            System.out.println("Policy applied: Cables can be issued max 3 hours. Updated to 3.");
        }

   
        store.markBorrowed(a);
        s.currentBorrowCount++;

        
        String txn = "TXN-20260221-" + a.assetId + "-" + s.uid;

        return txn;
    }
}


public class Main {

    public static void main(String[] args) {

        
        HashMap<String, Student> students = new HashMap<>();
        students.put("KRG20281", new Student("KRG20281", "Shruti", 0, 0));
        students.put("ABC12345", new Student("ABC12345", "Rahul", 100, 0)); 
        students.put("KRG99999", new Student("KRG99999", "Shreya", 0, 2));    

       
        AssetStore store = new AssetStore();
        store.addAsset(new Asset("LAB-101", "HDMI Cable", true, 1));
        store.addAsset(new Asset("LAB-202", "Arduino Kit", true, 2));
        store.addAsset(new Asset("LAB-303", "Server Rack", true, 3));
        store.addAsset(new Asset("LAB-404", "LAN Cable", false, 1)); 

        CheckoutService service = new CheckoutService(students, store);

        
        CheckoutRequest r1 = new CheckoutRequest("KRG20281", "LAB-101", 4); 
        CheckoutRequest r2 = new CheckoutRequest("KRG20281", "LAB-XYZ", 2); 
        CheckoutRequest r3 = new CheckoutRequest("ABC12345", "LAB-202", 2); 

        List<CheckoutRequest> requests = Arrays.asList(r1, r2, r3);

        for (CheckoutRequest req : requests) {

            try {
                String receipt = service.checkout(req);
                System.out.println("SUCCESS: " + receipt);

            } catch (IllegalArgumentException e) {
                AuditLogger.logError(e);

            } catch (NullPointerException e) {
                AuditLogger.logError(e);

            } catch (SecurityException e) {
                AuditLogger.logError(e);

            } catch (IllegalStateException e) {
                AuditLogger.logError(e);

            } finally {
                AuditLogger.log("Attempt finished for UID=" + req.uid +
                        ", Asset=" + req.assetId);
                System.out.println("-----------------------------------");
            }
        }
    }
}
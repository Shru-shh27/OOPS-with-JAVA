import java.util.*;

// Common interface
interface ProctoringStep {
    void execute();
}

// One identity check
class IdentityCheck implements ProctoringStep {
    public void execute() {
        System.out.println("Identity verification done");
    }
}

// One environment check
class EnvironmentCheck implements ProctoringStep {
    public void execute() {
        System.out.println("Environment check done");
    }
}

// One behaviour monitoring
class BehaviourMonitoring implements ProctoringStep {
    public void execute() {
        System.out.println("Behaviour monitoring done");
    }
}

// Controller
class ProctoringController {
    List<ProctoringStep> steps = new ArrayList<>();

    void addStep(ProctoringStep step) {
        steps.add(step);
    }

    void startExam() {
        for (ProctoringStep step : steps) {
            step.execute();
        }
    }
}

// Main
public class Main {
    public static void main(String[] args) {

        ProctoringController exam = new ProctoringController();

        exam.addStep(new IdentityCheck());
        exam.addStep(new EnvironmentCheck());
        exam.addStep(new BehaviourMonitoring());

        exam.startExam();
    }
}

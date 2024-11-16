package com.gophers.structures.grades;

import java.util.List;
import java.util.Map;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import com.gophers.interfaces.Grade;

/**
 * An abstract class that implements the {@link Grade} interface and provides a
 * template for grading assignments or tests based on results. This class processes
 * the outcome of tests, allocates marks based on test weightings, adjusts for failures,
 * and provides feedback on each test method.
 */
public abstract class GradeTemplate implements Grade {
    
    /**
     * A map that stores the marks assigned to each test method.
     * The key is the test method name, and the value is the marks for that test.
     */
    protected Map<String, Integer> testMarks;
    
    /**
     * A map that stores feedback for each test method.
     * The key is the test method name, and the value is the feedback score for that test.
     */
    protected Map<String, Integer> feedbackMap;
    
    /**
     * The total marks available for the assignment or test.
     */
    private int totalMarks;
    
    /**
     * The marks earned by the student based on the test results.
     */
    private int marksEarned;

    /**
     * Constructs a new GradeTemplate instance with the given test result and total available marks.
     * This constructor initializes the weightings and feedback maps, and calculates the marks earned
     * based on the result of the test.
     *
     * @param result The result of running the test, including any failures.
     * @param totalMarks The total marks possible for the test or assignment.
     */
    public GradeTemplate(Result result, int totalMarks) {
        this.totalMarks = totalMarks;
        this.allocateWeightings();
        this.allocateFeedback();
        this.setMarksEarned(result);
    }

    /**
     * Sets the marks earned by the student based on the test result.
     * If any failures are found in the result, the marks are adjusted by deducting the marks
     * associated with the failed tests.
     *
     * @param result The result of the test run, containing information about successes and failures.
     */
    private void setMarksEarned(Result result) {
        this.marksEarned = this.totalMarks;
        if (!result.wasSuccessful()) {
            adjustMarksForFailures(result.getFailures());
        }
    }

    /**
     * Adjusts the marks earned by the student based on the failures in the test result.
     * Each failure reduces the marks by the amount associated with the failed test method.
     * Feedback for each failed test method is set to zero.
     *
     * @param failures A list of failures from the test result, each containing a test method name.
     */
    private void adjustMarksForFailures(List<Failure> failures) {
        for (Failure failure : failures) {
            String methodName = failure.getDescription().getMethodName();
            this.marksEarned -= testMarks.getOrDefault(methodName, 0);
            feedbackMap.put(methodName, 0);
        }
    }

    /**
     * Returns the marks earned by the student for the test or assignment.
     * This is the total marks available minus any deductions for failed tests.
     *
     * @return The marks earned by the student.
     */
    @Override
    public int getMarks() {
        return this.marksEarned;
    }

    /**
     * Returns the total marks available for the assignment or test.
     *
     * @return The total marks available.
     */
    @Override
    public int getTotalMarks() {
        return this.totalMarks;
    }

    /**
     * Returns a map containing feedback for each test method.
     * The map contains test names as keys and feedback scores as values.
     *
     * @return A map of test names to feedback scores.
     */
    @Override
    public Map<String, Integer> getTestFeedback() {
        return feedbackMap;
    }

    /**
     * Allocates weightings for each test. Subclasses should implement this method
     * to define how marks are distributed across different test methods.
     */
    protected abstract void allocateWeightings();

    /**
     * Allocates feedback for each test. Subclasses should implement this method
     * to define the feedback for each test method based on the result.
     */
    protected abstract void allocateFeedback();
}

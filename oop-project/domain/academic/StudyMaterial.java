package academic;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import users.Student;
import users.Teacher;

public class StudyMaterial implements Serializable {
    public String title;
    public String description;
    public String fileName;
    public Date deadline;
    public boolean isTask;
    public Teacher uploadedBy;
    public Course course;
    public Map<Integer, String> submissions;

    public StudyMaterial(String title, String description, String fileName, Date deadline, boolean isTask, Teacher uploadedBy, Course course) {
        this.title = title;
        this.description = description;
        this.fileName = fileName;
        this.deadline = deadline;
        this.isTask = isTask;
        this.uploadedBy = uploadedBy;
        this.course = course;
        this.submissions = new HashMap<Integer, String>();
    }

    public void submitSolution(Student student, String solutionText) {
        submissions.put(student.id, solutionText);
    }

    public String getSubmission(int studentId) {
        return submissions.get(studentId);
    }

    public String toString() {
        String type = isTask ? "TASK" : "FILE";
        String deadlineText = deadline == null ? "no deadline" : new SimpleDateFormat("dd.MM.yyyy HH:mm").format(deadline);
        return "[" + type + "] " + title + " (" + fileName + "), deadline: " + deadlineText;
    }
}

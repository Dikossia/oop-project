import java.io.*;
import java.util.*;
import academic.Course;

public class DataLoader {

    public static List<Course> loadCourses(String fileName) {

        List<Course> courses = new ArrayList<Course>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String line;

            while((line = br.readLine()) != null) {

                String[] parts = line.split(",");

                if(parts.length != 3) continue;

                String name = parts[0].trim();
                String code = parts[1].trim();
                int credits = Integer.parseInt(parts[2].trim());

                Course c = new Course(name, code, credits);
                c.isOpen = true;

                courses.add(c);
            }

            br.close();

        } catch(Exception e) {
            System.out.println("Error loading file: " + fileName);
        }

        return courses;
    }
}
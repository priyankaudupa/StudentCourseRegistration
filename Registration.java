import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public String getCourseInfo() {
        return courseCode + " - " + title + "\n" +
                "Description: " + description + "\n" +
                "Capacity: " + capacity + "\n" +
                "Schedule: " + schedule;
    }
}

class Student {
    int studentID;
    String name;
    List<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (registeredCourses.size() < 3) {
            registeredCourses.add(course);
            System.out.println("Course registration successful.");
        } else {
            System.out.println("You have already registered for the maximum number of courses.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            System.out.println("Course dropped successfully.");
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    public void displayRegisteredCourses() {
        System.out.println("Registered Courses for " + name + " (ID: " + studentID + "):");
        for (Course course : registeredCourses) {
            System.out.println(course.getCourseInfo());
            System.out.println("--------------------");
        }
    }
}

class CourseDatabase {
    List<Course> courses;

    public CourseDatabase() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course.getCourseInfo());
            System.out.println("--------------------");
        }
    }
}

public class Registration {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CourseDatabase courseDatabase = new CourseDatabase();
        courseDatabase.addCourse(new Course("CSC101", "Introduction to Programming", "Basic programming concepts", 30,
                "Mon/Wed 9:00 AM"));
        courseDatabase.addCourse(
                new Course("MAT201", "Mathematics", "Limits, Theorems, and Ratios", 25, "Tue/Thu 2:00 PM"));
        courseDatabase
                .addCourse(new Course("PHY301", "Physics", "Academic writing skills", 20, "Mon/Fri 1:00 PM"));

        Student student = new Student(19421, "Priyanka");

        while (true) {
            System.out.println("\nCourse Registration System Menu:");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    courseDatabase.displayCourses();
                    break;

                case 2:
                    System.out.print("Enter the course code to register: ");
                    String registerCode = scanner.next();
                    Course registerCourse = findCourseByCode(courseDatabase, registerCode);
                    if (registerCourse != null) {
                        student.registerCourse(registerCourse);
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter the course code to drop: ");
                    String dropCode = scanner.next();
                    Course dropCourse = findCourseByCode(courseDatabase, dropCode);
                    if (dropCourse != null) {
                        student.dropCourse(dropCourse);
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;

                case 4:
                    student.displayRegisteredCourses();
                    break;

                case 5:
                    System.out.println("Thank you for using the Course Registration System. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please choose a valid option (1-5).");
            }
        }
    }

    private static Course findCourseByCode(CourseDatabase courseDatabase, String courseCode) {
        for (Course course : courseDatabase.courses) {
            if (course.courseCode.equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}

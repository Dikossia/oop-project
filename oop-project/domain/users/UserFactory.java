package users;

import enums.ManagerType;
import enums.TeacherType;
import enums.UserType;

public final class UserFactory {
    private UserFactory() {}

    public static User createUser(UserType type, int id, String username, String password, String email) {
        if(type == UserType.Admin) {
            Admin admin = new Admin();
            admin.id = id;
            admin.username = username;
            admin.password = password;
            admin.email = email;
            return admin;
        }
        if(type == UserType.Student) {
            return new Student(id, username, password, email, 0.0, 1, 0, 0);
        }
        if(type == UserType.Teacher) {
            Teacher teacher = new Teacher();
            teacher.id = id;
            teacher.username = username;
            teacher.password = password;
            teacher.email = email;
            teacher.title = TeacherType.TUTOR;
            return teacher;
        }
        Manager manager = new Manager();
        manager.id = id;
        manager.username = username;
        manager.password = password;
        manager.email = email;
        manager.type = ManagerType.OR_MANAGER;
        return manager;
    }
}

package piss.entities;

public enum UserRole {
    Student("Student"),
    Teacher("Teacher"),
    Admin("Admin");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}

package model;

public class User {
    public int id;
    public String name;
    public String email;
    public String password;

    private boolean authenticated;

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.authenticated = false;
    }

    public boolean login(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            this.authenticated = true;
            return true;
        }
        return false;
    }

    public boolean logout() {
        this.authenticated = false;
        return true;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}

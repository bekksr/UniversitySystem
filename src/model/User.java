package model;

import java.util.Objects;
import java.util.UUID;

public abstract class User {
     private final String id;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private boolean authenticated;
    
 
    protected User(String firstName, String lastName, String email, String password) {
        this.id           = UUID.randomUUID().toString();
        this.firstName    = firstName;
        this.lastName     = lastName;
        this.email        = email;
        this.passwordHash = hash(password);
        this.authenticated = false;
    }
 
    // ---- Auth ----
 
    public boolean login(String email, String password) {
        if (this.email.equals(email) && this.passwordHash.equals(hash(password))) {
            authenticated = true;
            System.out.println(getFullName() + " logged in.");
            return true;
        }
        System.out.println("Login failed for: " + email);
        return false;
    }
 
    public void logout() {
        authenticated = false;
        System.out.println(getFullName() + " logged out.");
    }
 
    public boolean isAuthenticated() { return authenticated; }
 
    private String hash(String pw) { return String.valueOf(Objects.hash(pw)); }
 
    // ---- Getters / Setters ----
 
    public String getId()        { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName()  { return lastName; }
    public String getEmail()     { return email; }
    public String getFullName()  { return firstName + " " + lastName; }
 
    public void setFirstName(String v) { this.firstName = v; }
    public void setLastName(String v)  { this.lastName  = v; }
    public void setEmail(String v)     { this.email     = v; }
    public void setPassword(String pw) { this.passwordHash = hash(pw); }
 
    @Override
    public String toString() {
        return "[" + getClass().getSimpleName() + "] " + getFullName() + " <" + email + ">";
    }
}

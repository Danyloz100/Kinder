package lms.model;

import java.util.Objects;

public class User {
    private long id;
    private String Email;
    private String Password;
    private String Name;
    private String Regestration;
    private String LastEntered;

    public User() {
    }

    public User(long id, String email, String password, String name, String regestration, String lastEntered) {
        this.id = id;
        Email = email;
        Password = password;
        Name = name;
        Regestration = regestration;
        LastEntered = lastEntered;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRegestration() {
        return Regestration;
    }

    public void setRegestration(String regestration) {
        Regestration = regestration;
    }

    public String getLastEntered() {
        return LastEntered;
    }

    public void setLastEntered(String lastEntered) {
        LastEntered = lastEntered;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", Name='" + Name + '\'' +
                ", Regestration='" + Regestration + '\'' +
                ", LastEntered='" + LastEntered + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(Email, user.Email) &&
                Objects.equals(Password, user.Password) &&
                Objects.equals(Name, user.Name) &&
                Objects.equals(Regestration, user.Regestration) &&
                Objects.equals(LastEntered, user.LastEntered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Email, Password, Name, Regestration, LastEntered);
    }
}

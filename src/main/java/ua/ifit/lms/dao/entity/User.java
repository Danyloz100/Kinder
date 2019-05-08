package ua.ifit.lms.dao.entity;

import java.util.Objects;

public class User {
    private long id;
    private String email;
    private String password;
    private String name;
    private String date_created;
    private String date_last_entered;

    public User() {
    }

    public User(long id, String email, String password, String name, String date_created, String date_last_entered) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.date_created = date_created;
        this.date_last_entered = date_last_entered;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_last_entered() {
        return date_last_entered;
    }

    public void setDate_last_entered(String date_last_entered) {
        this.date_last_entered = date_last_entered;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", date_created='" + date_created + '\'' +
                ", date_last_entered='" + date_last_entered + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(date_created, user.date_created) &&
                Objects.equals(date_last_entered, user.date_last_entered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, date_created, date_last_entered);
    }
}

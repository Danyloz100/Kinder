package ua.ifit.lms.dao.entity;

import java.util.Objects;

public class Note {
    private long id;
    private long user_id;
    private String text;
    private String title;
    private String date_created;
    private String date_last_edited;

    public Note() {
    }

    public Note(long id, long user_id, String text, String title, String date_created, String date_last_edited) {
        this.id = id;
        this.user_id = user_id;
        this.text = text;
        this.title = title;
        this.date_created = date_created;
        this.date_last_edited = date_last_edited;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_last_edited() {
        return date_last_edited;
    }

    public void setDate_last_edited(String date_last_edited) {
        this.date_last_edited = date_last_edited;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", text='" + text + '\'' +
                ", title='" + title + '\'' +
                ", date_created='" + date_created + '\'' +
                ", date_last_edited='" + date_last_edited + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id &&
                user_id == note.user_id &&
                Objects.equals(text, note.text) &&
                Objects.equals(title, note.title) &&
                Objects.equals(date_created, note.date_created) &&
                Objects.equals(date_last_edited, note.date_last_edited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, text, title, date_created, date_last_edited);
    }
}

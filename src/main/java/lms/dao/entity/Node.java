package lms.dao.entity;

import java.util.Objects;

public class Node {
    private  long ID;
    private long user_id;
    private  String text;
    private String title;
    private String date_created;
    private String date_entred;

    public Node() {
    }
    public Node(long ID, long user_id, String text, String title, String date_created, String date_entred) {
        this.ID = ID;
        this.user_id = user_id;
        this.text = text;
        this.title = title;
        this.date_created = date_created;
        this.date_entred = date_entred;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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

    public String getDate_entred() {
        return date_entred;
    }

    public void setDate_entred(String date_entred) {
        this.date_entred = date_entred;
    }

    @Override
    public String toString() {
        return "Node{" +
                "ID=" + ID +
                ", user_id=" + user_id +
                ", text='" + text + '\'' +
                ", title='" + title + '\'' +
                ", date_created='" + date_created + '\'' +
                ", date_entred='" + date_entred + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return ID == node.ID &&
                user_id == node.user_id &&
                Objects.equals(text, node.text) &&
                Objects.equals(title, node.title) &&
                Objects.equals(date_created, node.date_created) &&
                Objects.equals(date_entred, node.date_entred);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, user_id, text, title, date_created, date_entred);
    }
}

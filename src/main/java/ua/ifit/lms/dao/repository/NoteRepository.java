package ua.ifit.lms.dao.repository;

import ua.ifit.lms.dao.entity.Note;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NoteRepository {

    public List<Note> getNotesByUserid(long userid) {
        DataSource dataSource = new DataSource();
        List<Note> notes = new ArrayList<>();

        String query = "SELECT id, user_id, text, title, date_created, date_last_edited " +
                "FROM note " +
                "WHERE note.user_id=" + userid;

        try (
                // get connection with our database
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        )
        {
            while (resultSet.next())  {
                Note note =  new Note(
                        resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        resultSet.getString("text"),
                        resultSet.getString("title"),
                        resultSet.getString("date_created"),
                        resultSet.getString("date_last_edited")
                );
                notes.add(note);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }
}

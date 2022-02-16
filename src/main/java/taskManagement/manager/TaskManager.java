package taskManagement.manager;

import taskManagement.db.DBConnectionProvider;
import taskManagement.model.Task;
import taskManagement.model.TaskStatus;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class TaskManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private UserManager userManager = new UserManager();

    public void create (Task task) {
        String sql = "INSERT INTO task(name, description, user_id, status, deadline) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, task.getName());
            if (task.getDeadline() != null) {
                statement.setString(2, sdf.format(task.getDeadline()));
            } else {
                statement.setString(2, null);
            }
            statement.setString(3, task.getStatus().name());
            statement.setLong(4, task.getUser().getId());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                task.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tasks.add(getTaskFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private Task getTaskFromResultSet(ResultSet resultSet) {
        try {
            return Task.builder()
                    .id(resultSet.getLong(1))
                    .name(resultSet.getString(2))
                    .description(resultSet.getString(3))
                    .user(userManager.getById(resultSet.getLong(4)))
                    .status(TaskStatus.valueOf(resultSet.getString(5)))
                    .deadline(sdf.parse(resultSet.getString(6)))
                    .build();
        } catch (SQLException e) {
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Task getTaskById (long id) {
        String sql = "SELECT * FROM todo WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return getTaskFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateStatus (Task task) {
        String sql = "UPDATE task SET status = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            System.out.println("Task was updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

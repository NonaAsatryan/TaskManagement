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
    private UserManager userManager = new UserManager();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public void addTask(Task task) {
        String sql = "INSERT INTO task(name, description, status, deadline, user_id) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setString(3, task.getStatus().name());
            statement.setString(4, sdf.format(task.getDeadline()));
            statement.setInt(5, task.getUserId());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                task.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks() {
        String sql = "SELECT * FROM task";
        List<Task> tasks = new ArrayList<>();
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

    public List<Task> getAllTasksByUserId(int userId) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM task Where user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tasks.add(getTaskFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public Task getTaskById (long id) {
        String sql = "SELECT * FROM task WHERE id = " + id;
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

    public void updateStatus (int taskId, String newStatus) {
        String sql = "UPDATE task SET status = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.setString(2, newStatus);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete (int id) {
        String sql = "DELETE FROM task WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Task getTaskFromResultSet(ResultSet resultSet) {
        try {
            //table-=y 6 hat syun uni, du 7 hat es uzum vercnel ed e xndiry> bajc usery normal kexni user id-
            return Task.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .description(resultSet.getString(3))
                    .status(TaskStatus.valueOf(resultSet.getString(4)))
                    .deadline(sdf.parse(resultSet.getString(5)))
                    .user(userManager.getUserById(resultSet.getInt(6)))
                    .build();
        } catch (SQLException e) {
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}

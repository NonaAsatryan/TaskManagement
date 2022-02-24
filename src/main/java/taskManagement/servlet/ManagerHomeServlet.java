package taskManagement.servlet;

import taskManagement.manager.TaskManager;
import taskManagement.manager.UserManager;
import taskManagement.model.Task;
import taskManagement.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/managerHome")
public class ManagerHomeServlet extends HttpServlet {

    private TaskManager taskManager = new TaskManager();
    private UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //stex er xndiry Li manageri mej e erevi jspi
        List<Task> allTasks = taskManager.getAllTasks();
        List<User> allUsers = userManager.getAllUsers();
        for (Task task : allTasks) {
            if (task.getDeadline().after(new Date())) {
                task.setExpired(true);
            }
        }
        req.setAttribute("tasks", allTasks);
        req.setAttribute("users", allUsers);
        req.getRequestDispatcher("/WEB-INF/managerHome.jsp").forward(req, resp);
    }
}

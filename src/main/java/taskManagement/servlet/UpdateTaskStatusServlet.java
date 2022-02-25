package taskManagement.servlet;

import taskManagement.manager.TaskManager;
import taskManagement.model.User;
import taskManagement.model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/updateTask")
public class UpdateTaskStatusServlet extends HttpServlet {

    private TaskManager taskManager = new TaskManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int taskId = Integer.parseInt(req.getParameter("taskId"));
        String status = req.getParameter("status");
        taskManager.updateStatus(taskId, status);
        if (user.getType() == UserType.MANAGER) {
            resp.sendRedirect("/managerHome");
        } else {
            resp.sendRedirect("/userHome");
        }
    }
}

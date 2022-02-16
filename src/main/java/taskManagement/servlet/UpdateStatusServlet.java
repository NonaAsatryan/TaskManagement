package taskManagement.servlet;

import taskManagement.manager.TaskManager;
import taskManagement.model.Task;
import taskManagement.model.TaskStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/updateStatus")
public class UpdateStatusServlet extends HttpServlet {

    private TaskManager taskManager = new TaskManager();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);

    int id = Integer.parseInt(req.getParameter("id"));
        Task task = taskManager.getTaskById(id);

        req.setAttribute("task", task);

        req.getRequestDispatcher("/WEB-INF/updateTask.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        int userId = Integer.parseInt(req.getParameter("userId"));
        TaskStatus taskStatus = TaskStatus.valueOf(req.getParameter("taskStatus"));

        Task task = new Task();
        task.setId(id);
        task.setName(name);
        task.setDescription(description);
        task.setId(userId);
        task.setStatus(taskStatus);

        try {
            Date deadline = sdf.parse((req.getParameter("taskStatus")));
            task.setDeadline(deadline);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        taskManager.updateStatus(task);

        resp.sendRedirect("/WEB-INF/tasks");
    }
}

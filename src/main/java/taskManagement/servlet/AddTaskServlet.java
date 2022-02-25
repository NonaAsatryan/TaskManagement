package taskManagement.servlet;

import taskManagement.manager.TaskManager;
import taskManagement.manager.UserManager;
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

@WebServlet(urlPatterns = "/addTask")
public class AddTaskServlet extends HttpServlet {

    private UserManager userManager = new UserManager();
    private TaskManager taskManager = new TaskManager();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String status = req.getParameter("status");
        String deadline = req.getParameter("deadline");
        int userId = Integer.parseInt(req.getParameter("user_id"));

        try {
            taskManager.addTask(Task.builder()
                            .name(name)
                            .description(description)
                            .status(TaskStatus.valueOf(status))
                            .deadline(sdf.parse(deadline))
                            .userId(userId)
                    .build());
            //es debug em anum vor tesnem du run es sexmum)) misht sovorutyun
            resp.sendRedirect("/managerHome");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

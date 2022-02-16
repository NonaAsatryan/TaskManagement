package taskManagement.servlet;

import taskManagement.manager.TaskManager;
import taskManagement.manager.UserManager;
import taskManagement.model.Task;
import taskManagement.model.TaskStatus;
import taskManagement.model.User;
import taskManagement.model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/manager")
public class ManagerHomeServlet extends HttpServlet {

    private UserManager userManager = new UserManager();
    private TaskManager taskManager = new TaskManager();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserType userType = UserType.valueOf(req.getParameter("userType"));

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setType(userType);


        String name1 = req.getParameter("name");
        String description = req.getParameter("description");
        int userId = Integer.parseInt(req.getParameter("userId"));
        TaskStatus taskStatus = TaskStatus.valueOf(req.getParameter("taskStatus"));

        Task task = new Task();
        task.setName(name1);
        task.setDescription(description);
        task.setId(userId);
        task.setStatus(taskStatus);

        try {
            Date deadline = sdf.parse((req.getParameter("deadline")));
            task.setDeadline(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        userManager.add(user);
        taskManager.create(task);

        resp.sendRedirect("/WEB-INF/manager.jsp");
    }
}

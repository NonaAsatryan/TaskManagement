package taskManagement.servlet;


import taskManagement.manager.UserManager;
import taskManagement.model.User;
import taskManagement.model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserType userType = UserType.valueOf(req.getParameter("userType"));
        User user = new User();
        user.setType(userType);

        req.setAttribute("userType", userType);

        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }
}

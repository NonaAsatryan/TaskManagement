package taskManagement.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(urlPatterns = "/image")
public class ImageDownloadServlet extends HttpServlet {

    private final String UPLOAD_DIR = "/Users/nona.asatryan/IdeaProjects/maven/TaskManagement/upload";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        if (path == null || path.length() == 0) {
            resp.sendRedirect("/");
        } else {
            File file = new File(UPLOAD_DIR + File.separator + path);
            if (!file.exists()) {
                resp.sendRedirect("/");
            }
            resp.setContentType("image/jpeg");
            resp.setHeader("Content-disposition", "attachment; filename" + path);

            try (InputStream in = new FileInputStream(file);
                 OutputStream out = resp.getOutputStream()) {
                byte[] buffer = new byte[1048];
                int numBytesRead;
                while ((numBytesRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, numBytesRead);
                }
            }
        }
    }
}

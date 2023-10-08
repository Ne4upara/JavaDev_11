package sergey.goit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {
    private TimeZone timeZone;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html;charset=UTF-8");
        String timezoneParam = req.getParameter("timezone");

        if (timezoneParam.isEmpty()) {
            timeZone = TimeZone.getTimeZone("GMT");
        } else {
            int tz = Integer.parseInt(timezoneParam);
            if (-1 < tz) {
                timeZone = TimeZone.getTimeZone("GMT+" + timezoneParam);
            } else {
                timeZone = TimeZone.getTimeZone("GMT" + timezoneParam);
            }
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setTimeZone(timeZone);
        Date currentTime = new Date();
        String formattedTime = dateFormat.format(currentTime);

        req.setAttribute("formattedTime", formattedTime);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/time.jsp");
        dispatcher.forward(req, resp);
    }
}
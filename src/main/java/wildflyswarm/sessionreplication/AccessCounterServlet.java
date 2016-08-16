package wildflyswarm.sessionreplication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet("/")
public class AccessCounterServlet extends HttpServlet {

  private static final Logger LOGGER = Logger.getLogger(AccessCounterServlet.class.getName());
  private static final String COUNTER_KEY = "yourCounter";

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/plain; charset=UTF-8");
    PrintWriter out = response.getWriter();

    HttpSession session = request.getSession(true);

    Integer counter = (Integer) session.getAttribute(COUNTER_KEY);

    if (counter == null) {
      counter = 0;
    }
    counter++;
    LOGGER.info(message(session, counter));

    session.setAttribute(COUNTER_KEY, counter);

    out.println(message(session, counter));
  }

  private String message(HttpSession session, Integer counter) {
    return "Session ID: " + session.getId() + ", " + counter + " Times Access";
  }

}

package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metrics.MetricsService;

@WebServlet("/testGrabber")
public class GrabberTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Inject
    MetricsService ms;
    
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<meta http-equiv=\"refresh\" content=\"5\">");
		
		out.println("<h3>Grabber Tester Servlet</h3>");
		
		out.println("Function value: " + ms.getFunctionValue());
		out.println("<br/>Pear Inc value: " + ms.getPearValue());
		out.println("<br/>Major Current value: " + ms.getMajorValue());
		out.println("<br/>Macrohard value: " + ms.getMacroValue());
		out.println("<br/>Time Up value: " + ms.getUptimeValue());
		out.println("<br/>Rotor value: " + ms.getRotorValue());
				
		out.println("");
		
    }
    
}

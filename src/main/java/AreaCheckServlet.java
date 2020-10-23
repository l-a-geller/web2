import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class AreaCheckServlet extends HttpServlet {

    private static LinkedList<Point> points = new LinkedList<>();

    public static void clearPoints(){
        points.clear();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            long startTime = System.currentTimeMillis();
            float y = Float.parseFloat(request.getParameter("y"));
            float r = Float.parseFloat(request.getParameter("r"));

            String xNum = "x0"; float nextX;
            for (int i=0; i<9; i++){
                try{
                    xNum = "x" + i;
                    nextX = Float.parseFloat(request.getParameter(xNum));

                    Point point = new Point(nextX, y, r);
                    point.setRes(hitsFigure(nextX, y, r));
                    point.setExecutionTime(System.currentTimeMillis() - startTime);
                    points.add(point);

                }catch (NumberFormatException | NullPointerException e){break;}
            }

            printTable(response.getWriter());
        }catch (Exception e){
            response.getWriter().println(e.toString());
        }
    }

    private boolean hitsFigure(float x, float y, float r){
        return ((x * x + y * y) <= r/2 * r/2 && x <= 0 && y <= 0) || (y > (x - r) && x >= 0 && y <= 0) || (x<=0 && y>=0 && x>=-r && y<=r );
    }

    static void printTable(PrintWriter out){
        for (Point point: points){
            out.println("<tr>");
            out.println("<td>" + point.getX() + "</td>");
            out.println("<td>" + point.getY() + "</td>");
            out.println("<td>" + point.getR() + "</td>");
            out.println("<td>" + point.getRes() + "</td>");
            out.println("<td>" + point.getRequestTime() + "</td>");
            out.println("<td>" + point.getExecutionTime() + "</td>");
            out.println("</tr>");
        }
    }
}

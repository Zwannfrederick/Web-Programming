import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import com.google.gson.Gson;
import java.util.HashMap;

@WebServlet("/ClearCartServlet")
public class ClearCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("cart");

        // JSON nesnesi olarak dön
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        HashMap<String, String> message = new HashMap<>();
        message.put("message", "Sepetiniz boş");

        Gson gson = new Gson();
        out.print(gson.toJson(message));
        out.flush();
    }
}

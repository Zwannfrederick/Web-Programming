import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        if (cart == null || cart.isEmpty()) {
            HashMap<String, String> message = new HashMap<>();
            message.put("message", "Sepetiniz boş");
            out.print(gson.toJson(message));
        } else {
            out.print(gson.toJson(cart));
        }
        out.flush();
    }
}

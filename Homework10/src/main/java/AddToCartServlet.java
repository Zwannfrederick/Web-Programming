import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
        }

        String product = request.getParameter("product");
        cart.put(product, cart.getOrDefault(product, 0) + 1);
        session.setAttribute("cart", cart);

        // JSON formatında döndürme
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        out.println(gson.toJson(cart)); // Sepet JSON formatında gönderiliyor
    }
}

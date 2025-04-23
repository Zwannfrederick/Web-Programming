import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String product = request.getParameter("product");
        String action = request.getParameter("action"); // Action: increase or decrease
        Cookie[] cookies = request.getCookies();

        // Sepete ürün ekleme veya artırma/azaltma işlemi
        if (product != null) {
            boolean exists = false;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(product)) {
                        int count = Integer.parseInt(cookie.getValue());

                        // Artırma ve Azaltma
                        if ("increase".equals(action)) {
                            count++;
                        } else if ("decrease".equals(action) && count > 1) {
                            count--;
                        }

                        cookie.setValue(String.valueOf(count));
                        response.addCookie(cookie);
                        exists = true;
                        break;
                    }
                }
            }
            if (!exists) {
                Cookie newCookie = new Cookie(product, "1");
                newCookie.setMaxAge(3600); // 1 saatlik geçerlilik süresi
                response.addCookie(newCookie);
            }
        }

        // Sepeti temizleme işlemi
        if ("true".equals(request.getParameter("clear"))) {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (!cookie.getName().equals("JSESSIONID")) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
            response.sendRedirect("cart.jsp");
            return;
        }

        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}

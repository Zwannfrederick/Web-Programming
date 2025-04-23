import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ürünlerin listesi (Örnek)
        List<String> products = List.of("Kitap", "Telefon", "Kalem", "Defter");

        // Ürünleri request'e ekle
        request.setAttribute("products", products);

        // products.jsp'ye yönlendir
        request.getRequestDispatcher("products.jsp").forward(request, response);
    }
}

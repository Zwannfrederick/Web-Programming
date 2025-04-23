import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displayInfo")
public class DisplayInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // GET isteği ile gelen verileri al
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String city = request.getParameter("city");
        String email = request.getParameter("email");
        String password = request.getParameter("password"); // Şifreyi al

        // Yanıtı HTML olarak göster
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(
            "<html><head><title>Kayıt Bilgileri</title></head><body>" +
            "<h2>Kayıt Başarılı</h2>" +
            "<p><strong>Ad:</strong> " + firstName + "</p>" +
            "<p><strong>Soyad:</strong> " + lastName + "</p>" +
            "<p><strong>Şehir:</strong> " + city + "</p>" +
            "<p><strong>E-posta:</strong> " + email + "</p>" +
            "<p><strong>Şifre:</strong> " + password + "</p>" + // Şifreyi göster
            "</body></html>"
        );
    }
}

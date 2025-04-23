import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Form verilerini al
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String city = request.getParameter("city");
        String email = request.getParameter("email");
        String password = request.getParameter("password");  // Şifreyi al

        // GET isteği ile yönlendirme yap (Şifreyi URL içinde gönderiyoruz)
        response.sendRedirect("displayInfo?firstName=" + firstName + "&lastName=" + lastName + "&city=" + city + "&email=" + email + "&password=" + password);
    }
}


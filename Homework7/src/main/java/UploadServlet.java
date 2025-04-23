import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10, // 10MB
                 maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Dosya Yükleme Sonucu</title></head><body>");

        out.println("<h2>🔹 HTTP İstek Başlıkları</h2>");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            out.println("<p><b>" + headerName + ":</b> " + request.getHeader(headerName) + "</p>");
        }

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        out.println("<h2>📝 Form Verileri</h2>");
        out.println("<p><b>Ad Soyad:</b> " + name + "</p>");
        out.println("<p><b>E-posta:</b> " + email + "</p>");

        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Windows uyumluluğu için

        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String filePath = uploadPath + File.separator + fileName;
        try (InputStream fileContent = filePart.getInputStream();
             FileOutputStream fos = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }

        out.println("<h2>📂 Dosya Yükleme Bilgileri</h2>");
        out.println("<p><b>Yüklenen Dosya:</b> " + fileName + "</p>");
        out.println("<p><b>Kaydedildiği Yer:</b> " + filePath + "</p>");

        String fileUrl = request.getContextPath() + "/" + UPLOAD_DIR + "/" + fileName;

        if (fileName.matches(".*\\.(jpg|jpeg|png|gif|webp)$")) { 
            out.println("<h2>🖼️ Önizleme</h2>");
            out.println("<img src='" + fileUrl + "' alt='Yüklenen Görsel' style='max-width:300px; max-height:300px; border: 1px solid #ddd; padding: 5px;'>");
        } 
        else if (fileName.endsWith(".pdf")) { 
            out.println("<h2>📄 PDF Önizleme</h2>");
            out.println("<p><a href='" + fileUrl + "' target='_blank'>📄 PDF'yi Aç</a></p>");
            out.println("<embed src='" + fileUrl + "' type='application/pdf' width='500px' height='600px' />");
        } 
        else { 
            out.println("<p><a href='" + fileUrl + "' download>📂 Dosyayı İndir</a></p>");
        }

        out.println("</body></html>");
    }
}

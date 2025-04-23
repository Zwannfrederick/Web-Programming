<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Kullanıcı Bilgisi</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin: 50px;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            margin: auto;
        }
        input, button {
            margin: 10px 0;
            padding: 10px;
            width: 90%;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: #28a745;
            color: white;
            font-weight: bold;
            cursor: pointer;
        }
        button:hover {
            background-color: #218838;
        }
        h3 {
            color: #333;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Kullanıcı Bilgi Formu</h2>
        <form method="POST" action="kullaniciBilgisi.jsp">
            Ad: <input type="text" name="ad" required><br>
            Soyad: <input type="text" name="soyad" required><br>
            Doğum Yılı: <input type="number" name="dogumYili" required><br>
            <button type="submit">Gönder</button>
        </form>

        <%-- Kullanıcının yaşını hesaplamak için Declaration bloğu --%>
        <%! 
            public int yasHesapla(int dogumYili) {
                int mevcutYil = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
                return mevcutYil - dogumYili;
            }
        %>

        <%-- Scriptlet bloğu ile form verilerini al --%>
        <%
            String ad = request.getParameter("ad");
            String soyad = request.getParameter("soyad");
            String dogumYiliStr = request.getParameter("dogumYili");
            int yas = 0;

            if (ad != null && soyad != null && dogumYiliStr != null && !dogumYiliStr.isEmpty()) {
                try {
                    int dogumYili = Integer.parseInt(dogumYiliStr);
                    yas = yasHesapla(dogumYili);
                } catch (NumberFormatException e) {
                    yas = -1; // Geçersiz giriş durumunu kontrol etmek için
                }
            }
        %>

        <%-- Expression bloğu ile sonucu ekrana yazdır --%>
        <% if (ad != null && soyad != null && yas > 0) { %>
            <h3><%= "Hoş geldin " + ad + " " + soyad + ", yaşınız: " + yas %></h3>
        <% } else if (yas == -1) { %>
            <h3 style="color: red;">Geçerli bir doğum yılı giriniz!</h3>
        <% } %>
    </div>

</body>
</html>

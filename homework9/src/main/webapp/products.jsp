<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ürünler</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
            text-align: center;
        }
        
        .container {
            width: 50%;
            margin: auto;
            background: white;
            padding: 20px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        .cart-indicator {
            position: absolute;
            top: 20px;
            right: 20px;
            background: #007bff;
            color: white;
            padding: 5px 10px;
            border-radius: 50%;
            font-weight: bold;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            background: #007bff;
            margin: 10px 0;
            padding: 15px;
            border-radius: 5px;
            transition: all 0.3s;
        }

        li a {
            color: white;
            text-decoration: none;
            font-weight: bold;
            display: block;
        }

        li:hover {
            background: #0056b3;
            transform: scale(1.02);
        }

        .quantity-controls {
            margin-top: 10px;
        }

        .quantity-controls a {
            margin: 0 5px;
            color: white;
            font-weight: bold;
            text-decoration: none;
            padding: 5px 10px;
            border-radius: 5px;
            background: rgba(0,0,0,0.2);
        }

        .quantity-controls a:hover {
            background: rgba(0,0,0,0.3);
        }

        .button-group {
            margin-top: 20px;
            display: flex;
            justify-content: center;
            gap: 10px;
        }

        a.button {
            display: inline-block;
            background: #28a745;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
        }

        a.button.cart {
            background: #ffc107;
            color: #212529;
        }

        a.button:hover {
            opacity: 0.9;
        }
    </style>
</head>
<body>
    <%-- Sepet göstergesi --%>
    <% 
        int totalItems = 0;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (!cookie.getName().equals("JSESSIONID")) {
                    totalItems += Integer.parseInt(cookie.getValue());
                }
            }
        }
        if (totalItems > 0) {
    %>
        <div class="cart-indicator"><%= totalItems %></div>
    <% } %>

    <div class="container">
        <h2>Ürün Listesi</h2>
        <ul>
            <li>
                <a href="cart?product=Kitap&action=add">📖 Kitap</a>
                <div class="quantity-controls">
                    <a href="cart?product=Kitap&action=add">Sepete Ekle</a>
                </div>
            </li>
            <li>
                <a href="cart?product=Telefon&action=add">📱 Telefon</a>
                <div class="quantity-controls">
                    <a href="cart?product=Telefon&action=add">Sepete Ekle</a>
                </div>
            </li>
            <li>
                <a href="cart?product=Kalem&action=add">✏️ Kalem</a>
                <div class="quantity-controls">
                    <a href="cart?product=Kalem&action=add">Sepete Ekle</a>
                </div>
            </li>
            <li>
                <a href="cart?product=Defter&action=add">📒 Defter</a>
                <div class="quantity-controls">
                    <a href="cart?product=Defter&action=add">Sepete Ekle</a>
                </div>
            </li>
        </ul>
        <div class="button-group">
            <a class="button cart" href="cart.jsp">🛒 Sepeti Gör (<%= totalItems %>)</a>
        </div>
    </div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sepet</title>
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
        }

        .cart-items {
            list-style-type: none;
            padding: 0;
        }

        .cart-item {
            background: #007bff;
            margin: 10px 0;
            padding: 10px;
            border-radius: 5px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .cart-item a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }

        .cart-item .quantity-controls {
            display: inline-flex;
            align-items: center;
        }

        .quantity-controls button {
            background: #007bff;
            color: white;
            border: none;
            padding: 5px 10px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
        }

        .quantity-controls button:hover {
            background: #0056b3;
        }

        .cart-item .quantity {
            margin: 0 10px;
            font-weight: bold;
            color: white;
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

        a.button.clear {
            background: #dc3545;
        }

        a.button.home {
            background: #6c757d;
        }

        a.button:hover {
            opacity: 0.9;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Sepetiniz</h2>
        <ul class="cart-items">
            <% 
                Cookie[] cookies = request.getCookies();
                boolean hasItems = false;
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (!cookie.getName().equals("JSESSIONID")) {
                            String product = cookie.getName();
                            String count = cookie.getValue();
                            hasItems = true;
            %>
                <li class="cart-item">
                    <span><%= product %> - <%= count %> adet</span>
                    <div class="quantity-controls">
                        <a href="cart?product=<%= product %>&action=increase">➕</a>
                        <a href="cart?product=<%= product %>&action=decrease">➖</a>
                    </div>
                </li>
            <% 
                        }
                    }
                }
                if (!hasItems) {
            %>
                <li>Sepetiniz boş.</li>
            <% 
                }
            %>
        </ul>
        <div class="button-group">
            <a class="button home" href="products.jsp">🏠 Ana Sayfa</a>
            <a class="button clear" href="cart?clear=true">🗑️ Sepeti Temizle</a>
        </div>
    </div>
</body>
</html>
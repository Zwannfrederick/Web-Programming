<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dosya Yükleme Formu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #2c3e50, #34495e);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background: #ecf0f1;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            width: 350px;
            text-align: center;
        }
        h2 {
            color: #2c3e50;
        }
        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
            color: #34495e;
        }
        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #bdc3c7;
            border-radius: 4px;
            font-size: 14px;
        }
        input:focus {
            border-color: #3498db;
            outline: none;
            box-shadow: 0 0 5px rgba(52, 152, 219, 0.5);
        }
        button {
            background-color: #2980b9;
            color: white;
            border: none;
            padding: 10px;
            width: 100%;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background 0.3s ease;
        }
        button:hover {
            background-color: #1f618d;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Dosya Yükleme Formu</h2>
        <form action="upload" method="post" enctype="multipart/form-data">
            <label>Ad Soyad:</label>
            <input type="text" name="name" required>
            
            <label>E-posta:</label>
            <input type="email" name="email" required>
            
            <label>CV Yükle:</label>
            <input type="file" name="file" required>
            
            <button type="submit">Gönder</button>
        </form>
    </div>
</body>
</html>

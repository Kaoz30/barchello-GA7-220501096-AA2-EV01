<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Barchello Bávaro Pub</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #3E2723 0%, #2c1a16 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: 'Segoe UI', Roboto, sans-serif;
        }
        .card-login {
            background: white;
            border-radius: 20px;
            padding: 40px;
            max-width: 450px;
            width: 100%;
            box-shadow: 0 20px 35px rgba(0,0,0,0.3);
        }
        h2 {
            color: #3E2723;
            text-align: center;
            margin-bottom: 30px;
            font-weight: 700;
        }
        .form-control {
            border: none;
            border-bottom: 2px solid #8D6E63;
            border-radius: 0;
            padding: 10px 5px;
        }
        .form-control:focus {
            border-bottom-color: #3E2723;
            box-shadow: none;
        }
        .btn-login {
            background: #8D6E63;
            color: white;
            width: 100%;
            border-radius: 25px;
            padding: 12px;
            font-weight: bold;
            border: none;
        }
        .btn-login:hover {
            background: #6d4c41;
            color: white;
        }
    </style>
</head>
<body>
    <div class="card-login">
        <h2>Barchello Bávaro Pub</h2>
        
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
        
        <form action="auth" method="POST">
            <div class="mb-3">
                <label for="email" class="form-label">Correo electrónico</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Contraseña</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn-login">Ingresar</button>
        </form>
    </div>
</body>
</html>


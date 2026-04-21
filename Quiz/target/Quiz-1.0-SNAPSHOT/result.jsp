<html>
<head>
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI';
            height: 100vh;

            display: flex;
            justify-content: center;
            align-items: center;

            background: linear-gradient(135deg, #6a85f1, #a777e3);
        }

        .container {
            width: 500px;
            padding: 40px;
            background: white;
            border-radius: 20px;
            text-align: center;
        }

        img {
            width: 100px;
        }

        button {
            padding: 12px 25px;
            background: #2979ff;
            color: white;
            border: none;
            border-radius: 10px;
        }
    </style>
</head>

<body>

<div class="container">

    <img src="https://cdn-icons-png.flaticon.com/512/190/190411.png">

    <h2>Quiz Completed!</h2>
    <h3>Your Score: ${score}</h3>

    <a href="index.jsp">
        <button>Home</button>
    </a>

</div>

</body>
</html>
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

        /* MAIN BIG CARD */
        .container {
            width: 600px;
            padding: 40px;
            background: rgba(255,255,255,0.95);
            border-radius: 20px;
            text-align: center;
            box-shadow: 0 15px 40px rgba(0,0,0,0.3);
        }

        /* ICON */
        img {
            width: 100px;
            margin-bottom: 10px;
        }

        /* TEXT */
        h2 {
            font-size: 28px;
            margin: 10px 0;
        }

        p {
            color: #666;
            font-size: 16px;
        }

        /* BUTTONS */
        .btn {
            display: block;
            width: 80%;
            margin: 15px auto;
            padding: 14px;
            border-radius: 10px;
            text-decoration: none;
            color: white;
            font-weight: bold;
            font-size: 16px;
            transition: 0.3s;
        }

        .add {
            background: linear-gradient(to right, #00c853, #64dd17);
        }

        .quiz {
            background: linear-gradient(to right, #2979ff, #00b0ff);
        }

        .btn:hover {
            transform: scale(1.05);
        }
    </style>
</head>

<body>

<div class="container">

    <img src="https://cdn-icons-png.flaticon.com/512/3135/3135755.png">

    <h2>Welcome to Quiz App</h2>
    <p>Test your knowledge. Add questions or start a quiz now!</p>

    <a class="btn add" href="addQuestion.jsp">Add Question</a>
    <a class="btn quiz" href="startQuiz">Start Quiz</a>

</div>

</body>
</html>
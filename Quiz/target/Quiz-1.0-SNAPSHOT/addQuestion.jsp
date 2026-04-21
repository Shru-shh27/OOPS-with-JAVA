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
            width: 600px;
            padding: 30px;
            background: white;
            border-radius: 20px;
            text-align: center;
        }

        img {
            width: 80px;
        }

        input {
            width: 90%;
            padding: 12px;
            margin: 8px 0;
            border-radius: 8px;
            border: 1px solid #ccc;
        }

        /* BUTTONS */
        .btn {
            width: 45%;
            padding: 12px;
            margin: 10px;
            border-radius: 10px;
            border: none;
            color: white;
            font-size: 15px;
            cursor: pointer;
        }

        .add {
            background: linear-gradient(to right, #00c853, #64dd17);
        }

        .back {
            background: linear-gradient(to right, #2979ff, #00b0ff);
        }

        .btn:hover {
            transform: scale(1.05);
        }
    </style>
</head>

<body>

<div class="container">

    <img src="https://cdn-icons-png.flaticon.com/512/1828/1828817.png">

    <h2>Add Question</h2>

    <form action="addQuestion" method="post">
        <input type="text" name="question" placeholder="Question">
        <input type="text" name="opt1" placeholder="Option 1">
        <input type="text" name="opt2" placeholder="Option 2">
        <input type="text" name="opt3" placeholder="Option 3">
        <input type="text" name="opt4" placeholder="Option 4">
        <input type="text" name="answer" placeholder="Correct Answer">

        <br>
        <button class="btn add">Add Question</button>
    </form>

    <a href="index.jsp">
        <button class="btn back">Back</button>
    </a>

</div>

</body>
</html>
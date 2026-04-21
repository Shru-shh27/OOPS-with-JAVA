<%@ page import="java.util.*" %>

<html>
<head>
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI';
            background: linear-gradient(135deg, #6a85f1, #a777e3);

            display: flex;
            justify-content: center;
        }

        .container {
            width: 700px;
            margin: 40px;
            padding: 30px;
            background: white;
            border-radius: 20px;
        }

        .question {
            margin-bottom: 20px;
            padding: 15px;
            background: #f5f5f5;
            border-radius: 10px;
        }

        button {
            width: 100%;
            padding: 14px;
            background: linear-gradient(to right, #00c853, #64dd17);
            border: none;
            color: white;
            border-radius: 10px;
            font-size: 16px;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>Quiz</h2>

    <form action="submitQuiz" method="post">

        <%
            List<Map<String,String>> questions = (List<Map<String,String>>) request.getAttribute("questions");

            for(Map<String,String> q : questions){
        %>

        <div class="question">
            <p><b><%= q.get("question") %></b></p>

            <input type="radio" name="q<%=q.get("id")%>" value="<%=q.get("option1")%>"> <%=q.get("option1")%><br>
            <input type="radio" name="q<%=q.get("id")%>" value="<%=q.get("option2")%>"> <%=q.get("option2")%><br>
            <input type="radio" name="q<%=q.get("id")%>" value="<%=q.get("option3")%>"> <%=q.get("option3")%><br>
            <input type="radio" name="q<%=q.get("id")%>" value="<%=q.get("option4")%>"> <%=q.get("option4")%><br>
        </div>

        <%
            }
        %>

        <button type="submit">Submit Quiz</button>

    </form>

</div>

</body>
</html>
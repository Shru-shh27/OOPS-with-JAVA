package com.quiz;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/startQuiz")
public class QuizServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM questions");

            List<Map<String, String>> list = new ArrayList<>();

            while(rs.next()){
                Map<String, String> q = new HashMap<>();

                q.put("id", rs.getString("id"));
                q.put("question", rs.getString("question"));
                q.put("option1", rs.getString("option1"));
                q.put("option2", rs.getString("option2"));
                q.put("option3", rs.getString("option3"));
                q.put("option4", rs.getString("option4"));

                list.add(q);
            }

            request.setAttribute("questions", list);
            request.getRequestDispatcher("quiz.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
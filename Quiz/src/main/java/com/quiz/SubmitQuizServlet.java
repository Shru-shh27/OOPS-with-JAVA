package com.quiz;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/submitQuiz")
public class SubmitQuizServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int score = 0;

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM questions");

            while(rs.next()){
                int id = rs.getInt("id");
                String correct = rs.getString("correct_answer");

                String userAns = request.getParameter("q" + id);

                if(correct != null && correct.equals(userAns)){
                    score++;
                }
            }

            request.setAttribute("score", score);
            request.getRequestDispatcher("result.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
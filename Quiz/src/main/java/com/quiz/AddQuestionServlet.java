package com.quiz;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/addQuestion")
public class AddQuestionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            Connection con = DBConnection.getConnection();

            String q = request.getParameter("question");
            String o1 = request.getParameter("opt1");
            String o2 = request.getParameter("opt2");
            String o3 = request.getParameter("opt3");
            String o4 = request.getParameter("opt4");
            String ans = request.getParameter("answer");

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO questions(question, option1, option2, option3, option4, correct_answer) VALUES (?,?,?,?,?,?)"
            );

            ps.setString(1, q);
            ps.setString(2, o1);
            ps.setString(3, o2);
            ps.setString(4, o3);
            ps.setString(5, o4);
            ps.setString(6, ans);

            ps.executeUpdate();

            response.sendRedirect("addQuestion.jsp?success=1");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
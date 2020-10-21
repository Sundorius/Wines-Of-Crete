package com.cs360.winesofcrete.server;

import com.google.gson.Gson;
import com.cs360.winesofcrete.db.DebitCardDB;
import com.cs360.winesofcrete.db.UserDB;
import com.cs360.winesofcrete.model.DebitCard;
import com.cs360.winesofcrete.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */
@WebServlet(name = "ValidateData", urlPatterns ={"/ValidateData"})
public class ValidateData extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException
    {
        
        // Map to be converted to JSON.
        Map<String, String> results = new HashMap<>();

        if (request.getParameterMap().containsKey("option"))
        {
            if (request.getParameter("option").equals("signIn"))
            {
                if (request.getParameterMap().containsKey("username"))
                {
                    Pattern pattern = Pattern.compile("[A-Za-z]{8,}");
                    Matcher matcher = pattern.matcher(request.getParameter("username"));
                    if (matcher.matches())// If input matches the pattern.
                    {
                        if (!UserDB.checkValidUserName(request.getParameter("username")))// If username exists in DataBase.
                        {
                            results.put("username", "exist");// exist: username exists.
                            response.setStatus(200);
                        }
                        else
                        {
                            results.put("username", "Dexist");// Dexist: username does not exist.
                            response.setStatus(404);
                        }
                    }
                    else
                    {
                        results.put("username", "Wpattern");// Wpattern: wrong pattern.
                        response.setStatus(404);
                    }
                }
                if (request.getParameterMap().containsKey("password"))
                {
                    Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,10}$");
                    Matcher matcher = pattern.matcher(request.getParameter("password"));
                    if (matcher.matches())// If input matches the pattern.
                    {
                        User user = UserDB.getUser(request.getParameter("username"));
                        System.out.println(user.toString());
                        if (user.getPassword().equals(request.getParameter("password")))
                        {
                            results.put("password", "Cpattern_Cpassword");// Cpattern_Cpassword: correct pattern, password.
                            response.setStatus(200);
                        }
                        else
                        {
                            results.put("password", "Cpattern_Wpassword");// Cpattern_Wpassword: correct pattern, wrong password.
                            response.setStatus(404);
                        }
                    }
                    else
                    {
                        results.put("password", "Wpattern");// Wpattern: wrong pattern.
                        response.setStatus(404);
                    }
                }
                int valid = 0;
                for (String value : results.values())
                {
                    if (value.equals("exist")
                            || value.equals("Cpattern_Cpassword"))
                    {
                        valid++;
                    }
                }
                if (valid == 2)
                {
                    response.setStatus(200);

                    HttpSession session = request.getSession();
                    String userName = request.getParameter("username");
                    session.setAttribute("username", userName);

                    Logger.getLogger(ValidateData.class.getName()).log(Level.INFO, null, "User " + userName + " logged in.");

                    RequestDispatcher rd = request.getRequestDispatcher("Home");
                    rd.include(request, response);
                }
            }
            else if (request.getParameter("option").equals("register"))
            {
                System.out.println("********REQUEST PARAMS: "+request.getParameterMap());
                if (request.getParameterMap().containsKey("username"))
                {
                    Pattern pattern = Pattern.compile("[A-Za-z]{8,}");
                    Matcher matcher = pattern.matcher(request.getParameter("username"));
                    if (matcher.matches())// If input matches the pattern.
                    {
                        if (!UserDB.checkValidUserName(request.getParameter("username")))// If username exists in DataBase.
                        {
                            results.put("username", "exist");// exist: username exists.
                        }
                        else
                        {
                            results.put("username", "Dexist");// Dexist: username does not exist.
                        }
                    }
                    else
                    {
                        results.put("username", "Wpattern");// Wpattern: wrong pattern.
                    }
                    response.setStatus(200);
                }
                if (request.getParameterMap().containsKey("email"))
                {
                    Pattern pattern = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$");
                    Matcher matcher = pattern.matcher(request.getParameter("email"));
                    if (matcher.matches())// If input matches the pattern.
                    {
                        if (!UserDB.checkValidEmail(request.getParameter("email")))// If email exists in DataBase.
                        {
                            results.put("email", "exist");// exist: email exists.
                        }
                        else
                        {
                            results.put("email", "Dexist");// Dexist: email does not exist.
                        }
                    }
                    else
                    {
                        results.put("email", "Wpattern");// Wpattern: wrong pattern.
                    }
                    response.setStatus(200);
                }
                if (request.getParameterMap().containsKey("password"))
                {
                    Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,10}$");
                    Matcher matcher = pattern.matcher(request.getParameter("password"));
                    if (matcher.matches())// If input matches the pattern.
                    {
                        results.put("password", "Cpattern");// Cpattern: correct pattern.
                    }
                    else
                    {
                        results.put("password", "Wpattern");// Wpattern: wrong pattern.
                    }
                    response.setStatus(200);
                }
                if (request.getParameterMap().containsKey("password_confirm"))
                {
                    Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,10}$");
                    Matcher matcher = pattern.matcher(request.getParameter("password_confirm"));
                    if (matcher.matches())// If input matches the pattern.
                    {
                        // If user typed password field also.
                        if (request.getParameterMap().containsKey("password"))
                        {
                            if (request.getParameter("password").equals(request.getParameter("password_confirm")))
                            {
                                results.put("password_confirm", "Cpattern_Cpassword_confirm");// Cpattern_Cpassword_confirm: correct pattern, password confirm.
                            }
                            else
                            {
                                results.put("password_confirm", "Cpattern_Wpassword_confirm");// Cpattern_Wpassword_confirm: correct pattern,worng pasword confirm..
                            }
                        }
                        else
                        {
                            results.put("password_confirm", "no password");// no password: no password.
                        }
                    }
                    else
                    {
                        results.put("password_confirm", "Wpattern");// Wpattern: wrong pattern.
                    }
                    response.setStatus(200);
                }


                int valid = 0;
                for (String value : results.values())
                {
                    if (value.equals("Dexist")
                            || value.equals("Cpattern")
                            || value.equals("Cpattern_Cpassword_confirm"))
                    {
                        valid++;
                    }
                }
                if (valid == 4
                        && request.getParameterMap().containsKey("account_type")
                        && request.getParameterMap().containsKey("name")
                        && request.getParameterMap().containsKey("phoneNumber")
                        && request.getParameterMap().containsKey("debitCardNumber")
                        && request.getParameterMap().containsKey("verificationCode")
                        && request.getParameterMap().containsKey("expirationDate")
                        && request.getParameterMap().containsKey("type"))
                {
                    User newUser = new User(request.getParameter("username"),
                        request.getParameter("password"),
                        request.getParameter("email"),
                        request.getParameter("name"),
                        request.getParameter("phoneNumber"),
                        request.getParameter("account_type"),
                        request.getParameter("address"),
                        request.getParameter("debitCardNumber"));
                    
                    DebitCard debitCard = new DebitCard(request.getParameter("debitCardNumber"),
                        Integer.parseInt(request.getParameter("verificationCode")),
                        request.getParameter("expirationDate"),
                        request.getParameter("type"));
                    
                    DebitCardDB.addDebitCard(debitCard);
                    UserDB.addUser(newUser);
                    results.clear();

                    response.setStatus(200);
                    RequestDispatcher rd = request.getRequestDispatcher("SuccessfulRegistration");
                    rd.forward(request, response);
                }
                response.setStatus(200);
            }
            else
            {
                response.setStatus(400);
            }
        }
        else
        {
            response.setStatus(400);
        }

        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter())
        {
            // Convert hashmap to JSON via googles GSON.
            // Dependancy for GSON added to maven.
            String jsonString = new Gson().toJson(results);
            System.out.print(results);
            System.out.print(jsonString);
            out.write(jsonString);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(ValidateData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(ValidateData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}

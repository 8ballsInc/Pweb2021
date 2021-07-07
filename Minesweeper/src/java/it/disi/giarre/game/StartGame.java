/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.disi.giarre.game;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author giarr
 */
public class StartGame extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sessione = request.getSession(true);
        String nome = (String) sessione.getAttribute("nome");
       
        
        if(nome!=null){ 
            Board board = (Board) sessione.getAttribute("board");
            
            if(board == null){
                log("KEK");
                board = new Board();
                
            }
            sessione.setAttribute("board",board);
            try(PrintWriter out = response.getWriter()){
                
                request.getRequestDispatcher("/navbar.html").include(request,response);
                out.println("<h1>Ciao "+nome+"!</h1>");
                out.println("<table id=\"maintable\">");
               for (int i = 0; i < 9; i++) {
                    out.println("<tr>");
                    for (int j = 0; j < 9; j++) {
                        String coord = "" + i + "" + j;
                        if (!board.griglia[i][j].coperta) {
                            out.println("<th id=\"" + coord + "\"  class=\"cell\" onClick=\"getValue(" + i + "," + j + ")\">" + board.griglia[i][j].value + "</th>"
                            );
                        } else {
                            out.println("<th id=\"" + coord + "\" class=\"cell\" onClick=\"getValue(" + i + "," + j + ")\">" + " " + "</th>");
                        }
                    }
                    out.println("</tr>");
                }
                out.println("</table>");
                request.getRequestDispatcher("/bottom.html").include(request,response);
            }
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
            throws ServletException, IOException {
        processRequest(request, response);
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
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

package controladores;

import modelos.Division;
import dao.DivisionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorDivision", urlPatterns = {"/ControladorDivision"})
public class ControladorDivision extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("accion")!=null){
        String accion = request.getParameter("accion");
        switch(accion){
            case "1": registrar(request,response);
                break;
            case "2": modificar(request,response);
            break;
            case "3": eliminar(request,response);
            break;
        }
         }else{
             response.sendRedirect("crudProductos.jsp?msj=No te pases");
         }
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            
            String nombre = request.getParameter("nombre").trim();
           
            if(nombre.equals("")){
                response.sendRedirect("index.jsp?msj=valores erroneos");
            }else{
                DivisionDAO ed = new  DivisionDAO();
                Division e = new Division (nombre);
                if(ed.obtenerDivision(e.getNombre())==null){
                    int respuesta = ed.registrar(e);
                    if(respuesta==1){
                    response.sendRedirect("index.jsp?msj=Division registrada");
                    }else{
                    response.sendRedirect("index.jsp.jsp?msj=Division no se pudo registrar");
                    }
                }else{
                    response.sendRedirect("index.jsp.jsp?msj=Division ya existe");
                }
            }
           }catch(Exception e){
               response.sendRedirect("index.jsp.jsp?msj="+e.getMessage());
           }
    }
    private void modificar(HttpServletRequest request, HttpServletResponse response){
        
    }
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException{
         try{
            int id =Integer.parseInt(request.getParameter("id").trim());
            String nombre = request.getParameter("nombre").trim();
           
            if(nombre.equals("")||id<1){
                response.sendRedirect("crudEstados.jsp?msj=valores erroneos");
            }else{
                DivisionDAO ed = new DivisionDAO();
                Division e = new Division(id,nombre);
                if(ed.existeDivision(e)==true){
                    DivisionDAO pd = new DivisionDAO();
                    if(ed.existeDivision(e)){
                        response.sendRedirect("crudEstados.jsp?msj=No se puede eliminar por tener productos con este estado");
                    }else{
                    int respuesta = ed.eliminar(e);
                    if(respuesta==1){
                    response.sendRedirect("crudEstados.jsp?msj=Estado eliminado");
                    }else{
                    response.sendRedirect("crudEstados.jsp?msj=Estado no se pudo eliminar");
                    }}
                }else{
                    response.sendRedirect("crudEstados.jsp?msj=Estado no existe");
                }
            }
           }catch(Exception e){
               response.sendRedirect("crudEstados.jsp?msj="+e.getMessage());
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
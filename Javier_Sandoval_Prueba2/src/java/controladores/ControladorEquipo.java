package controladores;

import dao.DivisionDAO;
import dao.EstadioDAO;

import dao.EquipoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Equipo;


@WebServlet(name = "ControladorEquipo", urlPatterns = {"/ControladorEquipo"})
public class ControladorEquipo extends HttpServlet {

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
         if(request.getParameter("accion")!=null){
        String accion = request.getParameter("accion");
        switch(accion){
            case "1": registrar(request,response);
                break;
           
        }
         }else{
             response.sendRedirect("crudProductos.jsp?msj=No te pases");
         }
    }
    
    /*private void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException{
         try{
            int cantidad = Integer.parseInt(request.getParameter("cantidad").trim());
            String nombre = request.getParameter("nombre").trim();
            String descripcion = request.getParameter("descripcion").trim();*/
         
    
         private void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException{
           try{
           int codigo =Long.parseLong(request.getParameter("codigo").trim());
            String nombre = request.getParameter("nombre").trim();
            
     
            if(codigo<1||nombre.equals("")||descripcion.equals("")||cantidad<1||precio<1||estado<1){
                response.sendRedirect("crudProductos.jsp?msj=valores erroneos");
            }else{
                EstadioDAO ed = new  EstadioDAO ();
                Equipo nuevoEquipo = new Equipo (codigo,nombre,descripcion,
                        cantidad,precio,ed.obtenerEstadio(estadio));
                EquipoDAO pd = new EquipoDAO();
                if(pd.obtenerProducto(nuevoProducto.getCodigo())==null){
                    int respuesta = pd.registrar(nuevoProducto);
                    if(respuesta==1){
                    response.sendRedirect("crudProductos.jsp?msj=equipo registrado registrado");
                    }else{
                    response.sendRedirect("index.jsp?msj=Equipo no se pudo registrar");
                    }
                }else{
                    response.sendRedirect("registraequipo.jsp?msj=Producto ya existe");
                }
            }
           }catch(Exception e){
               response.sendRedirect("registraequipo.jsp?msj="+e.getMessage());
           }
        }
         
         
         
             
    private void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException{
         try{
            long codigo =Long.parseLong(request.getParameter("codigo").trim());
            String nombre = request.getParameter("nombre").trim();
            String descripcion = request.getParameter("descripcion").trim();
            int cantidad = Integer.parseInt(request.getParameter("cantidad").trim());
            int precio = Integer.parseInt(request.getParameter("precio").trim());
            int estado =Integer.parseInt( request.getParameter("estado").trim());
            if(codigo<1||nombre.equals("")||descripcion.equals("")||cantidad<1||precio<1||estado<1){
                response.sendRedirect("modProducto.jsp?msj=valores erroneos");
            }else{
                EstadioDAO  ed = new EstadioDAO();
                Equipo nuevoEquipo = new Equipo (codigo,nombre,descripcion,
                        cantidad,precio,ed.obtenerEstadio(estadio));
                if(pd.obtenerProducto(nuevoProducto.getCodigo())==null){
                    response.sendRedirect("modProducto.jsp?msj=Codigo de producto inexistente");
                }else{
                   int respuesta = pd.modificar(nuevoProducto);
                   if(respuesta>0){
                       response.sendRedirect("crudProductos.jsp?msj=Producto modificado");
                   }else{
                       response.sendRedirect("crudProductos.jsp?msj=Producto no se pudo modificar");
                   }
                }
            }
         }catch(Exception e){
             
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

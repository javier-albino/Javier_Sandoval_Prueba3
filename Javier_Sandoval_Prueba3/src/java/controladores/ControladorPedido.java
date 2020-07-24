package controladores;

import dao.PedidoDAO;
import dao.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Producto;
import modelos.Pedido;

@WebServlet(name = "ControladorPedido", urlPatterns = {"/ControladorPedido"})
public class ControladorPedido extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("accion")!=null){
        String accion = request.getParameter("accion");
        switch(accion){
            case "1": registrar(request,response);
                break;
            //*case "2": modificar(request,response);
            //break;
            //case "3": eliminar(request,response);
          //  break; 
        }
         }else{
             response.sendRedirect("index.jsp?msj=No te pases");
         }
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException{
           try{
             int producto =Integer.parseInt( request.getParameter("producto").trim());
             int cantidad = Integer.parseInt(request.getParameter("cantidad").trim());
             String correo = request.getParameter("correo").trim();
             String estado = "pendiente";
            
            
            if(cantidad<1||producto<1){
                response.sendRedirect("index.jsp?msj=valores erroneos");
            }else{
                ProductoDAO ed = new ProductoDAO();
                Pedido nuevoPedido = new Pedido(ed.obtenerProducto(producto),cantidad,correo,estado);
                PedidoDAO pd = new PedidoDAO();
                if(pd.obtenerPedido(nuevoPedido.getId())==null){
                    
                    int respuesta = pd.registrar(nuevoPedido);
                    if(respuesta==1){
                    response.sendRedirect("homePedidos.jsp?msj=Pedido enviado");
                    }else{
                    response.sendRedirect("homePedidos.jsp?msj=Pedido no se pudo enviar");
                    }
                }else{
                    response.sendRedirect("homePedidos.jsp?msj=Pedido ya existe");
                }
            }
           }catch(Exception e){
               response.sendRedirect("homePedidos.jsp?msj="+e.getMessage());
           }

    }
    /*private void modificar(HttpServletRequest request, HttpServletResponse response){
        
    }
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException{
         try{
            int id =Integer.parseInt(request.getParameter("id").trim());
            String nombre = request.getParameter("nombre").trim();
           
            if(nombre.equals("")||id<1){
                response.sendRedirect("crudEstados.jsp?msj=valores erroneos");
            }else{
                CiudadDAO ed = new CiudadDAO();
                Ciudad e = new Ciudad(id,nombre);
                if(ed.obtenerCiudad(e.getId())!=null){
                    EstadioDAO pd = new EstadioDAO();
                    if(ed.existeCiudad(e)){
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
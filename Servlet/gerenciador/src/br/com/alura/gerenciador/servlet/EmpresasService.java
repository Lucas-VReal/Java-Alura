package br.com.alura.gerenciador.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;
import com.google.gson.Gson;

@WebFilter(urlPatterns="/empresas")
public class EmpresasService extends HttpServlet{

    public static final long serialVersionUID  = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException) {

        List<Empresa> empresas = new Banco().getEmpresas();

        String valor = request.getHeader("Acept");

        if(valor.contains("xml")){
            XStream xstream = new XStream();
            xstream.alias("empresa", Empresa.class);
            String xml = xstream.toXML(empresas);

            response.setContentType("application/xml");
            response.getWriter().print(xml);
        } else if(valor.contains("json")) {
            Gson gson = new Gson();
            String json = gson.toJson(empresas);

            response.setContentType("application/json");
            response.getWriter().print(json);
        }else{
            response.setContentType("application/json");
            response.getWriter().print("message: no content");
        }

    }

}
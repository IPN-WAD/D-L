package ManagerBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@RequestScoped
public class MBSession 
{
private String id;
private final HttpServletRequest request;
private final FacesContext fc;
private FacesMessage fm;

    public MBSession() 
    {
        fc= FacesContext.getCurrentInstance();
        request = (HttpServletRequest)fc.getExternalContext().getRequest();        
        if(request.getSession().getAttribute("sesionusuario")!=null)
        {
        id=(String)request.getSession().getAttribute("sesionusuario");
        }
    }
    public String cerrarSesion()
    {
    request.getSession().removeAttribute("sesionusuario");
    fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"sesion cerrada",null);
    fc.addMessage(null, fm);    
    return "index";
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
}


package ManagerBean;

import entity.HibernateUtil;
import entity.Users;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean
@RequestScoped
public class MB {
    private String id,Nombre,tipo;
    private String password;
    
    private final HttpServletRequest request;
    private final FacesContext fc;
    private FacesMessage fm;
    Session hibernateSession;
    Users login;
    private List result;
    
    public MB() {
         fc= FacesContext.getCurrentInstance();
         request = (HttpServletRequest)fc.getExternalContext().getRequest();
    }
    
    public String validate()
    {
        hibernateSession=HibernateUtil.getSessionFactory().openSession(); 
        //Query query=hibernateSession.createQuery("from Users where idUsers= :id");
        //query.setParameter("id", 4);
       // result = query.list();
        //result.iterator();
        Transaction t1=hibernateSession.beginTransaction();
        login=(Users) hibernateSession.createQuery("from Users where idUsers='"+id+"'AND Password='"+password+"'").uniqueResult();
        t1.commit();
        
        if(id!=null && password!=null &&(!id.equals(""))&&(!password.equals("")))
        {
            if(login!=null){
                
                request.getSession().setAttribute("sesionusuario",id);
                tipo = login.getType();
                if(tipo.equals("Student")){
                    fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Welcome my friend"+login.getName(),null);
                    fc.addMessage(null, fm);
                    return "Students";
                }
                else if(tipo.equals("Teacher")){
                    fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Welcome"+login.getName(),null);
                    fc.addMessage(null, fm);
                    return "Teachers";
                }
                else if(tipo.equals("Administrator")){
                    fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Welcome"+login.getName(),null);
                    fc.addMessage(null, fm);
                    return "Admin";
                }
            }
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Incorrecto",null);
            fc.addMessage(null, fm);
            return "index";  
        }
        else{
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Incorrecto",null);
            fc.addMessage(null, fm);
            return "index";  
        }
        
     
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    
    
}

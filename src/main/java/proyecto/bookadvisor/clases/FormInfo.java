package proyecto.bookadvisor.clases;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class FormInfo {   

   @NotEmpty(message = "No puede estar vacio")
   private String nombre;

   @NotEmpty(message = "No puede estar vacio")
   @Email(message = "Forma de correo incorrecto")
   private String email;

   @NotEmpty(message = "No puede estar vacio")
   private String tipo_contact;

   @NotEmpty(message = "No puede estar vacio")
   private String comentario;

   @AssertTrue(message = "Debe aceptarlas condiciones")
   private boolean acepta_condiciones;


   @Override
   public String toString() {
     return "<table>" +
         "<tr><td><strong>nombre</strong></td><td>" + nombre + "\n</td></tr>" +
         "<tr><td><strong>email</strong></td><td>" + email + "\n</td></tr>" +
         "<tr><td><strong>tipo Contacto</strong></td><td>" + tipo_contact + "\n</td></tr>" +
         "<tr><td><strong>comentario</strong></td><td>" + comentario + "\n</td></tr>" +
         "<tr><td><strong>acepta condiciones</strong></td><td>" + acepta_condiciones + "</td></tr>" +
         "</table>";
   }
   
    /**
     * @return String return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the tipo_contact
     */
    public String getTipo_contact() {
        return tipo_contact;
    }

    /**
     * @param tipo_contact the tipo_contact to set
     */
    public void setTipo_contact(String tipo_contact) {
        this.tipo_contact = tipo_contact;
    }

    /**
     * @return String return the comentarios
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return boolean return the acepta_condiciones
     */
    public boolean isAcepta_condiciones() {
        return acepta_condiciones;
    }

    /**
     * @param acepta_condiciones the acepta_condiciones to set
     */
    public void setAcepta_condiciones(boolean acepta_condiciones) {
        this.acepta_condiciones = acepta_condiciones;
    }

}

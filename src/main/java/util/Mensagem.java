package util;

import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author aluno
 */
public class Mensagem {

    public static void show(String mensagem) {
        show(FacesMessage.SEVERITY_INFO, "Aviso", mensagem);
    }

    public static void showError(String mensagem) {
        show(FacesMessage.SEVERITY_ERROR, "Erro", mensagem);
    }

    private static void show(
            FacesMessage.Severity tipo,
            String titulo, String mensagem) {

        FacesMessage msg = new FacesMessage(
                tipo,
                titulo,
                mensagem
        );
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
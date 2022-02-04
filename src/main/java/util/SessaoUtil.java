/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author aluno
 */
public class SessaoUtil {

    public static User getUsuarioLogado() {
        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        User logado = (User) sessao.getAttribute("usuarioLogado");
        return logado;
    }
}

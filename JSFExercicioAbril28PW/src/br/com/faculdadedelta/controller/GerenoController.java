package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.GeneroDAO;
import br.com.faculdadedelta.modelo.Generos;

@ManagedBean
@SessionScoped
public class GerenoController {
	private  Generos genero = new Generos();
	private GeneroDAO dao = new GeneroDAO();

	public Generos getGenero() {
		return genero;
	}

	public void setGenero(Generos genero) {
		this.genero = genero;
	}

	public void limparCampos() {
		genero = new Generos();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if (genero.getIdGenero() == null) {
				dao.incluir(genero);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				dao.alterar(genero);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, "
					+ "tente novamente mais tarde! " + e.getMessage());
		}
		return "cadastroGenero.xhtml";
	}

	public String editar() {
		return "cadastroGenero.xhtml";
	}

	public String excluir() {
		try {
			dao.excluir(genero);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, "
					+ "tente novamente mais tarde! " + e.getMessage());
		}
		return "listaGenero.xhtml";
	}

	public List<Generos> getLista() {
		List<Generos> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, "
					+ "tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	}
	public String limpar() {
		limparCampos();
		return "cadastroGenero.xhtml";
	}

}


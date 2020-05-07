package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.SeriesDAO;
import br.com.faculdadedelta.modelo.Generos;
import br.com.faculdadedelta.modelo.Series;
import br.com.faculdadedelta.modelo.Status;

@ManagedBean
@SessionScoped
public class SeriesController {
	private Series series = new Series();
	private SeriesDAO dao = new SeriesDAO();
	private Generos generoSelecionado = new Generos();	
	private Status statusSelecionado = new Status();

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

	public Generos getGeneroSelecionado() {
		return generoSelecionado;
	}

	public void setGeneroSelecionado(Generos generoSelecionado) {
		this.generoSelecionado = generoSelecionado;
	}

	public Status getStatusSelecionado() {
		return statusSelecionado;
	}

	public void setStatusSelecionado(Status statusSelecionado) {
		this.statusSelecionado = statusSelecionado;
	}

	public void limparCampos() {
		series = new Series();
		generoSelecionado = new Generos();
		statusSelecionado = new Status();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if (series.getIdSeries() == null) {
				series.setGeneros(generoSelecionado);
				series.setStatus(statusSelecionado);
				dao.incluir(series);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				series.setGeneros(generoSelecionado);
				series.setStatus(statusSelecionado);
				dao.alterar(series);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, "
					+ "tente novamente mais tarde! " + e.getMessage());			
		}
		return "cadastroSeries.xhtml";
	}

	public String editar() {
		generoSelecionado = series.getGeneros();
		statusSelecionado = series.getStatus();
		
		return "cadastroSeries.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(series);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, "
					+ "tente novamente mais tarde! " + e.getMessage());			
		}
		return "listaSeries.xhtml";
	}
	
	public List<Series> getLista() {
		List<Series> listaRetorno = new ArrayList<>();
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
		return "cadastroSeries.xhtml";
	}
}
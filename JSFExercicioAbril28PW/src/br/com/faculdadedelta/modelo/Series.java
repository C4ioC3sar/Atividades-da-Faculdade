package br.com.faculdadedelta.modelo;

public class Series {

	private Long idSeries;
	private Generos generos;
	private Status status;
	private String nomeSeries;
	private String comentarioSeries;
	private int notaAvaliacao;
	
	public Series() {
		super();
	}

	public Series(Long idSeries, Generos generos, Status status,
			String nomeSeries, String comentarioSeries, int notaAvaliacao) {
		super();
		this.idSeries = idSeries;
		this.generos = generos;
		this.status = status;
		this.nomeSeries = nomeSeries;
		this.comentarioSeries = comentarioSeries;
		this.notaAvaliacao = notaAvaliacao;
	}

	public Long getIdSeries() {
		return idSeries;
	}

	public void setIdSeries(Long idSeries) {
		this.idSeries = idSeries;
	}

	public Generos getGeneros() {
		return generos;
	}

	public void setGeneros(Generos generos) {
		this.generos = generos;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getNomeSeries() {
		return nomeSeries;
	}

	public void setNomeSeries(String nomeSeries) {
		this.nomeSeries = nomeSeries;
	}

	public String getComentarioSeries() {
		return comentarioSeries;
	}

	public void setComentarioSeries(String comentarioSeries) {
		this.comentarioSeries = comentarioSeries;
	}

	public int getNotaAvaliacao() {
		return notaAvaliacao;
	}

	public void setNotaAvaliacao(int notaAvaliacao) {
		this.notaAvaliacao = notaAvaliacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idSeries == null) ? 0 : idSeries.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Series other = (Series) obj;
		if (idSeries == null) {
			if (other.idSeries != null)
				return false;
		} else if (!idSeries.equals(other.idSeries))
			return false;
		return true;
	}
	
}

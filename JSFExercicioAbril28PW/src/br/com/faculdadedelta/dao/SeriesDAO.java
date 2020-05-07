package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.Generos;
import br.com.faculdadedelta.modelo.Series;
import br.com.faculdadedelta.modelo.Status;
import br.com.faculdadedelta.util.ConexaoCaioMatos;

public class SeriesDAO {
	public void incluir(Series series) throws Exception {
		Connection conn = ConexaoCaioMatos.conectarNoBancoDeDados();
		String sql = "INSERT INTO series(id_genero, id_status, nome, comentario, nota_avaliacao) "
				+ "VALUES (?, ?, ?, ?, ?)";

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);

			ps.setLong(1, series.getGeneros().getIdGenero());
			ps.setLong(2, series.getStatus().getIdStatus());
			ps.setString(3, series.getNomeSeries());
			ps.setString(4, series.getComentarioSeries());
			ps.setInt(5, series.getNotaAvaliacao());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			ConexaoCaioMatos.fecharConexao(conn, ps, null);
		}
	}

	public void alterar(Series series) throws Exception {
		Connection conn = ConexaoCaioMatos.conectarNoBancoDeDados();
		String sql = "UPDATE series SET id_genero = ?, id_status = ?, "
				+ "nome = ?, comentario= ?, nota_avaliacao = ? "
				+ "WHERE id = ?";

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setLong(1, series.getGeneros().getIdGenero());
			ps.setLong(2, series.getStatus().getIdStatus());
			ps.setString(3, series.getNomeSeries());
			ps.setString(4, series.getComentarioSeries());
			ps.setInt(5, series.getNotaAvaliacao());
			ps.setLong(6, series.getIdSeries());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			ConexaoCaioMatos.fecharConexao(conn, ps, null);
		}
	}

	public void excluir(Series series) throws Exception {
		Connection conn = ConexaoCaioMatos.conectarNoBancoDeDados();
		String sql = "DELETE FROM series WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, series.getIdSeries());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			ConexaoCaioMatos.fecharConexao(conn, ps, null);
		}
	}

	public List<Series> listar() throws Exception {
		Connection conn = ConexaoCaioMatos.conectarNoBancoDeDados();
		String sql = "SELECT se.id AS idSe, "
				+ "g.id AS idGenero, "
				+ "g.descricao AS descG, "
				+ "s.id AS idStatus, "
				+ "s.descricao AS descS, "
				+ "se.nome AS nomeSe, "
				+ "se.comentario AS coment, "
				+ "se.nota_avaliacao AS notaAvaliacao "
				+ "FROM series se "
				+ "INNER JOIN genero g ON se.id_genero = g.id "
				+ "INNER JOIN status s ON se.id_status = s.id";

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Series> listaRetorno = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

				Series series = new Series();
				series.setIdSeries(rs.getLong("idSe"));
				series.setNomeSeries(rs.getString("nomeSe").trim());
				series.setComentarioSeries(rs.getString("coment").trim());
				series.setNotaAvaliacao(rs.getInt("notaAvaliacao"));

				Generos genero = new Generos();
				genero.setIdGenero(rs.getLong("idGenero"));
				genero.setDescricao(rs.getString("descG").trim());
				
				Status status = new Status();
				status.setIdStatus(rs.getLong("idGenero"));
				status.setDescricao(rs.getString("descS").trim());
				
				series.setGeneros(genero);
				series.setStatus(status);

				listaRetorno.add(series);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			ConexaoCaioMatos.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;
	}
}
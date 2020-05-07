package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.Generos;
import br.com.faculdadedelta.util.ConexaoCaioMatos;

public class GeneroDAO {
	public void incluir(Generos genero) throws Exception {

		Connection conn = ConexaoCaioMatos.conectarNoBancoDeDados();
		String sql = "INSERT INTO genero(descricao)VALUES (?)";

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, genero.getDescricao().trim());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			ConexaoCaioMatos.fecharConexao(conn, ps, null);
		}
	}

	public void alterar(Generos genero) throws Exception {
		Connection conn = ConexaoCaioMatos.conectarNoBancoDeDados();
		String sql = "UPDATE genero SET descricao = ? WHERE id = ?";

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, genero.getDescricao().trim());
			ps.setLong(2, genero.getIdGenero());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			ConexaoCaioMatos.fecharConexao(conn, ps, null);
		}
	}

	public void excluir(Generos genero) throws Exception {
		Connection conn = ConexaoCaioMatos.conectarNoBancoDeDados();
		String sql = "DELETE FROM genero WHERE id = ?";

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, genero.getIdGenero());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			ConexaoCaioMatos.fecharConexao(conn, ps, null);
		}
	}

	public List<Generos> listar() throws Exception {
		Connection conn = ConexaoCaioMatos.conectarNoBancoDeDados();
		String sql = "SELECT id, descricao FROM genero";

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Generos> listaRetorno = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				Generos genero = new Generos();

				genero.setIdGenero(rs.getLong("id"));
				genero.setDescricao(rs.getString("descricao").trim());

				listaRetorno.add(genero);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			ConexaoCaioMatos.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;
	}

	public Generos pesquisarPorId(Long id) throws Exception {
		Connection conn = ConexaoCaioMatos.conectarNoBancoDeDados();
		String sql = "SELECT id, descricao FROM genero WHERE id = ?";

		PreparedStatement ps = null;
		ResultSet rs = null;
		Generos retorno = new Generos();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {

				retorno.setIdGenero(rs.getLong("id"));
				retorno.setDescricao(rs.getString("descricao").trim());

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			ConexaoCaioMatos.fecharConexao(conn, ps, rs);
		}

		return retorno;
	}
}

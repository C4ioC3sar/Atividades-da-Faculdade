package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.Status;
import br.com.faculdadedelta.util.ConexaoCaioMatos;

public class StatusDAO {
	public void incluir(Status status) throws Exception {

		Connection conn = ConexaoCaioMatos.conectarNoBancoDeDados();
		String sql = "INSERT INTO status(descricao)VALUES(?)";

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, status.getDescricao().trim());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			ConexaoCaioMatos.fecharConexao(conn, ps, null);
		}
	}

	public void alterar(Status status) throws Exception {
		Connection conn = ConexaoCaioMatos.conectarNoBancoDeDados();
		String sql = "UPDATE status SET descricao = ? WHERE id = ?";

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, status.getDescricao().trim());
			ps.setLong(2, status.getIdStatus());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			ConexaoCaioMatos.fecharConexao(conn, ps, null);
		}
	}

	public void excluir(Status status) throws Exception {
		Connection conn = ConexaoCaioMatos.conectarNoBancoDeDados();
		String sql = "DELETE FROM status WHERE id = ?";

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, status.getIdStatus());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			ConexaoCaioMatos.fecharConexao(conn, ps, null);
		}
	}

	public List<Status> listar() throws Exception {
		Connection conn = ConexaoCaioMatos.conectarNoBancoDeDados();
		String sql = "SELECT id, descricao FROM status";

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Status> listaRetorno = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				Status status = new Status();

				status.setIdStatus(rs.getLong("id"));
				status.setDescricao(rs.getString("descricao").trim());

				listaRetorno.add(status);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			ConexaoCaioMatos.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;
	}

	public Status pesquisarPorId(Long id) throws Exception {
		Connection conn = ConexaoCaioMatos.conectarNoBancoDeDados();
		String sql = "SELECT * FROM status WHERE id = ?";

		PreparedStatement ps = null;
		ResultSet rs = null;
		Status retorno = new Status();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {

				retorno.setIdStatus(rs.getLong("id"));
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

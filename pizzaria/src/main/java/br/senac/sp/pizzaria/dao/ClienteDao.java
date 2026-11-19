package br.senac.sp.pizzaria.dao;

import br.senac.sp.pizzaria.factory.ConnectionFactory;
import br.senac.sp.pizzaria.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao implements InterfaceDao<Cliente> {

    @Override
    public void inserir(Cliente objeto) {
        String sql = "INSERT INTO tb_cliente (nome, telefone, endereco, aniversario) VALUES (?,?,?,?)";
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getTelefone());
            stmt.setString(3, objeto.getEndereco());
            stmt.setObject(4, objeto.getAniversario());
            stmt.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void alterar(Cliente objeto) {
        String sql = "UPDATE tb_cliente SET nome = ?, telefone = ?, endereco = ?, aniversario = ? WHERE id = ?";
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getTelefone());
            stmt.setString(3, objeto.getEndereco());
            stmt.setObject(4, objeto.getAniversario());
            stmt.setLong(5, objeto.getId());
            stmt.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void excluir(Long id) {
        String sql = "DELETE FROM tb_cliente WHERE id = ?";
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cliente> listar() {
        String sql = "SELECT * FROM tb_cliente ORDER BY nome";
        List<Cliente> lista = new ArrayList<>();
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getLong("id"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setEndereco(rs.getString("endereco"));
                c.setAniversario(rs.getObject("aniversario", LocalDate.class));
                lista.add(c);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Cliente> listar(String telefone) {
        String sql = "SELECT * FROM tb_cliente  WHERE telefone LIKE ? ORDER BY nome";
        List<Cliente> lista = new ArrayList<>();
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, "%" + telefone + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getLong("id"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setEndereco(rs.getString("endereco"));
                c.setAniversario(rs.getObject("aniversario", LocalDate.class));
                lista.add(c);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
}

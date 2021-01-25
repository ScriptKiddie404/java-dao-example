package modelo;

import DBUtils.DBConnection;

import static modelo.mysqlqueries.UserQueries.*;
import static modelo.mysqlqueries.ConstantesUsuario.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsuarioRepositorio implements Repositorio<Usuario> {

    @Override
    public List<Usuario> listAll() {

        List<Usuario> userList = new ArrayList<>();

        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(LIST_ALL_USERS)) {

            while (resultSet.next()) {
                Usuario user = getUser(resultSet);
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public Usuario findById(Long id) {

        Usuario user = null;
        try (PreparedStatement statement = getConnection().prepareStatement(FIND_USER_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = getUser(resultSet);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void save(Usuario usuario) {
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT_USER)) {

            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setLong(3, usuario.getEdad());
            statement.setString(4, usuario.getPais());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAll(List<Usuario> list) {
        for (Usuario usuario : list) {
            save(usuario);
        }
    }

    @Override
    public void update(Usuario usuario) {

        try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_USER_BY_ID)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setLong(3, usuario.getEdad());
            statement.setString(4, usuario.getPais());
            statement.setLong(5, usuario.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement statement = getConnection().prepareStatement(DELETE_USER)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Usuario getUser(ResultSet resultSet) throws SQLException {
        Usuario user = new Usuario();
        user.setId(resultSet.getLong(ID));
        user.setNombre(resultSet.getString(NOMBRE));
        user.setApellido(resultSet.getString(APELLIDO));
        user.setEdad(resultSet.getLong(EDAD));
        user.setPais(resultSet.getString(PAIS));
        return user;
    }

    private Connection getConnection() throws SQLException {
        return DBConnection.getInstance();
    }

}

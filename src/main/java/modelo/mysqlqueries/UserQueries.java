package modelo.mysqlqueries;

public final class UserQueries {

    public static final String LIST_ALL_USERS = "SELECT * FROM usuarios";
    public static final String FIND_USER_BY_ID = "SELECT * FROM usuarios WHERE id = ?";
    public static final String INSERT_USER = "INSERT INTO usuarios(nombre, apellido, edad, pais) VALUES(?, ?, ?, ?)";
    public static final String UPDATE_USER_BY_ID = "UPDATE usuarios SET nombre = ?, apellido = ?, edad = ?, pais = ? WHERE id = ?";
    public static final String DELETE_USER = "DELETE FROM usuarios WHERE id = ?";


}

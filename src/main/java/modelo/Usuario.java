package modelo;

import lombok.Data;

@Data
public class Usuario {
    private Long id;
    private String nombre;
    private String apellido;
    private long edad;
    private String pais;
}

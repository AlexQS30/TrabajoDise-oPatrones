
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDAO {
    Connection con;
    private PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    public login log(String correo, String pass){
    login l = new login();
    String sql = "SELECT * FROM usuarios WHERE correo = ? AND pass = ?";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, correo);
        ps.setString(2, pass);
        ps.executeQuery();
        rs = ps.executeQuery();
        if (rs.next()){
            l.setId(rs.getInt("id"));
            l.setNombre(rs.getString("nombre"));
            l.setCorreo(rs.getString("correo"));
            l.setPass(rs.getString("pass"));
            l.setRol(rs.getString("rol"));
        }
        
    }catch(Exception e){
        System.out.println(e.toString());
    }
    return l;
    }
    
    public boolean Registrar(login reg){
        String sql = "INSERT INTO usuarios(nombre, correo, pass, rol) VALUES (?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, reg.getNombre());
            ps.setString(2, reg.getCorreo());
            ps.setString(3, reg.getPass());
            ps.setString(4, reg.getRol());
            ps.executeQuery();
            return true;
        }catch (SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
}

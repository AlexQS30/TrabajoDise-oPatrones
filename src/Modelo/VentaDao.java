
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDao {
    Connection con; 
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    public int IdVenta(){
        int id = 0;
        String sql = "SELECT MAX(id) FROM ventas";
        try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println(e.toString()); 
        }
        return id;
    }
    public int RegistrarVenta(Venta v){
        String sql = "INSERT INTO ventas (cliente, vendedor, total, fecha) VALUES (?,?,?, ?)";
        try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, v.getCliente());
           ps.setString(2, v.getVendedor());
           ps.setDouble(3, v.getTotal());
           ps.setString(4, v.getFecha());
           ps.execute();
        }catch (SQLException e){
            System.out.println(e.toString());  
        }finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }
        return r;
    }
    public int RegistrarDetalle(Detalle Dv){
        String sql = "INSERT INTO detalle (cod_pro, cantidad, precio, id_venta) VALUES (?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, Dv.getCod_pro());
            ps.setInt(2, Dv.getCantidad());
            ps.setDouble(3, Dv.getPrecio());
            ps.setInt(4, Dv.getId());
            ps.execute();
        }catch (SQLException e){
            System.out.println(e.toString());
        }finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }
        return r;
    }
    
    public List ListarVentas(){
        List<Venta> ListaVenta = new ArrayList();
        String sql = "SELECT * FROM ventas";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Venta vent = new Venta();
                vent.setId(rs.getInt("id"));
                vent.setCliente(rs.getString("cliente"));
                vent.setVendedor(rs.getString("vendedor"));
                vent.setTotal(rs.getDouble("total"));
                ListaVenta.add(vent);
            }
        }catch (SQLException e){
            System.out.println(e.toString());
        }
        return ListaVenta;
    } 
}

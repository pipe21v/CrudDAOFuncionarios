
package dao;

import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.*;

/**
 *
 * @author Luis Felipe
 */
public class DAOFuncionarios implements Operaciones{
    
    Database db = new Database();
    Funcionarios fun = new Funcionarios();
    
    @Override
    public boolean insertar(Object obj) {
        Funcionarios fun = (Funcionarios) obj;
        Connection con = null;
        PreparedStatement pst = null;
        String sql = "INSERT INTO funcionario (tipo_id, num_id, nombres, apellidos, estado_civil, sexo, direccion, telefono, fecha_nacimiento) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
        try {
        Class.forName(db.getDriver());
        con = DriverManager.getConnection(
                db.getUrl(),
                db.getUsername(),
                db.getPassword()
        );
        pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setString(1, fun.getTipoIdentificacion());
        pst.setInt(2, fun.getNumeroIdentificacion());
        pst.setString(3, fun.getNombre());
        pst.setString(4, fun.getApellido());
        pst.setString(5, fun.getEstadoCivil());
        pst.setString(6, fun.getSexo());
        pst.setString(7, fun.getDireccion());
        pst.setInt(8, fun.getTelefono());
        pst.setDate(9, new java.sql.Date(fun.getFechaNacimiento().getTime()));

        int filas = pst.executeUpdate();
        if (filas > 0) {
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                long idGenerado = generatedKeys.getLong(1);
                // idGenerado contiene el valor autoincrementable generado
                // Puedes usarlo para cualquier propósito necesario
            }
            con.close();
            return true;
        } else {
            con.close();
            return false;
        }
    } catch (SQLException | ClassNotFoundException e) {
        JOptionPane.showMessageDialog(null, "Ocurrió el siguiente error: " + e.getMessage());
        return false;
    }
}

    @Override
public boolean eliminar(Object obj) {
    Funcionarios fun = (Funcionarios) obj;
    Connection con = null;
    PreparedStatement pst = null;
    String sql = "DELETE FROM funcionario WHERE Num_id = ?";
    
    try {
        Class.forName(db.getDriver());
        con = DriverManager.getConnection(
            db.getUrl(),
            db.getUsername(),
            db.getPassword()
        );
        pst = con.prepareStatement(sql);
        pst.setInt(1, fun.getNumeroIdentificacion()); // Establece el parámetro para el campo Num_id

        int filas = pst.executeUpdate();
        if (filas > 0) {
            con.close();
            return true;
        } else {
            con.close();
            return false;
        }
    } catch (SQLException | ClassNotFoundException e) {
        JOptionPane.showMessageDialog(null, "Ocurrió el siguiente error: " + e.getMessage());
        return false;
    }
}

    @Override
public boolean modificar(Object obj) {
    Funcionarios fun = (Funcionarios) obj;
    
    try (Connection con = DriverManager.getConnection(db.getUrl(), db.getUsername(), db.getPassword());
         PreparedStatement pst = con.prepareStatement(
            "UPDATE funcionario SET tipo_id=?, num_id=?, nombres=?, apellidos=?, estado_Civil=?, sexo=?, direccion=?, telefono=? WHERE id=?")) {
         
        pst.setString(1, fun.getTipoIdentificacion());
        pst.setInt(2, fun.getNumeroIdentificacion());
        pst.setString(3, fun.getNombre());
        pst.setString(4, fun.getApellido());
        pst.setString(5, fun.getEstadoCivil());
        pst.setString(6, fun.getSexo());
        pst.setString(7, fun.getDireccion());
        pst.setInt(8, fun.getTelefono());
        pst.setDate(9, new java.sql.Date(fun.getFechaNacimiento().getTime()));

        int filas = pst.executeUpdate();
        return filas > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Ocurrió el siguiente error: " + e.getMessage());
        return false;
    }
}

    @Override
    public ArrayList<Object[]> consultar() {
        ArrayList<Object[]> data = new ArrayList<>();
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "select * from funcionario";
        
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(
                    db.getUrl(),
                    db.getUsername(),
                    db.getPassword()
            );
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                Object[] fila = new Object [10];
                for(int i=0; i<=9; i++){
                    fila[i]=rs .getObject(i+1);
                }
                data.add(fila);
            }
            con.close();
            
        }catch (SQLException| ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, 
                    "Ocurrio este error" + e.getMessage());
        }finally{
            return data;
            }
    }
    
}

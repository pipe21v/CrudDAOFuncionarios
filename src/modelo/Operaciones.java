
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Luis Felipe
 */
public interface Operaciones {
    
    public boolean insertar(Object obj);
    public boolean eliminar(Object obj);
    public boolean modificar(Object obj);
    public ArrayList<Object[]> consultar();
    
}

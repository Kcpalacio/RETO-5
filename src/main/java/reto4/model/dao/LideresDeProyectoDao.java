package reto4.model.dao;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import reto4.util.*;
import java.util.List;
import java.util.ArrayList;
import reto4.model.vo.LideresDeProyectoVo;

public class LideresDeProyectoDao {
    public List<LideresDeProyectoVo> listar() throws SQLException{
        ArrayList<LideresDeProyectoVo> respuesta = new ArrayList<LideresDeProyectoVo>();
        Connection conn = JDBCUtilities.getConnection();
        Statement stm = null;
        ResultSet rs = null;
        String consulta = "SELECT ID_Lider as id, Nombre , Primer_Apellido as apellido, Ciudad_Residencia as ciudad FROM Lider l order by Ciudad_Residencia";
        try{
            stm = conn.createStatement();
            rs = stm.executeQuery(consulta);
            while(rs.next()){
                LideresDeProyectoVo vo = new LideresDeProyectoVo();
                vo.setId(rs.getInt("id"));
                vo.setNombre(rs.getString("nombre"));
                vo.setApellido(rs.getString("apellido"));
                vo.setCiudad(rs.getString("ciudad"));           
                respuesta.add(vo);
            }
            
        }
        finally{
            if (rs != null){
                rs.close();
            }
            if (stm != null){
                stm.close();
            }
            if(conn != null){
                conn.close();
            }
        }
        return respuesta;
    }
    
}

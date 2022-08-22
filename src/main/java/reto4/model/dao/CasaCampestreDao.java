package reto4.model.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import reto4.model.vo.CasaCampestreVo;
import reto4.util.JDBCUtilities;

public class CasaCampestreDao {
    public List<CasaCampestreVo> listar() throws SQLException{
        List<CasaCampestreVo> respuesta = new ArrayList<CasaCampestreVo>(); 
        Connection conn = JDBCUtilities.getConnection();
        Statement stm = null;
        ResultSet rs = null;
        String consulta = "SELECT ID_Proyecto as id, Constructora, Numero_Habitaciones as habitaciones, Ciudad FROM Proyecto WHERE Clasificacion = 'Casa Campestre' AND Ciudad IN('Santa Marta','Cartagena', 'Barranquilla')";
        try{
            stm = conn.createStatement();
            rs = stm.executeQuery(consulta);
            while (rs.next()){
                CasaCampestreVo vo = new CasaCampestreVo();
                vo.setId(rs.getInt("id"));
                vo.setConstructora(rs.getString("constructora"));
                vo.setHabitaciones(rs.getInt("habitaciones"));
                vo.setCiudad(rs.getString("ciudad"));
                respuesta.add(vo);
            }
        }
    
        finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(conn != null){
                conn.close();
            }
        }
        return respuesta;
    }
}

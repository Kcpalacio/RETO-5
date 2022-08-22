package reto4.controller;
import reto4.model.dao.*;
import reto4.model.vo.*;

import java.sql.SQLException;
import java.util.List;

public class ReportesController {
    private LideresDeProyectoDao comprasDeLiderDao;
    private ComprasHomecenterDao deudasPorProyectoDao;
    private CasaCampestreDao proyectoBancoDao;

    public ReportesController(){
        proyectoBancoDao = new CasaCampestreDao();
        deudasPorProyectoDao = new ComprasHomecenterDao();
        comprasDeLiderDao = new LideresDeProyectoDao();
    }
    public List<CasaCampestreVo> listarProyectosPorBanco() throws SQLException{
        return proyectoBancoDao.listar();
    }
    public List<ComprasHomecenterVo> listarTotalAdeudadoProyectos() throws SQLException{
        return deudasPorProyectoDao.listar();
    }
    public List<LideresDeProyectoVo> listarLideresCompras() throws SQLException{
        return comprasDeLiderDao.listar();
    }
}
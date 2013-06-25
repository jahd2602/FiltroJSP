/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.honorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import paw.honorio.modelo.Categoria;
import paw.honorio.modelo.Marca;
import paw.honorio.modelo.Producto;

/**
 *
 * @author Sistemas
 */
public class BeanDatos {

    private Connection con;
    PreparedStatement psSelectCategorias;
    PreparedStatement psSelectMarcas;
    PreparedStatement psSelect;
    PreparedStatement psSelectPorCategoria;
    PreparedStatement psSelectPorMarca;
    PreparedStatement psSelectPorCategoriaYMarca;

    public BeanDatos() throws Exception {
        con = Conexion.getInstance().getConnection();
        psSelectCategorias = con.prepareStatement("select * from APP.CATEGORIA");
        psSelectMarcas = con.prepareStatement("select * from APP.MARCA");
        psSelect = con.prepareStatement("select app.CATEGORIA.NOMBRE, app.MARCA.NOMBRE, modelo, precio from APP.PRODUCTO join APP.CATEGORIA on app.PRODUCTO.IDCATEGORIA = app.CATEGORIA.ID join App.MARCA on app.PRODUCTO.IDMARCA = app.MARCA.ID");
        psSelectPorCategoria = con.prepareStatement("select app.CATEGORIA.NOMBRE, app.MARCA.NOMBRE, modelo, precio from APP.PRODUCTO join APP.CATEGORIA on app.PRODUCTO.IDCATEGORIA = app.CATEGORIA.ID join App.MARCA on app.PRODUCTO.IDMARCA = app.MARCA.ID where app.CATEGORIA.id =?");
        psSelectPorMarca = con.prepareStatement("select app.CATEGORIA.NOMBRE, app.MARCA.NOMBRE, modelo, precio from APP.PRODUCTO join APP.CATEGORIA on app.PRODUCTO.IDCATEGORIA = app.CATEGORIA.ID join App.MARCA on app.PRODUCTO.IDMARCA = app.MARCA.ID where app.MARCA.id =?");
        psSelectPorCategoriaYMarca = con.prepareStatement("select app.CATEGORIA.NOMBRE, app.MARCA.NOMBRE, modelo, precio from APP.PRODUCTO join APP.CATEGORIA on app.PRODUCTO.IDCATEGORIA = app.CATEGORIA.ID join App.MARCA on app.PRODUCTO.IDMARCA = app.MARCA.ID where app.CATEGORIA.id =? and app.MARCA.id = ?");
    }

    public ArrayList<Categoria> getCategorias() throws SQLException {
        ResultSet rs = psSelectCategorias.executeQuery();
        ArrayList<Categoria> ret = new ArrayList<Categoria>();
        Categoria cat;
        while (rs.next()) {
            cat = new Categoria();
            cat.setId(rs.getInt("id"));
            cat.setNombre(rs.getString("nombre"));
            ret.add(cat);
        }
        return ret;
    }
    public ArrayList<Marca> getMarcas() throws SQLException {
        ResultSet rs = psSelectMarcas.executeQuery();
        ArrayList<Marca> ret = new ArrayList<Marca>();
        Marca marca;
        while (rs.next()) {
            marca = new Marca();
            marca.setId(rs.getInt("id"));
            marca.setNombre(rs.getString("nombre"));
            ret.add(marca);
        }
        return ret;
    }
    
    public ArrayList<Producto> getProductosSinFiltro() throws SQLException {
        ResultSet rs = psSelect.executeQuery();
        return acomodar(rs);
    }
     public ArrayList<Producto> getProductosPorCategoria(int idCategoria) throws SQLException {
         psSelectPorCategoria.setInt(1, idCategoria);
        ResultSet rs = psSelectPorCategoria.executeQuery();
        return acomodar(rs);
    }
     public ArrayList<Producto> getProductosPorMarca(int idMarca) throws SQLException {
         psSelectPorMarca.setInt(1, idMarca);
        ResultSet rs = psSelectPorMarca.executeQuery();
        return acomodar(rs);
    }
     public ArrayList<Producto> getProductosPorCategoriaYMarca(int idCategoria, int idMarca) throws SQLException {
         psSelectPorCategoriaYMarca.setInt(1, idCategoria);
         psSelectPorCategoriaYMarca.setInt(2, idMarca);
        ResultSet rs = psSelectPorCategoriaYMarca.executeQuery();
        return acomodar(rs);
    }
    public ArrayList<Producto> acomodar(ResultSet rs) throws SQLException{
        ArrayList<Producto> ret = new ArrayList<Producto>();
        Producto producto;
        while (rs.next()) {
            producto = new Producto();
            producto.setCategoria(rs.getString(1));
            producto.setMarca(rs.getString(2));
            producto.setModelo(rs.getString(3));
            producto.setPrecio(rs.getDouble(4));
            ret.add(producto);
        }
        return ret;
    }
    
}

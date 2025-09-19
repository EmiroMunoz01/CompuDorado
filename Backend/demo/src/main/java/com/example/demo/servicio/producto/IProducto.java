package com.example.demo.servicio.producto;

import java.util.List;

import com.example.demo.dto.producto.CrearProductoDTO;
import com.example.demo.dto.producto.EditarProductoDTO;
import com.example.demo.dto.producto.ProductoDTO;
import com.example.demo.dto.producto.ProductoDTOAdmin;

public interface IProducto {

    public List<ProductoDTO> listarProductos();

    public List<ProductoDTOAdmin> listarProductosAdmin();

    public ProductoDTO listarProductoPorId(Integer idProducto);

    public ProductoDTO listarProductoPorIdentificador(String identificadorProducto);

    public ProductoDTOAdmin listarProductoPorIdentificadorAdmin(String identificadorProducto);

    public ProductoDTOAdmin editarProductoPorId(EditarProductoDTO editarProductoDTO, Integer idProducto);

    public ProductoDTOAdmin editarProductoPorIdentificador(EditarProductoDTO editarProductoDTO, String identificadorProducto);

    public void eliminarProductoPorId(Integer idProducto);

    public void eliminarProductoPorIdentificador(String identificadorProducto);

    public ProductoDTO crearProductoDTO(CrearProductoDTO crearProductoDTO);

}

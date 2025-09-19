package com.example.demo.servicio.producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.producto.CrearProductoDTO;
import com.example.demo.dto.producto.EditarProductoDTO;
import com.example.demo.dto.producto.ProductoDTO;
import com.example.demo.dto.producto.ProductoDTOAdmin;
import com.example.demo.modelo.producto.Producto;
import com.example.demo.repositorio.RepositorioProducto;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoServicio implements IProducto {

    private final RepositorioProducto repositorioProducto;

    public ProductoServicio(RepositorioProducto repositorioProducto) {
        this.repositorioProducto = repositorioProducto;
    }

    private ProductoDTO convertirProductoEnDTO(Producto producto) {

        ProductoDTO dtoResponse = new ProductoDTO();

        dtoResponse.setNombre(producto.getNombre());
        dtoResponse.setDescripcion(producto.getDescripcion());
        dtoResponse.setCantidad(producto.getCantidad());
        dtoResponse.setCategoria(producto.getCategoria());
        dtoResponse.setPrecio(producto.getPrecio());

        return dtoResponse;
    }

    private ProductoDTOAdmin convertirProductoEnDTOAdmin(Producto producto) {

        ProductoDTOAdmin dtoResponse = new ProductoDTOAdmin();

        dtoResponse.setId(producto.getId());
        dtoResponse.setIdentificador(producto.getIdentificador());
        dtoResponse.setNombre(producto.getNombre());
        dtoResponse.setDescripcion(producto.getDescripcion());
        dtoResponse.setCantidad(producto.getCantidad());
        dtoResponse.setPrecio(producto.getPrecio());
        dtoResponse.setCategoria(producto.getCategoria());

        return dtoResponse;
    }

    @Override
    public ProductoDTO crearProductoDTO(CrearProductoDTO crearProductoDTO) {

        Optional<Producto> buscarProducto = repositorioProducto.findProductoByIdentificador(crearProductoDTO.getIdentificador());

        if (buscarProducto.isPresent()) {
            throw new IllegalArgumentException("El producto con el identificador: ---" + crearProductoDTO.getIdentificador()
                    + "--- ya existe en la base de datos");
        }

        Producto nuevoProducto = new Producto();

        nuevoProducto.setNombre(crearProductoDTO.getNombre());
        nuevoProducto.setPrecio(crearProductoDTO.getPrecio());
        nuevoProducto.setIdentificador(crearProductoDTO.getIdentificador());
        nuevoProducto.setCantidad(crearProductoDTO.getCantidad());
        nuevoProducto.setCategoria(crearProductoDTO.getCategoria());

        return null;
    }

    @Override
    public List<ProductoDTO> listarProductos() {

        List<Producto> productos = repositorioProducto.findAll();
        List<ProductoDTO> productosConvertidos = new ArrayList<>(productos.size());

        for (Producto p : productos) {
            productosConvertidos.add(convertirProductoEnDTO(p));
        }
        return productosConvertidos;
    }

    @Override
    public List<ProductoDTOAdmin> listarProductosAdmin() {

        List<Producto> productos = repositorioProducto.findAll();
        List<ProductoDTOAdmin> productosConvertidos = new ArrayList<>(productos.size());

        for (Producto p : productos) {
            productosConvertidos.add(convertirProductoEnDTOAdmin(p));
        }
        return productosConvertidos;
    }

    @Override
    public ProductoDTO listarProductoPorId(Integer idProducto) {

        Producto buscarProducto = repositorioProducto.findById(idProducto).orElseThrow(() -> new EntityNotFoundException("No se encontró producto con el id: " + idProducto));

        return convertirProductoEnDTO(buscarProducto);
    }

    @Override
    public ProductoDTO listarProductoPorIdentificador(String identificadorProducto) {

        Producto buscarProducto = repositorioProducto.findProductoByIdentificador(identificadorProducto).orElseThrow(
                () -> new EntityNotFoundException("No se encontró producto con el identificadorProducto: %s" + identificadorProducto));

        return convertirProductoEnDTO(buscarProducto);
    }

    @Override
    public ProductoDTOAdmin listarProductoPorIdentificadorAdmin(String identificadorProducto) {

        Producto buscarProducto = repositorioProducto.findProductoByIdentificador(identificadorProducto).orElseThrow(
                () -> new EntityNotFoundException("No se encontró producto con el identificadorProducto: %s" + identificadorProducto));

        return convertirProductoEnDTOAdmin(buscarProducto);
    }

    @Override
    public ProductoDTOAdmin editarProductoPorId(EditarProductoDTO editarProductoDTO, Integer idProducto) {
        Producto existente = repositorioProducto.findById(idProducto).orElseThrow(() -> new EntityNotFoundException("No se encontró producto con el idProducto " + idProducto));

        existente.setIdentificador(editarProductoDTO.getIdentificador());
        existente.setNombre(editarProductoDTO.getNombre());
        existente.setDescripcion(editarProductoDTO.getDescripcion());
        existente.setCantidad(editarProductoDTO.getCantidad());
        existente.setPrecio(editarProductoDTO.getPrecio());
        existente.setCategoria(editarProductoDTO.getCategoria());

        repositorioProducto.save(existente);

        return convertirProductoEnDTOAdmin(existente);
    }

    @Override
    public ProductoDTOAdmin editarProductoPorIdentificador(EditarProductoDTO editarProductoDTO,
            String identificadorProducto) {

        Producto existente = repositorioProducto.findProductoByIdentificador(identificadorProducto).orElseThrow(() -> new EntityNotFoundException("No se encontró producto con el identificador " + identificadorProducto));

        existente.setIdentificador(editarProductoDTO.getIdentificador());
        existente.setNombre(editarProductoDTO.getNombre());
        existente.setDescripcion(editarProductoDTO.getDescripcion());
        existente.setCantidad(editarProductoDTO.getCantidad());
        existente.setPrecio(editarProductoDTO.getPrecio());
        existente.setCategoria(editarProductoDTO.getCategoria());

        repositorioProducto.save(existente);

        return convertirProductoEnDTOAdmin(existente);
    }

    @Override
    public void eliminarProductoPorId(Integer idProducto) {

        Producto existente = repositorioProducto.findById(idProducto).orElseThrow(() -> new EntityNotFoundException("No se encontró producto con el id " + idProducto));

        repositorioProducto.delete(existente);
    }

    @Override
    public void eliminarProductoPorIdentificador(String identificadorProducto) {

        Producto existente = repositorioProducto.findProductoByIdentificador(identificadorProducto).orElseThrow(
                () -> new EntityNotFoundException("No se encontró producto con el identificador: %s" + identificadorProducto));

        repositorioProducto.delete(existente);

    }

}

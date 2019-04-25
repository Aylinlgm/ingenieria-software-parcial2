package gt.edu.umg.ingenieria.sistemas.parcial2.inventario.controller;

import gt.edu.umg.ingenieria.sistemas.core.parcial2.core.model.ProductoEntity;
import gt.edu.umg.ingenieria.sistemas.parcial2.inventario.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    
    @GetMapping("/buscarTodos")
    public List<ProductoEntity> buscarTodos() {   
        return this.productoService.buscarTodos();
    }
    
    
    @PostMapping("/registrarProducto")
    public String create(@RequestBody(required = true) ProductoEntity newProducto){
        
        return this.productoService.registrar(newProducto);
    }
    ///actualizarStock/{id}/{opcion}/{stock}
    @PutMapping("/actualizarStock/{id}/{operacion}/{stock}")
    public ProductoEntity updateStock(@PathVariable(required = true) Long id, @PathVariable(required = true) String operacion, @PathVariable(required = true) Integer stock) {
        return this.productoService.actualizarStock(id, operacion, stock);
}
  
}

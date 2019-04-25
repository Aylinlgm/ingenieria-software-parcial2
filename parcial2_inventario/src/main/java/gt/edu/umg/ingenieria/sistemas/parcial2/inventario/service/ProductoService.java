package gt.edu.umg.ingenieria.sistemas.parcial2.inventario.service;

import gt.edu.umg.ingenieria.sistemas.core.parcial2.core.model.ProductoEntity;
import gt.edu.umg.ingenieria.sistemas.parcial2.inventario.dao.ProductoRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    
    public List<ProductoEntity> buscarTodos() {
        
        ArrayList<ProductoEntity> productos = new ArrayList<ProductoEntity>();
        
        productos = (ArrayList<ProductoEntity>) this.productoRepository.findAll();      
        Collections.sort(productos, new Comparator<ProductoEntity>() {
            public int compare(ProductoEntity obj1, ProductoEntity obj2) {
                return obj1.getName().compareTo(obj2.getName());
            }
        });
        
        
     return productos;   
    }
    
     public ProductoEntity actualizarStock(Long id, String opcion,Integer stock) {
       
        ProductoEntity producto = this.productoRepository.findById(id).get();
        
        
        Integer stockActual=0;
        Integer Nuevostock=0;
        
        if (opcion.equals("incrementar")) 
        {
            stockActual = producto.getStock();
            Nuevostock = stockActual + stock;
            producto.setStock(Nuevostock);
        } 
        else if (opcion.equals("decrementar"))
        {
            stockActual = producto.getStock();
            Nuevostock = stockActual - stock;
            producto.setStock(Nuevostock);
        }
         
        return producto;
     }
        
        


   // public ProductoEntity  registrar(ProductoEntity producto){
     public String  registrar(ProductoEntity producto){   
        
         String cadena = "";
        String oldName = producto.getName();
        String [] split = oldName.split(" ");
        String newName = "";

        for (int j = 0; j < split.length; j++) {
            if(j==0)
                split[j] = split[j].substring(0, 1).toUpperCase() + split[j].substring(1).toLowerCase();
            else
                split[j] = split[j].substring(0, 1).toLowerCase()+ split[j].substring(1).toLowerCase();
            newName = newName + split[j] + " ";
        }
        
           producto.setName(newName);        
        this.productoRepository.save(producto).toString();
            
            cadena = "id = " + producto.getId() + "\nName = " + producto.getName()+ "\nDescripcion = " + producto.getDescription() +
                     "\nStock = " + producto.getStock() + "\nPrecio Unitario = " + producto.getUnit_price();
        
        return cadena;
          //  return this.productoRepository.save(producto);
        /*
        return this.productoRepository.save(producto);
        */
 
    }

    
}

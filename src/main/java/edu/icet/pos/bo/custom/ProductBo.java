package edu.icet.pos.bo.custom;

import edu.icet.pos.bo.SuperBo;
import edu.icet.pos.dto.Product;

public interface ProductBo extends SuperBo {
    boolean saveProduct(Product dto);
    boolean updateProduct(String id);
    boolean deleteProduct(String id);
}
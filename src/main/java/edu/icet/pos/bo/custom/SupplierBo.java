package edu.icet.pos.bo.custom;

import edu.icet.pos.bo.SuperBo;
import edu.icet.pos.dto.Supplier;

public interface SupplierBo extends SuperBo {
    boolean saveSupplier(Supplier dto);
    boolean updateSupplier(String id);
    boolean deleteSupplier(String id);
}
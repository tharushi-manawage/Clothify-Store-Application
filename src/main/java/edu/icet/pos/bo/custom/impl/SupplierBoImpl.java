package edu.icet.pos.bo.custom.impl;

import edu.icet.pos.bo.custom.SupplierBo;
import edu.icet.pos.dao.DaoFactory;
import edu.icet.pos.dao.custom.SupplierDao;
import edu.icet.pos.dto.Supplier;
import edu.icet.pos.entity.SupplierEntity;
import edu.icet.pos.util.DaoType;
import org.modelmapper.ModelMapper;

public class SupplierBoImpl implements SupplierBo {
    private SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);

    @Override
    public boolean saveSupplier(Supplier dto) {
        return supplierDao.save(new ModelMapper().map(dto, SupplierEntity.class));
    }

    @Override
    public boolean updateSupplier(String id) {
        return false;
    }

    @Override
    public boolean deleteSupplier(String id) {
        return false;
    }
}

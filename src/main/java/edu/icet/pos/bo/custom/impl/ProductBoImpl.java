package edu.icet.pos.bo.custom.impl;

import edu.icet.pos.bo.custom.ProductBo;
import edu.icet.pos.dao.DaoFactory;
import edu.icet.pos.dao.custom.ProductDao;
import edu.icet.pos.dto.Product;
import edu.icet.pos.entity.ProductEntity;
import edu.icet.pos.util.DaoType;
import org.modelmapper.ModelMapper;

public class ProductBoImpl implements ProductBo {
    private ProductDao productDao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);

    @Override
    public boolean saveProduct(Product dto) {
        return productDao.save(new ModelMapper().map(dto, ProductEntity.class));
    }

    @Override
    public boolean updateProduct(String id) {
        return false;
    }

    @Override
    public boolean deleteProduct(String id) {
        return false;
    }
}

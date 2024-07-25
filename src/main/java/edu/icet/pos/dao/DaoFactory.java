package edu.icet.pos.dao;

import edu.icet.pos.dao.custom.impl.*;
import edu.icet.pos.util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        return instance != null ? instance : (instance = new DaoFactory());
    }

    public <T extends SuperDao>T getDao(DaoType type) {
        switch (type) {
            case USER: return (T) new UserDaoImpl();
            case EMPLOYEE: return (T) new EmployeeDaoImpl();
            case SUPPLIER: return (T) new SupplierDaoImpl();
            case PRODUCT: return (T) new ProductDaoImpl();
            case ORDER: return (T) new OrderDaoImpl();
        }
        return null;
    }
}
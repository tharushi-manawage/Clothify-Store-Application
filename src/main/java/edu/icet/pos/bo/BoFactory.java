package edu.icet.pos.bo;

import edu.icet.pos.bo.custom.impl.*;
import edu.icet.pos.util.BoType;

public class BoFactory {
    private static BoFactory instance;

    private BoFactory() {}

    public static BoFactory getInstance(){
        return instance != null ? instance : (instance = new BoFactory());
    }

    public <T extends SuperBo>T getBo(BoType type) {
        switch (type) {
            case USER: return (T) new UserBoImpl();
            case EMPLOYEE: return (T) new EmployeeBoImpl();
            case SUPPLIER: return (T) new SupplierBoImpl();
            case PRODUCT: return (T) new ProductBoImpl();
            case ORDER: return (T) new OrderBoImpl();
        }
        return null;
    }
}
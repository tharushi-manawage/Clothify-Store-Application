package edu.icet.pos.dao;

public class DaoFactory {
    private static DaoFactory instance;

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        return instance != null ? instance : (instance = new DaoFactory());
    }

    public <T extends SuperDao>T getDao(DaoType type) {
        switch(type) {
            case USER : return (T) new UserDaoImpl();
        }
        return null;
    }
}
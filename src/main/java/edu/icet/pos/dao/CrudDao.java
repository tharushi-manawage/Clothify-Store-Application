package edu.icet.pos.dao;

public interface CrudDao <T> extends SuperDao {
    boolean save(T dto);
    boolean update(String id);
    boolean delete(String id);
}
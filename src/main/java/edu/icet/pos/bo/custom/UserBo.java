package edu.icet.pos.bo.custom;

public interface UserBo extends SuperBo {
    boolean saveUser(User dto);
    boolean deleteById(String id);
}
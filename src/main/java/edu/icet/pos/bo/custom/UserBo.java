package edu.icet.pos.bo.custom;

import edu.icet.pos.bo.SuperBo;
import edu.icet.pos.dto.User;

public interface UserBo extends SuperBo {
    boolean saveUser(User dto);
    boolean updateUser(String id);
    boolean deleteUser(String id);
}
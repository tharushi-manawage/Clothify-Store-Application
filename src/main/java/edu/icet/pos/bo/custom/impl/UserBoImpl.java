package edu.icet.pos.bo.custom.impl;

import org.modelmapper.ModelMapper;

public class UserBoImpl implements UserBo {
    private UserDao userDao = DaoFactory.getInstance().getDao(DaoType.USER);

    @Override
    public boolean saveUser(User dto) {
        return userDao.save(new ModelMapper().map(dto, UserEntity.class));
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}
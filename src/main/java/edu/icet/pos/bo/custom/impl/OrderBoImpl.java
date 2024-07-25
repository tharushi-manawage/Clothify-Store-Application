package edu.icet.pos.bo.custom.impl;

import edu.icet.pos.bo.custom.OrderBo;
import edu.icet.pos.dao.DaoFactory;
import edu.icet.pos.dao.custom.OrderDao;
import edu.icet.pos.dto.Order;
import edu.icet.pos.entity.OrderEntity;
import edu.icet.pos.util.DaoType;
import org.modelmapper.ModelMapper;

public class OrderBoImpl implements OrderBo {
    private OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);

    @Override
    public boolean saveOrder(Order dto) {
        return orderDao.save(new ModelMapper().map(dto, OrderEntity.class));
    }

    @Override
    public boolean updateOrder(String id) {
        return false;
    }

    @Override
    public boolean deleteOrder(String id) {
        return false;
    }
}

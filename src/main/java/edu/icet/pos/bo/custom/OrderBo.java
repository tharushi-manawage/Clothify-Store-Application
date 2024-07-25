package edu.icet.pos.bo.custom;

import edu.icet.pos.bo.SuperBo;
import edu.icet.pos.dto.Order;

public interface OrderBo extends SuperBo {
    boolean saveOrder(Order dto);
    boolean updateOrder(String id);
    boolean deleteOrder(String id);
}
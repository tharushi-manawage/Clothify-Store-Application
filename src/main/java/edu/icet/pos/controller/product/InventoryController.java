package edu.icet.pos.controller.product;

import edu.icet.pos.dto.OrderDetail;
import edu.icet.pos.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class InventoryController {
    private static InventoryController instance;

    private InventoryController() {}

    public static InventoryController getInstance() {
        if (instance == null) {
            return instance = new InventoryController();
        }
        return instance;
    }

    public boolean updateStock(List<OrderDetail> orderDetailList) {
        boolean isUpdate = false;
        for (OrderDetail orderDetail : orderDetailList) {
            isUpdate = updateStock((List<OrderDetail>) orderDetail);
        }
        if (isUpdate) {
            return true;
        }
        return false;
    }

    public boolean updateStock(OrderDetail orderDetail) {
        try {
            Object isUpdate = CrudUtil.execute(
                    "UPDATE product SET QtyOnHand = QtyOnHand - ? WHERE ProductID = ?",
                    orderDetail.getQty(),
                    orderDetail.getProductID());
            return (Boolean) isUpdate;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
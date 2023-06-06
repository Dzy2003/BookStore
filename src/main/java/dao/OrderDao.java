package dao;

import entity.Order;
import entity.OrderItem;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    /**
     * 增加订单项
     * @param order
     * @return
     */
    Long insertOrder(Order order) throws SQLException;


    /**
     * 增加订单数据
     * @param orderItem
     * @return
     */
    Integer insertOrderItem(OrderItem orderItem) throws SQLException;

    /**
     * 根据订单id查询
     * @param id
     * @return
     */
    Order selectOrderById(Integer id) throws SQLException;

    /**
     * 根据订单状态查询所有的订单项（是否发货）
     * @param status 订单状态
     * @return
     */
    List<Order> selectAll(Boolean status) throws SQLException;

    /**
     * 重载方法(根据用户uid查询)
     * @param uid 用户id
     * @return
     */
    List<Order> selectAll(Integer uid) throws SQLException;

    /**
     * 重载方法（根据用户uid和状态查询）
     * @param status 订单状态
     * @param uid 用户id
     * @return
     */
    List<Order> selectAll(Boolean status, Integer uid) throws SQLException;

    /**
     * 根据订单号查询该订单的所有订单数据
     * @param oids
     * @return
     */
    List<OrderItem> getOrderItemsByOids(Integer[] oids) throws SQLException;

    /**
     * 更改订单状态（未发货 -> 发货）
     * @param order
     */
    void updateStatus(Order order) throws SQLException;
}

package service;

import entity.Order;
import vo.CartVo;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    /**
     * 根据uid和购物车的id来创建订单
     * @param uid
     * @param cids
     * @throws SQLException
     */
    public void CreateOrder(Integer uid, Integer[] cids) throws SQLException;

    /**
     * 根据订单状态查询订单
     * @param status
     * @return
     */
    List<Order> listOrder(Boolean status) throws SQLException;

    /**
     * 查询订单下的订单项
     * @param oid
     * @return
     */
    List<CartVo> findOrderItemByOid(Integer oid) throws SQLException;

    /**
     * 把订单置为发货状态
     * @param oid
     */
    void confirmOrder(Integer oid) throws SQLException;

    /**
     * 获取某个用户的订单信息
     * @param uid
     * @return
     */
     List<Order> clientListOrder(Integer uid) throws SQLException;

}


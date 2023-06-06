package dao.impl;

import dao.OrderDao;
import entity.Order;
import entity.OrderItem;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JdbcUtils;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public Long insertOrder(Order order) throws SQLException {
        Connection connection = (Connection) JdbcUtils.getConnection();
        String sql = "insert into orders(ordertime,price,state,uid) values(?,?,?,?)";
        Object params[] = {order.getOrderTime(), order.getPrice(), order.isState(), order.getUid()};
        runner.update(connection,sql, params);
        BigInteger id = BigInteger.ZERO ;
        id = (BigInteger) runner.query(connection, "SELECT LAST_INSERT_ID()", new ScalarHandler(1));  //获取新增记录的自增主键
        return id.longValue();
    }

    @Override
    public Integer insertOrderItem(OrderItem orderItem) throws SQLException {
        String sql = "insert into orderitem(quantity, price, oid, bid) values(?,?,?,?)";
        Object params[] = {orderItem.getQuantity(), orderItem.getPrice(), orderItem.getOid(), orderItem.getBid()};
        return runner.update(sql, params);
    }

    @Override
    public Order selectOrderById(Integer id) throws SQLException {
        String sql = "select * from orders where id = ?";
        return runner.query(sql, new BeanHandler<Order>(Order.class) ,id);
    }

    @Override
    public List<Order> selectAll(Boolean status) throws SQLException {
        String sql = "select * from orders where state= ?";
        return runner.query(sql, new BeanListHandler<Order>(Order.class) ,status);
    }

    @Override
    public List<Order> selectAll(Integer uid) throws SQLException {
        String sql = "select * from orders where uid= ?";
        return runner.query(sql, new BeanListHandler<Order>(Order.class) ,uid);
    }

    @Override
    public List<Order> selectAll(Boolean status, Integer uid) throws SQLException {
        String sql = "select * from orders where uid= ? AND state = ?";
        return runner.query(sql, new BeanListHandler<Order>(Order.class),uid,status);
    }

    @Override
    public List<OrderItem> getOrderItemsByOids(Integer[] oids) throws SQLException {
        String s = "";
        for (int i = 0; i < oids.length; i++) {
            s += oids[i];
            s = i == oids.length - 1 ? s : s +",";
        }
        String sql = "select * from orderitem where oid IN (" + s + ")";
        return runner.query(sql, new BeanListHandler<OrderItem>(OrderItem.class));
    }

    @Override
    public void updateStatus(Order order) throws SQLException {
        String sql = "update orders set state=? where id=?";
        Object params[] = {order.isState(), order.getId()};
        runner.update(sql, params);
    }
}

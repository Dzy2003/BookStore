package dao.impl;

import dao.CartDao;
import entity.Cart;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class CartDaoImpl implements CartDao {
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    @Override
    public List<Cart> selectCartByUid(Integer uid) throws SQLException {
        String sql = "select * from cart where uid = ?";
        return runner.query(sql, new BeanListHandler<Cart>(Cart.class),uid);
    }

    @Override
    public Integer addCart(Cart cart) throws SQLException {
        String sql = "insert into cart(uid,bid,price,num) values(?,?,?,?)";
        Object params[] = {cart.getUid(), cart.getBid(), cart.getPrice(), cart.getNum()};
        return runner.update(sql, params);
    }

    @Override
    public Cart selectCartByUidAndBid(Integer uid, Integer bid) throws SQLException {
        String sql = "select * from cart where uid = ? AND bid = ?";
        Object params[] = {uid, bid};
        return runner.query(sql,new BeanHandler<Cart>(Cart.class),params);
    }

    @Override
    public Integer updateCartNumById(Integer cid, Integer num) throws SQLException {
        String sql = "update cart set num = ? where cid = ?";
        return runner.update(sql,num,cid);
    }

    @Override
    public List<Cart> selectCartVoByCids(Integer[] cids) throws SQLException {
        String s = "";
        for (int i = 0; i < cids.length; i++) {
            s += cids[i];
            s = i == cids.length - 1 ? s : s +",";
        }
        String sql = "select * from cart where cart.cid IN (" + s + ")";
        return runner.query(sql, new BeanListHandler<Cart>(Cart.class));
    }

    @Override
    public Integer deleteCartById(Integer id) throws SQLException {
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "delete from cart where cid = ?";
        return runner.update(sql,id);
    }
}

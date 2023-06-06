package dao;

import entity.Cart;

import java.sql.SQLException;
import java.util.List;

public interface CartDao {
    /**
     * 根据uid查询所有购物车数据
     * @param uid
     * @return
     * @throws SQLException
     */
    List<Cart> selectCartByUid(Integer uid) throws SQLException;

    /**
     * 添加数据
     * @param cart
     * @return
     * @throws SQLException
     */
    Integer addCart(Cart cart) throws SQLException;

    /**
     * 根据uid和bid查询所有购物车数据（检查该用户是否重复将一本书加入购物车）
     * @param uid
     * @param bid
     * @return
     * @throws SQLException
     */
    Cart selectCartByUidAndBid(Integer uid,Integer bid) throws SQLException;

    /**
     * 更新该商品购物车数量
     * @param cid
     * @return
     * @throws SQLException
     */
    Integer updateCartNumById(Integer cid, Integer num)throws SQLException;

    /**
     * 通过id数组查询所有数据
     * @param cids
     * @return
     */
    List<Cart> selectCartVoByCids(Integer[] cids) throws SQLException;

    /**
     * 根据id删除
     * @param id
     * @return
     * @throws SQLException
     */

    Integer deleteCartById(Integer id) throws SQLException;
}

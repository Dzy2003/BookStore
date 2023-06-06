package service;

import vo.CartVo;

import java.sql.SQLException;
import java.util.List;

public interface CartService {
    /**
     * 根据商品和用户数据插入购物车
     * @param uid 用户id
     * @param bid 图书id
     * @throws SQLException
     */
    void InsertCart(Integer uid, Integer bid) throws SQLException;

    /**
     * 列出用户的所有购物车项
     * @param uid 用户id
     * @return 购物车前端视图
     * @throws SQLException
     */
    List<CartVo> listCartBook(Integer uid) throws SQLException;

    /**
     * 删除
     * @param id
     */
    void delete(Integer id) throws SQLException;
}

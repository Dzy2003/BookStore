package service.Impl;

import dao.BookDao;
import dao.CartDao;
import dao.impl.BookDaoImpl;
import dao.impl.CartDaoImpl;
import entity.Book;
import entity.Cart;
import service.CartService;
import vo.CartVo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {
    BookDao bookDao = new BookDaoImpl();
    CartDao cartDao = new CartDaoImpl();
    @Override
    public void InsertCart(Integer uid, Integer bid) throws SQLException {
        Cart res = cartDao.selectCartByUidAndBid(uid, bid);
        //说明该用户是第一次将该书加入购物车
        if(res == null) {
            //封装数据加入购物车
            Cart cart = new Cart();
            cart.setNum(1);
            cart.setPrice(bookDao.selectById(bid).getPrice());
            cart.setUid(uid);
            cart.setBid(bid);
            cartDao.addCart(cart);
        //用户重复添加书，只需更新数量即可
        }else {
            cartDao.updateCartNumById(res.getCid(), res.getNum()+1);
        }
    }

    @Override
    public List<CartVo> listCartBook(Integer uid) throws SQLException {
        //查询用户的所有购物车
        List<Cart> cartList = cartDao.selectCartByUid(uid);
        List<CartVo> cartVoList = new ArrayList<>();
        //将所有购物车
        for (Cart cart : cartList) {
            CartVo vo = new CartVo();
            Book book = bookDao.selectById(cart.getBid());
            vo.setCid(cart.getCid());
            vo.setBid(cart.getBid());
            vo.setUid(cart.getUid());
            vo.setPrice(book.getPrice());
            vo.setAuthor(book.getAuthor());
            vo.setName(book.getName());
            vo.setNum(cart.getNum());
            vo.setRealPrice(cart.getPrice() * cart.getNum());
            cartVoList.add(vo);
        }
        return cartVoList;
    }

    @Override
    public void delete(Integer id) throws SQLException {
        cartDao.deleteCartById(id);
    }
}

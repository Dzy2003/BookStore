package service.Impl;

import dao.BookDao;
import dao.CartDao;
import dao.OrderDao;
import dao.impl.BookDaoImpl;
import dao.impl.CartDaoImpl;
import dao.impl.OrderDaoImpl;
import entity.Book;
import entity.Cart;
import entity.Order;
import entity.OrderItem;
import service.OrderService;
import vo.CartVo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    CartDao cartDao = new CartDaoImpl();
    BookDao bookDao = new BookDaoImpl();
    @Override
    public void CreateOrder(Integer uid, Integer[] cids) throws SQLException {
        List<Cart> cartList = cartDao.selectCartVoByCids(cids);
        List<CartVo> cartVoList = getCartVoList(cartList);
        //封装order
        Order order = new Order();
        order.setOrderTime(new Date());
        order.setUid(uid);
        Double totalPrice = 0.0;
        //获取总价
        for (CartVo cartVo : cartVoList) {
            totalPrice += cartVo.getRealPrice();
        }
        //插入order
        order.setPrice(totalPrice);

        //获取到id
        Long id = orderDao.insertOrder(order);

        //封装OrderItem
        for (CartVo cartVo : cartVoList) {
            OrderItem item = new OrderItem();
            item.setQuantity(cartVo.getNum());
            item.setOid(id);
            item.setBid(cartVo.getBid());
            item.setPrice(cartVo.getPrice());
            orderDao.insertOrderItem(item);
        }
    }

    @Override
    public List<Order> listOrder(Boolean status) throws SQLException {
        List<Order> orders = orderDao.selectAll(status);
        return orders;
    }

    @Override
    public List<CartVo> findOrderItemByOid(Integer oid) throws SQLException {
        List<OrderItem> itemsByOids = orderDao.getOrderItemsByOids(new Integer[]{oid});
        List<CartVo> cartVoList = new ArrayList<>();
        //订单项转换为vo视图给前端
        for (OrderItem item : itemsByOids) {
            CartVo vo = new CartVo();
            Book book = bookDao.selectById(item.getBid());
            vo.setBid(item.getBid());
            vo.setPrice(book.getPrice());
            vo.setAuthor(book.getAuthor());
            vo.setName(book.getName());
            vo.setNum(item.getQuantity());
            vo.setRealPrice((long) (item.getPrice() * item.getQuantity()));
            cartVoList.add(vo);
        }
        return cartVoList;
    }

    @Override
    public void confirmOrder(Integer oid) throws SQLException {
        Order order = orderDao.selectOrderById(oid);
        order.setState(true);
        orderDao.updateStatus(order);
    }

    @Override
    public List<Order> clientListOrder(Integer uid) throws SQLException {
        List<Order> orders = orderDao.selectAll(uid);
        return orders;
    }

    @Override
    public void delete(Integer id) throws SQLException {
        orderDao.deleteOrderById(id);
    }


    /**
     * 通过购物车封装cartVoList
     * @param cartList
     * @return
     * @throws SQLException
     */
    public List<CartVo> getCartVoList(List<Cart> cartList) throws SQLException {
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
}

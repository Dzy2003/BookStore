import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import entity.Order;
import entity.OrderItem;
import org.junit.jupiter.api.Test;
import service.Impl.OrderServiceImpl;
import service.OrderService;
import vo.CartVo;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderTest {
    OrderDao dao = new OrderDaoImpl();
    OrderService service = new OrderServiceImpl();
    @Test
    public void testInsert() throws Exception {
        Order order = new Order();
        order.setOrderTime(new Date());
        order.setState(false);
        order.setPrice(23);
        order.setUid(1);
        dao.insertOrder(order);

    }
    @Test
    public void testInsertOrderItem() throws Exception {
        OrderItem item = new OrderItem();
        item.setBid(1);
        item.setPrice(24);
        item.setQuantity(3);
        Integer integer = dao.insertOrderItem(item);
        System.out.println(integer);
    }

    @Test
    public void testSelectById() throws SQLException {
        Order order = dao.selectOrderById(1);
        System.out.println(order);
    }
    @Test
    public void testSelectAll1() throws SQLException {
        List<Order> orders = dao.selectAll(1);
        System.out.println(orders);
    }
    @Test
    public void testSelectAll2() throws SQLException {
        List<Order> orders = dao.selectAll(true);
        System.out.println(orders);
    }
    @Test
    public void testSelectAll3() throws SQLException {
        List<Order> orders = dao.selectAll(false,1);
        System.out.println(orders);
    }

    @Test
    public void testSelectItem() throws SQLException {
        Integer[] oids = {1};
        List<OrderItem> orderItemsByOids = dao.getOrderItemsByOids(oids);
        System.out.println(orderItemsByOids);
    }

    @Test
    public void CreateOrder() throws SQLException {
        List<CartVo> orderItemByOid = service.findOrderItemByOid(28);
        for (CartVo cartVo : orderItemByOid) {
            System.out.println(cartVo);
        }
    }


}

import dao.CartDao;
import dao.impl.CartDaoImpl;
import entity.Cart;
import org.junit.jupiter.api.Test;
import service.CartService;
import service.Impl.CartServiceImpl;
import vo.CartVo;

import java.util.List;

public class CartTest {
    CartDao dao = new CartDaoImpl();
    CartService service = new CartServiceImpl();

    @Test
    public void testAdd() throws Exception {
        Cart cart = new Cart();
        cart.setUid(1);
        cart.setBid(1);
        cart.setPrice(32L);
        cart.setNum(2);
        System.out.println(dao.addCart(cart));
    }
    @Test
    public void testSelect() throws Exception {
        System.out.println(dao.selectCartByUid(1));
    }
    @Test
    public void testSelectByUidAndBid() throws Exception {
        System.out.println(dao.selectCartByUidAndBid(1,24));
    }
    @Test
    public void testUpdateByCid() throws Exception {
        System.out.println(dao.updateCartNumById(1,24));
    }
    @Test
    public void testAddCart() throws Exception {
        service.InsertCart(1,2);
    }
    @Test
    public void testGetVo() throws Exception {
        List<CartVo> cartVoList = service.listCartBook(1);
        System.out.println(cartVoList);
    }


}

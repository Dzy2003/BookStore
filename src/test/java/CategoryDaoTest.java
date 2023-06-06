import dao.CategoryDao;
import dao.impl.CategoryDaoImpl;
import entity.Category;
import org.junit.jupiter.api.Test;

class CategoryDaoTest {
    CategoryDao dao = new CategoryDaoImpl();
    @Test
    public void testInsert() throws Exception {
        Category category = new Category();
        category.setName("外国文学");
        category.setDescription("国外经典小说");
        dao.insertCategory(category);
    }
    @Test
    public void testFind() throws Exception {
        Category category = dao.find(1);
        System.out.println(category);
    }
    @Test
    public void testGetAll() throws Exception {
        System.out.println(dao.getAll());
    }
    @Test
    public void testFindByName() throws Exception {
        Category category = dao.getByName("外国文学");
        System.out.println(category);
    }

}
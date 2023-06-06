package dao;

import entity.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {
    /**
     * 插入图书分类
     * @param category 分类的实体类
     * @return 插入的行数
     */
    Integer insertCategory(Category category) throws SQLException;

    /**
     * 根据id查找分裂
     * @param id
     * @return
     */
    Category find(Integer id) throws SQLException;

    /**
     * 获取所有图书分类
     * @return
     */
    List<Category> getAll() throws SQLException;

    Category getByName(String name) throws SQLException;
}

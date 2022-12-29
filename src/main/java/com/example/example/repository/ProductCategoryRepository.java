package com.example.example.repository;
import com.example.example.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

/*
   default void insertFakeData() throws SQLException {
        int id=3;
        String name="Postres";
        PreparedStatement stmt=null;



        try{
            Connection con=null;
            con= DriverManager.getConnection("jdbc:mysql://localhost:3307/examplepanycrud","root","Yatequiceno20022");
            stmt = con.prepareStatement("INSERT INTO product_category VALUES (?,?)");
            stmt.setInt(1,3);
            stmt.setString(2,"Postresitos");

            stmt.executeUpdate();
            con.close();
        }catch(NullPointerException e) {
            System.out.println(e.getMessage());
        }


    }

*/


    /*
    @Modifying
    @Transactional
    @Query(value = "  INSERT INTO product_category (id,category) VALUES(3,'Panesitos'); ",nativeQuery = true)
    void insertData();

*/
/*

@Modifying
@Query("update User u set u.status = :status where u.name = :name")
int updateUserSetStatusForName(@Param("status") Integer status,
  @Param("name") String name);
 */

    @Modifying
    @Transactional
    @Query(value = "  INSERT INTO product_category (id,category) VALUES(?1,?2); ",nativeQuery = true)
    void insertData(Integer id,String category);


    Optional<ProductCategory> findByCategory(String category);


/* @Query with parameters
    @Transactional
    @Query(value = " SELECT c.category FROM product_category c where c.category='Panes' ",nativeQuery = true)
    Optional<String>getCategory();

*/
/*
Transaction transaction = session.beginTransaction();
....update or save or delete here
transaction.commit();
 */

    /*
    @Modifying
    @Transactional
    @Query(value = "  delete ProductCategory  where ProductCategory.category ='Panes' ")
    void insertData(Integer id,String category);

*/


    /*
    @Query(value = "  SELECT category from ProductCategory where category ='Panes' ")
   Optional<String> getCategory();


     */

    /*
    @Query(value = "  SELECT c.category  from ProductCategory c  where c.category ='Panes' ")
    Optional<String> getCategory();


     */



}

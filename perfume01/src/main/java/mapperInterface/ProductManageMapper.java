package mapperInterface;

import com.example.perfume01.criteria.SearchCriteria;
import com.example.perfume01.dto.ProductDTO;

import java.util.List;

public interface ProductManageMapper {

    List<ProductDTO> selectList();

    ProductDTO selectOne(ProductDTO dto);
    int selectStock(int product_no);
    int insertProduct(ProductDTO dto);

    int edit(ProductDTO dto);

    int deleteProduct(ProductDTO dto);

    int deleteEachProduct(ProductDTO dto);

    // === 관리자 상품 검색 ===

    List<ProductDTO> searchList(SearchCriteria criteria);

    int searchTotalCount(SearchCriteria criteria);
}

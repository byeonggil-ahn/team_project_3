package mapperInterface;

import com.example.perfume01.criteria.SearchCriteria;
import com.example.perfume01.dto.ProductDTO;
//import com.example.perfume01.dto.ProductTagDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductTagMapper {

    ProductDTO selectOne(ProductDTO dto);
    int insertTag(ProductDTO dto);
    int editTag(ProductDTO dto);
    int deleteTag(ProductDTO dto);


}

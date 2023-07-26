package mapperInterface;

import com.example.perfume01.dto.CartDTO;
import com.example.perfume01.dto.CartProductInfoDTO;

import java.util.List;

public interface CartMapper {

    int cartInsert(CartDTO dto);

    CartDTO selectOne(CartDTO dto);

    List<CartDTO> cartList(String member_id);

//    int cartChangeCount(CartDTO dto);
    int cartChangeCount(int product_count, int product_no, String member_id);

    int cartDeleteItem(CartDTO dto);

    int cartDeleteAll(CartDTO dto);

    // 이미지 조회, 총 상품 갯수인데 아마 안될 가능성이 높음
    int cartImg(CartProductInfoDTO dto);

    // 장바구니의 총 상품 갯수 (아마도 고쳐야 할 것임)
    int cartCnt(String member_id);

    //List<CartProductInfoDTO> cartInfoList(String member_id);

}

package mapperInterface;

import com.example.perfume01.dto.CartProductInfoDTO;

import java.util.List;

public interface CartProductInfo {

    List<CartProductInfoDTO> cartInfoList(String member_id);

}

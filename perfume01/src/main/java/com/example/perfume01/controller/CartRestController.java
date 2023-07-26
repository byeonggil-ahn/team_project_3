package com.example.perfume01.controller;

import com.example.perfume01.dao.CartDAO;
import com.example.perfume01.dao.CartProductInfoDAO;
import com.example.perfume01.dao.ProductDAO;
import com.example.perfume01.dto.CartDTO;
import com.example.perfume01.dto.CartProductInfoDTO;
import com.example.perfume01.dto.ProductDTO;
import com.example.perfume01.service.CartProductInfoService;
import com.example.perfume01.service.CartService;
import com.example.perfume01.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@CrossOrigin(origins = {"http://127.0.0.1:3030"})
@RestController @Log4j2
@RequestMapping("/cart")
public class CartRestController {

    @Autowired
    CartService service;

    @Autowired
    ProductService productService;

    @Autowired
    CartProductInfoService infoService;

    @Autowired
    ProductDAO productDAO;


    @GetMapping("/list")
    public List<CartDTO> cartList(@RequestParam("member_id") String member_id) {
        List<CartDTO> rtn = service.cartList(member_id);
        return rtn;
    }

    // 장바구니 페이지 (list)
    @GetMapping("/main")
    public ResponseEntity<List<CartDTO>> mainList(@RequestParam("member_id") String member_id) {
        System.out.println("프론트에서 받아온 멤버 아이디 : " + member_id);

        List<CartDTO> itemInfo = service.cartList(member_id);
        System.out.println("아이템인포 " + itemInfo); //테스트용
        int cartCnt = service.cartCnt(member_id);
        int isEmpty = itemInfo.size();

        return ResponseEntity.ok(itemInfo);
    }


    // 장바구니에 상품 담기
    @PostMapping("/insert")
    public ResponseEntity<String> insert(
            @RequestBody CartDTO cartDTO
    ) {
        System.out.println("인서트 : " + cartDTO);

        boolean checked = service.selectOne(cartDTO) == null;
        System.out.println(checked);

        // 담으려는 수량
        int product_count = cartDTO.getProduct_count();
        // 재고 수량
        int product_stock = productService.selectStock(cartDTO.getProduct_no());

        // error 1,2,3 페이지를 만들어야 할 필요성이 있음. 없으면 그냥 메세지로 처리
        if (product_count <= product_stock && checked && product_count <= 10) {
            service.cartInsert(cartDTO);
            return ResponseEntity.ok("success");
        } else if (product_count > 10) {
            return ResponseEntity.badRequest().body("error3");
        } else if (!checked) {
            return ResponseEntity.badRequest().body("error1");
        } else {
            return ResponseEntity.badRequest().body("error2");
        }
    }


    // 장바구니에서 상품 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<?> cartDeleteItem(
                         @RequestParam int product_no, @RequestParam String member_id,
                         @ModelAttribute CartDTO cartDTO) {
        cartDTO.setMember_id(member_id);
        cartDTO.setProduct_no(product_no);
        service.cartDeleteItem(cartDTO);

        return ResponseEntity.ok("장바구니에서 상품을 삭제하였습니다.");
    }

    // 장바구니 상품 수량 변경
    @PostMapping("/update")
    public ResponseEntity<String> update(HttpSession session,
                                         @ModelAttribute CartDTO cartDTO,
                                         RedirectAttributes attr) {
        String member_id = (String) session.getAttribute("member_id");
        cartDTO.setMember_id(member_id);
        // 담으려는 수량
        int productCount = cartDTO.getProduct_count();

        // 재고수량
        ProductDTO productDTO = new ProductDTO();
        
        // 1번버전. 안될거 같으니 비교해보면서 테스트
//        int productStock = productDTO.getProduct_stock();
        // 2번버전. 이것도 될지 안될지 모름
        int productStock = productService.selectStock(cartDTO.getProduct_no());

        // 담으려는 수량이 재고보다 적거나 같으면 장바구니 추가
        // 아마 고쳐야할 확률이 매우 높음
        if (productCount <= productStock) {
            service.cartChangeCount(productCount, productStock, member_id);
        } else {
            attr.addFlashAttribute("mode", "error");
        }
        return ResponseEntity.ok("수량이 변경되었습니다.");
    }

    @PatchMapping("/")
    public List<CartProductInfoDTO> changeProductQty(HttpSession session, CartDTO dto) {
        String member_id = (String) session.getAttribute("member_id");
        dto.setMember_id(member_id);

        int product_count, product_no;
        product_count = dto.getProduct_count();
        product_no = dto.getProduct_no();

        service.cartChangeCount(product_count, product_no, member_id);

        return infoService.cartInfoList(member_id);
    }

}

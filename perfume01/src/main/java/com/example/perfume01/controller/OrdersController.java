package com.example.perfume01.controller;

import com.example.perfume01.dao.MemberDAO;
import com.example.perfume01.dto.CartDTO;
import com.example.perfume01.dto.OrdersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
@RestController("/rest")
public class OrdersController {
    

    @PostMapping("/buy")
    public String buy(List<OrdersDTO> ordersDTO) {
        /*int totalPrice = 0;
        for(int i = 0 ; i < order/sDTO.getCartList().size() ; i++){
            CartDTO item = ordersDTO.getCartList().get(i);
            totalPrice += item.getProduct_price() * item.getProduct_count();
        }
*/
        //String order_no = UUID.randomUUID().toString();
        int order_no = (int) (Math.random() * 1000);
        for(int i = 0 ; i < ordersDTO.size() ; i++){
            OrdersDTO order = ordersDTO.get(i);
            order.setOrder_no(order_no);
            // db insert
        }

        // 세션에서 맴버 아이디 가져와서 저장, 포인트도 생각
        //String memberId = (String) session.getAttribute("member_id");
        
        return "/buy";
    }

}

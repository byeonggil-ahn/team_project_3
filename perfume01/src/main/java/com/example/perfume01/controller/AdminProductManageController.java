package com.example.perfume01.controller;

import com.example.perfume01.criteria.PageMaker;
import com.example.perfume01.criteria.SearchCriteria;
import com.example.perfume01.dao.ProductDAO;
import com.example.perfume01.dao.ProductTagDAOImpl;
import com.example.perfume01.dto.ProductDTO;
import com.example.perfume01.dto.ProductTagDTO;
import com.example.perfume01.service.ProductService;
import com.example.perfume01.service.ProductTagService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Log4j2
@RequestMapping("/admin/productManage")
@Controller @AllArgsConstructor
public class AdminProductManageController {

    ProductDAO dao;
    ProductService service;
    ProductTagDAOImpl tagDao;
    ProductTagService tagService;

    @GetMapping("/productList")
    public String productList(Model model) {
        model.addAttribute("plist", service.selectList());
        return "/productManage/productList";
    }

    @GetMapping("/list")
    public String list(Model model, SearchCriteria criteria, PageMaker pageMaker) {
        criteria.setSnoEno();

        // 오류가 나면 아래 코드들을 전부 삭제

        List<ProductDTO> productList = service.searchList(criteria);
        int totalCount = service.searchTotalCount(criteria);

        pageMaker.setCriteria(criteria);
        pageMaker.setTotalRowsCount(service.searchTotalCount(criteria));

        model.addAttribute("plist", productList);
        model.addAttribute("pageMaker", pageMaker);

        return "/productManage/list";

    }

    // 상품 등록

    @GetMapping("/insertProduct")
    public String insertProductForm() {
        return "/productManage/insertProduct";
    }

    @PostMapping("/insertProduct")
    public String insertProduct(HttpServletRequest request,
                                @ModelAttribute ProductDTO productDTO,
                                @ModelAttribute ProductTagDTO tagDTO,
                                @RequestParam(required = false) int tag_no)
        throws IllegalStateException, IOException {

//        int productNo = productDTO.getProduct_no();
//        productDTO.setProduct_no(productNo);
//        dao.insertProduct(productDTO);
//
//        if (tag_no > 0 && tag_no <=8) {
//            tagDTO.setProduct_no(productDTO.getProduct_no());
//            tagDTO.setTag_no(tag_no);
//            // productTagDao.insert(productTagDto);
//            // 이런식으로 해야되는데 productTagDoa가 자동주입이 안됨
//            tagDao.insertTag(tagDTO);
//
//        }

        String realPath = request.getRealPath("/");
        realPath = realPath.substring(0, realPath.lastIndexOf("\\"));
        realPath = realPath.substring(0, realPath.lastIndexOf("\\")+1);
        realPath = realPath + "resources\\static\\image\\";
        System.out.println("리얼패스 확인 : " + realPath);
        //C:\eGovFrame-4.0.0\workspace.edu\perfume01\src\main\resources\static\image

        File f1 = new File(realPath);
        if (!f1.exists()) {
            f1.mkdir();
        }

        f1 = new File(realPath + "01_incense_large.jpg");
        if (!f1.isFile()) {
            String basicImgPath = "C:\\eGovFrame-4.0.0\\workspace.edu\\perfume01\\src\\main\\resources\\static\\image\\01_incense_large.jpg" ;

            if (!new File(basicImgPath).getParentFile().exists()){
                new File(basicImgPath).getParentFile().mkdirs();
            }
            FileInputStream fi = new FileInputStream(new File(basicImgPath));
            FileOutputStream fo = new FileOutputStream(f1);

            FileCopyUtils.copy(fi, fo);
        }

        String file1, file2 = "static/image/default.jpg", file3, file4 = "static/image/default.jpg";

        MultipartFile product_mainimgf = productDTO.getProduct_mainimgf();
        MultipartFile product_subimgf = productDTO.getProduct_subimgf();

        if (product_mainimgf != null && !product_mainimgf.isEmpty()) {
            file1 = realPath + product_mainimgf.getOriginalFilename();
            product_mainimgf.transferTo(new File(file1));

            file2 = "static/image/" + product_mainimgf.getOriginalFilename();
        }
        productDTO.setProduct_mainimg(file2);


        if (product_subimgf != null && !product_subimgf.isEmpty()) {
            file3 = realPath + product_subimgf.getOriginalFilename();
            product_subimgf.transferTo(new File(file3));

            file4 = "static/image/" + product_subimgf.getOriginalFilename();
        }
        productDTO.setProduct_subimg(file4);

        service.insertProduct(productDTO);
        tagService.insertTag(productDTO);

        // View 처리
        return "redirect:list";
    }

    // 상품 선택 삭제
    @PostMapping("/deleteProduct")
    public String deleteProduct(ProductDTO dto) {
        // 해당되는 태그를 삭제한 이후에 상품을 삭제하는 순서로 진행하면 문제없이 진행된다.
        // 상품을 먼저 삭제하면 상품을 참조하고 있는 태그는 지워지지 못하게 된다.
        tagService.deleteTag(dto);
        service.deleteProduct(dto);

        return "redirect:list";
    }

    @GetMapping("/deleteEachProduct")
    public String deleteEachProduct(ProductDTO dto) {
        service.deleteEachProduct(dto);
        return "redirect:list";
    }


    // 상품 수정
    @GetMapping("/edit")
    public String editForm(Model model, ProductDTO dto, @RequestParam Integer product_no) {
        // Integer product_no 사용
        //dto.setProduct_no(product_no);
        // 이전 상품 정보

        model.addAttribute("productDTO" ,service.selectOne(dto));
        return "/productManage/edit";
    }

    @PostMapping("/edit")
    public String edit(HttpServletRequest request, ProductDTO dto,
                       ProductTagDTO tagDTO, Model model) throws Exception {

        model.addAttribute("productDTO", dto);
        model.addAttribute("productTagDto", tagDTO);

        // C:\eGovFrame-4.0.0\workspace.edu\perfume01\src\main\resources\static\image

        MultipartFile uploadfilef1 = dto.getProduct_mainimgf();
        MultipartFile uploadfilef2 = dto.getProduct_subimgf();
        if (uploadfilef1 != null && uploadfilef2 != null && !uploadfilef1.isEmpty() && !uploadfilef2.isEmpty()) {
            String realPath = request.getRealPath("/");

            System.out.println("리얼패스위치 +++" + realPath);

            // 개발중인지, 배포중인지를 비교하여 실제 저장위치를 생성
            if (realPath.contains(".idea.")) {
//                realPath = "/perfume01\\src\\main\\resources\\static\\image\\";
                realPath = "..\\resources\\static\\image\\";
            } else {
                realPath += "resources\\static\\image\\";
            }

            String file1 = realPath + uploadfilef1.getOriginalFilename();
            uploadfilef1.transferTo(new File(file1));
            String file2 = "resources/static/image/" + uploadfilef1.getOriginalFilename();
            dto.setProduct_mainimg(file2);

            String file3 = realPath + uploadfilef1.getOriginalFilename();
            uploadfilef1.transferTo(new File(file1));
            String file4 = "resources/static/image/" + uploadfilef1.getOriginalFilename();
            dto.setProduct_subimg(file4);
        }
        service.edit(dto);
        tagService.editTag(dto);

        
        return "redirect:list";
    }


    @PostMapping("/insertTag")
    public String insertTag(ProductDTO tagDTO) {
        tagDao.insertTag(tagDTO);

        return "redirect:list";
    }


    // 상품 태그 삭제 - 상품의 삭제와 같이 이루어 져야 함
    @PostMapping("/deleteTag")
    public void deleteTag(ProductDTO tagDTO) {
        tagService.deleteTag(tagDTO);
    }


}


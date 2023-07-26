package com.example.perfume01.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Data
public class ProductDataDTO {
    ProductDTO productDTO;
    ProductTagDTO productTagDTO;
}

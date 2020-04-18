package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.ProductNewQuestionDto;
import com.nimaaj.ecommerce.dto.ProductQaDto;
import com.nimaaj.ecommerce.dto.ProductQaReplyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQaService {

    ProductQaDto reply(Long productId, ProductQaReplyDto replyDto);

    ProductQaDto addQuestion(Long productId, ProductNewQuestionDto productNewQuestionDto);

    Page<ProductQaDto> pageProductQa(Long productId, Pageable pageable);

    ProductQaDto verify(Long productId, Long qaId);

    ProductQaDto reject(Long productId, Long qaId);

}

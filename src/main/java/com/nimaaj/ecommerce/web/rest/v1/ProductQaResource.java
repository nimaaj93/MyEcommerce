package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.ProductNewQuestionDto;
import com.nimaaj.ecommerce.dto.ProductQaDto;
import com.nimaaj.ecommerce.dto.ProductQaReplyDto;
import com.nimaaj.ecommerce.service.ProductQaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product/{productId}/question")
@RequiredArgsConstructor
@Validated
public class ProductQaResource {

    private final ProductQaService productQaService;

    @PostMapping
    public ResponseEntity<ProductQaDto> addQuestionForProduct(@PathVariable("productId") Long productId,
                                                              @Valid @RequestBody
                                                              ProductNewQuestionDto productNewQuestionDto) {
        return ResponseEntity.ok(productQaService.addQuestion(productId, productNewQuestionDto));
    }

    @PostMapping("/{questionId}/reply")
    public ResponseEntity<ProductQaDto> replyQuestion(@PathVariable("productId") Long productId,
                                                      @Valid @RequestBody ProductQaReplyDto replyDto) {
        return ResponseEntity.ok(productQaService.reply(productId, replyDto));
    }

    @GetMapping
    public ResponseEntity<Page<ProductQaDto>> pageQasForProduct(@PathVariable("productId") Long productId,
                                                Pageable pageable) {
        return ResponseEntity.ok(productQaService.pageProductQa(productId, pageable));
    }

    @PatchMapping("/{questionId}/verify")
    public ResponseEntity<ProductQaDto> verifyQuestion(@PathVariable("productId") Long productId,
                                                       @PathVariable("questionId") Long questionId) {
        return ResponseEntity.ok(productQaService.verify(productId, questionId));
    }

}

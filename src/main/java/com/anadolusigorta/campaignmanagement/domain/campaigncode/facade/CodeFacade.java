package com.anadolusigorta.campaignmanagement.domain.campaigncode.facade;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeCriteria;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCode;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCode;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.port.CodeRepository;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CodeFacade {

    private final CodeRepository codeRepository;

    public PageContent<DiscountCode> getDiscountCodesByCriteria(CodeCriteria codeCriteria, Pageable pageable) {
        return codeRepository.findDiscountCodesByCriteria(codeCriteria, pageable);
    }

    public PageContent<GiftCode> getGiftCodesByCriteria(CodeCriteria codeCriteria, Pageable pageable) {
        return codeRepository.findGiftCodesByCriteria(codeCriteria, pageable);
    }
}

package com.anadolusigorta.campaignmanagement.domain.campaigncode.port;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeCriteria;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCode;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCode;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CodeRepository {
    PageContent<DiscountCode> findDiscountCodesByCriteria(CodeCriteria codeCriteria, Pageable pageable);

    PageContent<GiftCode> findGiftCodesByCriteria(CodeCriteria codeCriteria, Pageable pageable);
}

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateDiscountCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.infrastructure.validation.conditionalvalidation.ConditionalValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConditionalValidation(
        conditionalProperty = "codeTypeId", values = {"1"},
        requiredProperties = {"codeCharacterLength", "codeGenerationCount"},
        message = "Code Character Length and Code Generation Count required"
)
@ConditionalValidation(
        conditionalProperty = "codeTypeId", values = {"2"},
        requiredProperties = {"code"},
        message = "Code is needed for unlimited use code type"
)
public class CreateDiscountCodeInformationRequest {

    private Long id;

    @NotBlank
    private String codeGroupName;

    @NotNull
    private Long codeTypeId;

    private Long codeKindId;

    private Boolean isPairedWithCustomers;

    private Long codeCharacterLength;

    @NotNull
    private LocalDateTime codeExpirationDate;

    private Long codeGenerationCount;

    private String code;

    private List<DiscountCode> discountCodes;

    public CreateDiscountCodeInformation toModel() {
        if(isPairedWithCustomers){
            var customers=discountCodes.stream().map(DiscountCode::getCustomerNo)
                    .toList();
            var customersEmpty=customers.stream().filter(String::isEmpty)
                    .toList();
            if(customers.size()==customersEmpty.size()){
                throw new CampaignManagementException("customers.should.not.empty");
            }
            this.discountCodes=discountCodes.stream()
                    .filter(item->!item.getCustomerNo().isEmpty())
                    .toList();

        }
        return CreateDiscountCodeInformation.builder()
                .id(id)
                .codeGroupName(codeGroupName)
                .codeTypeId(codeTypeId)
                .codeKindId(codeKindId)
                .isPairedWithCustomers(isPairedWithCustomers)
                .codeCharacterLength(codeCharacterLength)
                .codeExpirationDate(codeExpirationDate)
                .codeGenerationCount(codeGenerationCount)
                .code(code)
                .discountCodes(discountCodes)
                .build();
    }
}

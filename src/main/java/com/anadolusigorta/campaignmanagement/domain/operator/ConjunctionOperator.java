/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.operator */

package com.anadolusigorta.campaignmanagement.domain.operator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConjunctionOperator {

	private Long id;

	private String name;

	private ConjunctionOperatorEnum conjunctionOperatorEnum;


}

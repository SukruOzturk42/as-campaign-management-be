/* dks20165 created on 3.01.2022 inside the package - com.anadolusigorta.campaignmanagement.domain.common.model */

package com.anadolusigorta.campaignmanagement.domain.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.LinkedList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PageContent<T> {


	private int page;

	private int size;

	private int totalPages;

	private long totalItems;

	private Sort sort;

	private List<T> content;

	public PageContent(){
		this.content=new LinkedList<>();
	}

}

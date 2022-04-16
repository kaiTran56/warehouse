package com.tranquyet.common.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public abstract class BasedDTO<T> {

	private Long id;

	private LocalDateTime createdDate;

	private LocalDateTime modifiedDate;

	private String createdBy;

	private String modifiedBy;

	private Long[] ids;

	private List<T> listResult = new ArrayList<T>();

	private Integer page;

	private Integer limit;

	private Integer totalPage;

	private Integer totalItem;

	private String sortName;

	private String sortBy;

	private String alert;

	private String type;

	private Integer currentPage;

	private Integer deleteStatus;
}

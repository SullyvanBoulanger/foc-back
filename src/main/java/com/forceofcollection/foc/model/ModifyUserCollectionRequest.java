package com.forceofcollection.foc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModifyUserCollectionRequest {
	private Integer cardId;
	private Integer numberToApply;
}
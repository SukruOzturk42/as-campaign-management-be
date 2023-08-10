package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface Specification <T> extends org.springframework.data.jpa.domain.Specification<T> {

    @Override
    default org.springframework.data.jpa.domain.Specification<T> and(org.springframework.data.jpa.domain.Specification<T> other) {
        return org.springframework.data.jpa.domain.Specification.super.and(other);
    }

    @Override
    default org.springframework.data.jpa.domain.Specification<T> or(org.springframework.data.jpa.domain.Specification<T> other) {
        return org.springframework.data.jpa.domain.Specification.super.or(other);
    }

    @Override
    Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder);
}
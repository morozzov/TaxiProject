package ru.taxi.orderprocessor.dao.custom;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.taxi.orderprocessor.dto.ECriteria;
import ru.taxi.orderprocessor.dto.Sort;
import ru.taxi.orderprocessor.entity.CarEntity;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.SortOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CarOperationsRepositoryCustom {

    EntityManager entityManager;

    public List<CarEntity> find(ECriteria criteria, Sort sort) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CarEntity> query = criteriaBuilder.createQuery(CarEntity.class);
        Root<CarEntity> root = query.from(CarEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        if (criteria != null) {
            //add search predicates
            ofNullable(criteria.getColor())
                    .ifPresent(option -> predicates.add(criteriaBuilder.equal(root.get("color"), option)));
            ofNullable(criteria.getCarClass())
                    .ifPresent(option -> predicates.add(criteriaBuilder.equal(root.get("carClass"), option)));
            ofNullable(criteria.getPriorityClass())
                    .ifPresent(option -> predicates.add(criteriaBuilder.equal(root.get("priorityClass"), option)));
            ofNullable(criteria.getIssuedAtGreater())
                    .ifPresent(option -> predicates.add(criteriaBuilder.greaterThan(root.get("issuedAt"), option)));
            ofNullable(criteria.getIssuedAtLower())
                    .ifPresent(option -> predicates.add(criteriaBuilder.lessThan(root.get("issuedAt"), option)));
        }
        ofNullable(sort).ifPresent(sortingStrategy -> {
            validateSort(sortingStrategy);
            if (sortingStrategy.getSortOrder().equals(SortOrder.ASCENDING)) {
                query.orderBy(criteriaBuilder.asc(root.get(sortingStrategy.getSortBy())));
            }
            else if (sortingStrategy.getSortOrder().equals(SortOrder.DESCENDING)) {
                query.orderBy(criteriaBuilder.desc(root.get(sortingStrategy.getSortBy())));
            }
        });
        query.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }

    private void validateSort(Sort sortingStrategy) {
        boolean anyMatch = Arrays.stream(CarEntity.class.getDeclaredFields())
                .anyMatch(field -> field.getName().equals(sortingStrategy.getSortBy()));
        if (!anyMatch) {
            throw new IllegalArgumentException(format("Field %s does not exist in entity!", sortingStrategy.getSortBy()));
        }
    }
}

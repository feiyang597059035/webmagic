package com.carwel.webmagic.config.es;

import org.elasticsearch.index.query.BoolQueryBuilder;

import java.util.List;

public interface SearchCondition {
    SearchCondition equal(String field, Object obj);

    SearchCondition notEqual(String field, Number obj);

    SearchCondition in(String field, List<?> objs);

    SearchCondition notIn(String field, List<?> objs);

    SearchCondition matchPhraseLike(String field, String obj);

    SearchCondition like(String field, String obj);

    SearchCondition orLike(String obj, String... field);

    SearchCondition orLikeWithNest(String path, SearchCondition nestCondition, String obj, String... field);

    SearchCondition llike(String field, String obj);

    SearchCondition rlike(String field, String obj);

    SearchCondition gt(String field, Number obj);
    SearchCondition gte(String field, Number obj);
    SearchCondition gt(String field, Number obj, boolean isInt);

    SearchCondition lt(String field, Number obj);
    SearchCondition lte(String field, Number obj);
    SearchCondition lt(String field, Number obj, boolean isInt);

    SearchCondition orderDesc(String... fields);

    SearchCondition orderAsc(String... fields);

    SearchCondition orderNestedDesc(String field,String nestedPath);

    SearchCondition orderNestedAsc(String field,String nestedPath);

    SearchCondition orlt(String field1, Number obj1, String field2, Number obj2);
    SearchCondition orlt(String field1, Number obj1, String field2, Number obj2, boolean isInt);

    SearchCondition select(String... fields);

    SearchCondition setStart(int start);

    SearchCondition setLimit(int limit);

    SearchCondition distinct(String... fields);
    SearchCondition count(String groupKey);
    SearchCondition count(String groupKey, String name);
    SearchCondition max(String groupKey, String field);
    SearchCondition max(String groupKey, String field, String name);
    SearchCondition min(String groupKey, String field);
    SearchCondition min(String groupKey, String field, String name);
    SearchCondition sum(String groupKey, String field);
    SearchCondition sum(String groupKey, String field, String name);
    SearchCondition topHit(String groupKey, String name,int from, int topN);

    BoolQueryBuilder build();
    default SearchCondition constantScoreBuild(){throw new UnsupportedOperationException("constantScoreBuild");}

    SearchCondition nestQuery(String path, SearchCondition nestCondition);

}

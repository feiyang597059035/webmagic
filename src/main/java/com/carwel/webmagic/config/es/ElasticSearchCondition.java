package com.carwel.webmagic.config.es;


import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequestBuilder;


import org.elasticsearch.index.query.BoolQueryBuilder;

import org.elasticsearch.index.query.QueryBuilders;

import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.collapse.CollapseBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;

import org.elasticsearch.search.sort.SortOrder;

import org.springframework.stereotype.Component;


import java.util.List;

public class ElasticSearchCondition implements SearchCondition {
    /**
     * 默认的pageSize
     */
    private static final int DEFAULT_PAGE_SIZE = 200;
    private SearchRequestBuilder builder;
    private BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    /**
     * 是否不进行相关性评分
     */
    private boolean isConstantScore = false;
    @Override
    public SearchCondition equal(String field, Object obj) {
        if (check(field, obj)) {
            boolQueryBuilder.filter(QueryBuilders.termQuery(field, obj));
        }
        return this;
    }

    private boolean check(Object... objs) {
        if (objs == null || objs.length == 0) {
            return false;
        }
        for (Object obj : objs) {
            if (obj == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public SearchCondition notEqual(String field, Number obj) {
        if (check(field, obj)) {
            boolQueryBuilder.mustNot(QueryBuilders.termQuery(field, obj));
        }
        return this;
    }

    @Override
    public SearchCondition in(String field, List<?> objs) {
        if (check(field, objs)) {
            boolQueryBuilder.filter(QueryBuilders.termsQuery(field, objs));
        }
        return this;
    }

    @Override
    public SearchCondition notIn(String field, List<?> objs) {
        if (check(field, objs)) {
            boolQueryBuilder.mustNot(QueryBuilders.termsQuery(field, objs));
        }
        return this;
    }

    @Override
    public SearchCondition matchPhraseLike(String field, String obj) {
        if (check(field, obj)) {
            boolQueryBuilder.must(QueryBuilders.matchPhraseQuery(field, obj));
        }
        return this;
    }

    @Override
    public SearchCondition like(String field, String obj) {
        if (check(field, obj)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(field, obj));
        }
        return this;
    }

    @Override
    public SearchCondition orLike(String obj, String... fields) {
        if (check(obj, fields)) {
            BoolQueryBuilder builder = QueryBuilders.boolQuery();
            for (String field : fields) {
                builder.should(QueryBuilders.matchQuery(field, obj));
            }
            boolQueryBuilder.must(builder);
        }
        return this;
    }

    @Override
    public SearchCondition orLikeWithNest(String path, SearchCondition nestCondition, String obj, String... fields) {
        if (check(obj, fields)) {
            BoolQueryBuilder builder = QueryBuilders.boolQuery();
            for (String field : fields) {
                builder.should(QueryBuilders.matchQuery(field, obj));
            }
            if (!StringUtils.isEmpty(path) && nestCondition != null && nestCondition instanceof ElasticSearchCondition) {
                builder.should(QueryBuilders.nestedQuery(path, ((ElasticSearchCondition) nestCondition).boolQueryBuilder, ScoreMode.Max));
            }
            boolQueryBuilder.must(builder);
        }
        return this;
    }

    @Override
    public SearchCondition llike(String field, String obj) {
        return like(field, obj);
    }

    @Override
    public SearchCondition rlike(String field, String obj) {
        return like(field, obj);
    }

    @Override
    public SearchCondition gt(String field, Number obj) {
        if (check(field, obj)) {
            boolQueryBuilder.filter(QueryBuilders.rangeQuery(field).gt(obj));
        }
        return this;
    }

    @Override
    public SearchCondition gte(String field, Number obj) {
        if (check(field, obj)) {
            boolQueryBuilder.filter(QueryBuilders.rangeQuery(field).gte(obj));
        }
        return this;
    }

    @Override
    public SearchCondition lte(String field, Number obj) {
        if (check(field, obj)) {
            boolQueryBuilder.filter(QueryBuilders.rangeQuery(field).lte(obj));
        }
        return this;
    }

    @Override
    public SearchCondition lt(String field, Number obj) {
        if (check(field, obj)) {
            boolQueryBuilder.filter(QueryBuilders.rangeQuery(field).lt(obj));
        }
        return this;
    }

    @Override
    public SearchCondition gt(String field, Number obj, boolean isInt) {
        return gt(field, obj);
    }

    @Override
    public SearchCondition lt(String field, Number obj, boolean isInt) {
        return lt(field, obj);
    }

    @Override
    public SearchCondition orlt(String field1, Number obj1, String field2, Number obj2, boolean isInt) {
        return orlt(field1, obj1, field2, obj2);
    }

    @Override
    public SearchCondition orderDesc(String... fields) {
        if (check((Object) fields)) {
            for (String field : fields) {
                builder.addSort(field, SortOrder.DESC);
            }
        }
        return this;
    }


    @Override
    public SearchCondition orderAsc(String... fields) {
        if (check((Object) fields)) {
            for (String field : fields) {
                builder.addSort(field, SortOrder.ASC);
            }
        }
        return this;
    }

    @Override
    public SearchCondition orderNestedDesc(String field,String nestedPath){
        if(check((Object) field)){
            SortBuilder sb = SortBuilders.fieldSort(field)
                                        .order(SortOrder.DESC)
                                        .setNestedPath(nestedPath);
            builder.addSort(sb);
        }
        return this;
    }

    @Override
    public SearchCondition orderNestedAsc(String field,String nestedPath){
        if(check((Object) field)){
            SortBuilder sb = SortBuilders.fieldSort(field)
                    .order(SortOrder.ASC)
                    .setNestedPath(nestedPath);
            builder.addSort(sb);
        }
        return this;
    }

    @Override
    public SearchCondition orlt(String field1, Number obj1, String field2, Number obj2) {
        throw new RuntimeException("Not support.");
    }

    @Override
    public SearchCondition select(String... fields) {
        builder.setFetchSource(fields, null);
        return this;
    }

    @Override
    public SearchCondition setStart(int start) {
        builder.setFrom(start);
        return this;
    }

    @Override
    public SearchCondition setLimit(int limit) {
        builder.setSize(limit);
        return this;
    }

    @Override
    public SearchCondition distinct(String... fields) {
        if (check((Object) fields)) {
            if (fields.length > 1) {
                throw new RuntimeException("ElasticSearch is not support one more field to distinct.");
            }
            if (fields.length == 1) {
                builder.setCollapse(new CollapseBuilder(fields[0]));
            }
        }
        return this;
    }

    @Override
    public SearchCondition count(String groupKey) {
        return count(groupKey, "count(" + groupKey + ")");
    }

    @Override
    public SearchCondition count(String groupKey, String name) {
        if (check(groupKey)) {
            builder.addAggregation(AggregationBuilders.terms(name).size(DEFAULT_PAGE_SIZE)
                    .field(groupKey).subAggregation(AggregationBuilders.count("count").field(groupKey)));
        }
        return this;
    }

    @Override
    public SearchCondition max(String groupKey, String field) {
        return max(groupKey, field, "max(" + groupKey + ")");
    }

    @Override
    public SearchCondition max(String groupKey, String field, String name) {
        if (!check(field)) {
            return this;
        }
        if (StringUtils.isBlank(groupKey)) {
            builder.addAggregation(AggregationBuilders.max(name)
                    .field(field));
        } else {
            builder.addAggregation(AggregationBuilders.terms(name).size(DEFAULT_PAGE_SIZE)
                    .field(groupKey).subAggregation(AggregationBuilders.max("max").field(field)));
        }
        return this;
    }

    @Override
    public SearchCondition min(String groupKey, String field) {
        return min(groupKey, field, "min(" + field + ")");
    }

    @Override
    public SearchCondition min(String groupKey, String field, String name) {
        if (!check(field)) {
            return this;
        }
        if (StringUtils.isBlank(groupKey)) {
            builder.addAggregation(AggregationBuilders.min(name)
                    .field(field));
        } else {
            builder.addAggregation(AggregationBuilders.terms(name).size(DEFAULT_PAGE_SIZE)
                    .field(groupKey).subAggregation(AggregationBuilders.min("min").field(field)));
        }
        return this;
    }

    @Override
    public SearchCondition sum(String groupKey, String field) {
        return sum(groupKey, field, "sum(" + field + ")");
    }

    @Override
    public SearchCondition sum(String groupKey, String field, String name) {
        if (!check(field)) {
            return this;
        }
        if (StringUtils.isBlank(groupKey)) {
            builder.addAggregation(AggregationBuilders.sum(name)
                    .field(field));
        } else {
            builder.addAggregation(AggregationBuilders.terms(name).size(DEFAULT_PAGE_SIZE)
                    .field(groupKey).subAggregation(AggregationBuilders.sum("sum").field(field)));
        }
        return this;
    }

    @Override
    public SearchCondition topHit(String groupKey, String name, int from, int topN) {
        if (!check(groupKey) || topN < 0) {
            return this;
        }
        builder.addAggregation(AggregationBuilders.terms(name).size(DEFAULT_PAGE_SIZE)
                .field(groupKey).subAggregation(AggregationBuilders.topHits("top").from(from).size(topN)));
        return this;
    }

    @Override
    public BoolQueryBuilder build() {
        return boolQueryBuilder;
    }

    @Override
    public SearchCondition constantScoreBuild() {
        builder.setQuery(QueryBuilders.constantScoreQuery(boolQueryBuilder));
        return this;
    }

    @Override
    public SearchCondition nestQuery(String path, SearchCondition nestCondition) {
        if (StringUtils.isEmpty(path) || nestCondition == null) {
            return this;
        }
        if (!(nestCondition instanceof ElasticSearchCondition)) {
            throw new RuntimeException("Incompatible class found.");
        }
        boolQueryBuilder.filter(QueryBuilders.nestedQuery(path, ((ElasticSearchCondition) nestCondition).boolQueryBuilder, ScoreMode.Max));
        return this;
    }

    BoolQueryBuilder getBuilder() {
        return boolQueryBuilder;
    }
}

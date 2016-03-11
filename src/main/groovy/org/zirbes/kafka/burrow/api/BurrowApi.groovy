package org.zirbes.kafka.burrow.api

import org.zirbes.kafka.burrow.domain.ClusterList
import org.zirbes.kafka.burrow.domain.ClusterResponse
import org.zirbes.kafka.burrow.domain.ConsumerResponse
import org.zirbes.kafka.burrow.domain.ConsumerTopicsResponse
import org.zirbes.kafka.burrow.domain.OffsetResponse

import retrofit.http.*

interface BurrowApi {

    @Headers(['Accept: application/json'])
    @GET('/v2/kafka')
    ClusterList getClusters()

    @Headers(['Accept: application/json'])
    @GET('/v2/kafka/{cluster}')
    Map clusterStatus(@Path('cluster') cluster)

    @Headers(['Accept: application/json'])
    @GET('/v2/kafka/{cluster}/consumer')
    ClusterResponse getCluster(@Path('cluster') cluster)

    @Headers(['Accept: application/json'])
    @GET('/v2/kafka/{cluster}/consumer/{consumer}/topic')
    ConsumerTopicsResponse getTopics(@Path('cluster') cluster, @Path('consumer') consumer)

    @Headers(['Accept: application/json'])
    @GET('/v2/kafka/{cluster}/consumer/{consumer}/status')
    ConsumerResponse consumerStatus(@Path('cluster') cluster, @Path('consumer') consumer)

    @Headers(['Accept: application/json'])
    @GET('/v2/kafka/{cluster}/consumer/{consumer}/lag')
    ConsumerResponse consumerLag(@Path('cluster') cluster, @Path('consumer') consumer)

    @Headers(['Accept: application/json'])
    @GET('/v2/kafka/{cluster}/consumer/{consumer}/topic/{topic}')
    OffsetResponse getOffsets(@Path('cluster') cluster, @Path('consumer') consumer, @Path('topic') topic)

    @Headers(['Accept: application/json'])
    @GET('/v2/kafka/{cluster}/topic/{topic}')
    OffsetResponse getOffsets(@Path('cluster') cluster, @Path('topic') topic)

}

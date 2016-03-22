package org.zirbes.kafka.burrow.service

import groovy.util.logging.Slf4j

import org.zirbes.kafka.burrow.api.BurrowApi
import org.zirbes.kafka.burrow.domain.Cluster
import org.zirbes.kafka.burrow.domain.Consumer
import org.zirbes.kafka.burrow.domain.Partition
import org.zirbes.kafka.burrow.domain.Topic

import retrofit.RestAdapter

import static org.zirbes.kafka.burrow.util.Colorizer.color

@Slf4j
class BurrowHerder {

    protected final BurrowApi api

    BurrowHerder(URI hostname) {
        log.info "Connecting to Burrow endpoint: ${hostname}"
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(hostname.toString())
                //.setLogLevel(RestAdapter.LogLevel.BASIC)
                .build()

        api = restAdapter.create(BurrowApi)
    }

    void herd() {
        log.info "Finding clusters..."

        List<Cluster> clusters = []

        api.getClusters().clusters.each { String clusterName ->
            Cluster cluster = new Cluster(clusterName)

            log.info "Loading cluster ${cluster.name}..."
            api.getCluster(cluster.name).consumers.each { String consumerName ->

                log.debug "Checking lag for ${consumerName}..."
                Consumer consumer = api.consumerLag(cluster.name, consumerName).status
                log.debug "Checking topics for ${consumerName}..."
                consumer.topics = api.getTopics(cluster.name, consumerName).topics.collect { new Topic(it) }

                consumer.partitions.groupBy { it.topic }.each { String topic, List<Partition> partitions ->
                    consumer.topic(topic).partitions = partitions
                }

                cluster.consumers << consumer
            }
            clusters << cluster
        }

        // print results
        clusters.each { Cluster cluster ->
            if (cluster.ok) {
                log.info printCluster(cluster)
            } else {
                log.info printFailing(cluster)
            }
        }

    }

    protected String printCluster(Cluster cluster) {
        StringBuffer sb = new StringBuffer()

        sb << "Cluster: ${cluster.name} : ${color(cluster.status)}\n"
        cluster.consumers.each { Consumer consumer ->
            sb << " * ${consumer.name} : ${color(consumer.status)}\n"
            consumer.topics.each { Topic topic ->
                sb << "   * ${topic.name} : ${color(topic.status)}\n"
                topic.partitions.each { Partition partition ->
                    sb << "     * ${partition.partition} : ${color(partition.status)} ${partition.start} ${partition.end}\n"
                }
            }
        }
        sb.toString()
    }

    protected String printFailing(Cluster cluster) {
        StringBuffer sb = new StringBuffer()

        sb << "Cluster: ${cluster.name} :: ${color(cluster.status)}\n"
        cluster.failingConsumers.each { Consumer consumer ->
            sb << " * Consumer: ${consumer.name} :: ${color(consumer.status)}\n"
            consumer.failingTopics.each { Topic topic ->
                sb << "   * Topic: ${topic.name} :: ${color(topic.status)}\n"
                topic.partitions.each { Partition partition ->
                    sb << "     * Partition ${partition.partition} :: ${color(partition.status)} ${partition.start} ${partition.end}\n"
                }
            }
        }
        sb.toString()
    }

}

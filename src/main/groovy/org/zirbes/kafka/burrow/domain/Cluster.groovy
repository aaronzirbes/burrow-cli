package org.zirbes.kafka.burrow.domain

class Cluster {
    String name
    List<Consumer> consumers = []

    List<Consumer> getFailingConsumers() {
        consumers.findAll { !it.ok }
    }

    List<Consumer> getFailingPartitions() {
        consumers*.failingPartitions?.flatten()
    }

    List<Consumer> getFailingTopics() {
        consumers*.failingTopics?.flatten()
    }

    boolean isOk() { status == 'OK' }
    String getStatus() {
        Set<String> statuses = (consumers*.status as Set)
        if (statuses.size() == 1) { return statuses[0] }
        statuses.remove('OK')
        return statuses[0]
    }


    Cluster(String name) { this.name = name }

    Consumer consumer(String consumerName) {
        Consumer consumer = consumers.find { it.name == consumerName }
        if (!consumer) {
            consumer = new Consumer(consumerName)
            consumers << consumer
        }
        return consumer
    }
}

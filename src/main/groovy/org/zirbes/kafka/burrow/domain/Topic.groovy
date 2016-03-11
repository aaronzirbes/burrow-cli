package org.zirbes.kafka.burrow.domain

class Topic {
    String name
    List<Partition> partitions = []

    List<Partition> getFailingPartitions() {
        partitions.findAll { !it.ok }
    }

    Topic(String name) { this.name = name }

    boolean isOk() { status == 'OK' }
    String getStatus() {
        Set<String> statuses = (partitions*.status as Set)
        if (statuses.size() == 1) { return statuses[0] }
        statuses.remove('OK')
        return statuses[0]
    }

}

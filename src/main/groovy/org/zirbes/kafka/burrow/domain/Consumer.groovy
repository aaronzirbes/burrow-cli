package org.zirbes.kafka.burrow.domain

class Consumer {

    String cluster
    boolean complete
    String group
    Partition maxlag
    List<Partition> partitions = []
    String status
    List<Topic> topics

    List<Partition> getFailingPartitions() {
        partitions.findAll { !it.ok }
    }

    List<Topic> getFailingTopics() {
        topics.findAll { !it.ok }
    }

    String getName() { group }

    boolean isOk() { status == 'OK' }

    Consumer(String group) { this.group = group }


    Topic topic(String topicName) {
        Topic topic = topics.find { it.name == topicName }
        if (!topic) {
            topic = new Topic(topicName)
            topics << topic
        }
        return topic
    }
}

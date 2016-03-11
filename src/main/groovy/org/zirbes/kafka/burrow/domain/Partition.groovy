package org.zirbes.kafka.burrow.domain

class Partition {
    Integer partition
    LagInfo start
    LagInfo end
    String status
    String topic

    boolean isOk() { status == 'OK' }
}

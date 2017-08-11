package org.zirbes.kafka.burrow.domain

class Partition {
    Long partition
    LagInfo start
    LagInfo end
    String status
    String topic

    boolean isOk() {
        if (!start?.ok) { return false }
        if (!end?.ok) { return false }
        return status == 'OK'
    }
}

package org.zirbes.kafka.burrow.domain

import org.joda.time.DateTime

class LagInfo {

    Integer lag
    Integer offset
    Long timestamp

    DateTime getTime() {
        new DateTime(timestamp)
    }

    String toString() {
        List<String> info = []
        if (lag) { info << "lag: ${lag}".toString() }
        if (offset) { info << "offset: ${offset}".toString() }
        if (lag || offset) {
            info << 'last seen: ' + time.toString('M/d h:mm:ss a z')
        }
        return info.join(', ').padLeft(48)
    }
}

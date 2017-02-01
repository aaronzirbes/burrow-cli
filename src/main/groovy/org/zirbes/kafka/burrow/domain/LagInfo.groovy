package org.zirbes.kafka.burrow.domain

import java.util.concurrent.CopyOnWriteArrayList

import org.joda.time.DateTime
import org.zirbes.kafka.burrow.util.Colorizer

class LagInfo {

    Long lag
    Long offset
    Long timestamp

    boolean isOk() { !lag }

    DateTime getTime() {
        new DateTime(timestamp)
    }

    String toString() {
        Long padding = 60
        List<String> info = new CopyOnWriteArrayList<String>()
        if (lag) {
            //padding += Colorizer.RED.length() + Colorizer.RESET.length()
            info << 'lag: ' + Colorizer.red(lag.toString())
        }
        if (offset) { info << "offset: ${offset}".toString() }
        if (lag || offset) {
            info << 'seen: ' + time.toString('M/d h:mm:ss a z')
        }
        return info.join(', ').padLeft(48)
    }
}

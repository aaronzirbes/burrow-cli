package org.zirbes.kafka.burrow.cli

import org.zirbes.kafka.burrow.config.BurrowConfig
import org.zirbes.kafka.burrow.service.BurrowHerder

class BurrowCli {

    static void main(String ... args) {

        BurrowConfig config = new BurrowConfig()
        URI hostname = config.default
        if (args) { hostname = config.getURI(args[0]) }

        BurrowHerder burrow = new BurrowHerder(hostname)
        burrow.herd()

    }

}

package org.zirbes.kafka.burrow.config

import groovy.util.logging.Slf4j

@Slf4j
class BurrowConfig {

    static final String HOME = 'HOME'
    static final String CONFIG_FILE = System.getenv(HOME) + '/.burrow-cli.config'

    Map<String, URI> aliases = [:]

    BurrowConfig() {
        File config = new File(CONFIG_FILE)
        if (config.exists()) {
            config.eachLine { String line ->
                String current = line.trim()
                if (current && !current.startsWith('#') && current =~ /:/) {
                    String alias = current.replaceFirst(/:.*/, '')
                    String uri = current.replaceFirst("${alias} *: *", '')

                    aliases[alias] = new URI(uri)
                    log.debug "Loaded alias: ${alias}: ${uri}"
                }
            }
        } else {
            log.warn "No configuration file ${CONFIG_FILE} found."
        }
    }

    URI getURI(String param) {
        aliases[param] ?: new URI(param)
    }

    URI getDefault() {

        String defaultAlias = getURI('default').toString()
        if (defaultAlias.toString() == 'default') {
            log.warn "No 'default' alias defined in ${CONFIG_FILE}."
            return null
        }

        return getURI(defaultAlias)
    }

}

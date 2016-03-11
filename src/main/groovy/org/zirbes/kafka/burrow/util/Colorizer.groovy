package org.zirbes.kafka.burrow.util

class Colorizer {

    static final String BLACK  = '\033[1;30m'
    static final String RED    = '\033[1;31m'
    static final String GREEN  = '\033[1;32m'
    static final String YELLOW = '\033[1;33m'
    static final String BLUE   = '\033[1;34m'
    static final String PURPLE = '\033[1;35m'
    static final String CYAN   = '\033[1;36m'
    static final String WHITE  = '\033[1;37m'
    static final String RESET  = '\033[0m'

    static String color(String status) {
        (status == 'OK') ? green(status) : red(status)
    }

    static String green(String str) {
        "${GREEN}${str}${RESET}"
    }

    static String red(String str) {
        "${RED}${str}${RESET}"
    }

}

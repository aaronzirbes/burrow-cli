# Burrow CLI

## Installation

    ./gradlew install

This installs the `burrow` script to your ~/bin folder and installs the JAR to ~/.burrow-cli/

## Usage

specify a specific host

    burrow http://localhost:8080

or specify an alias from your config file

    burrow qa


## Sample `~/.burrow-cli.config` configuration

```
# Burrow host aliases

# default alias to use
default: dev

# alias definitions
prod:    http://burrow.production.zirbes.org:8666
staging: http://burrow-staging.zirbes.org:8555
qa:      http://burrow-qa.zirbes.org:8080
dev:     http://burrow-dev.zirbes.org:8080
```

## Screenshot

![Burrow CLI Screenshot](/burrow-cli.png?raw=true "Burrow CLI Screenshot")


# Burrow CLI

## Usage

Run via gradle using defaults:

    gradle run

Run via the JAR file by specifying a URL

    java -jar burrow-cli-all.jar http://localhost:8080

or specify an alias from your config file

    java -jar burrow-cli-all.jar qa


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



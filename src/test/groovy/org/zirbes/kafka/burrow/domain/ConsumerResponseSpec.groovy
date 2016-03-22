package org.zirbes.kafka.burrow.domain

import com.google.gson.Gson

import spock.lang.Specification

class ConsumerResponseSpec extends Specification {

    Gson gson

    void setup() {
        gson = new Gson()
    }

    void 'can marshal sample response to object'() {
        given:
        String json = jsonFromFixture('consumerLag')

        when:
        ConsumerResponse response = gson.fromJson(json, ConsumerResponse)

        then:
        response.status.partitions.first().topic == 'GRAVE_CROOKED'
    }

    protected String jsonFromFixture(String fixture) {
        String path = "/fixtures/${fixture}.json"
        return jsonFromResource(path)
    }

    protected String jsonFromResource(String resourcePath) {
        InputStream inputStream = this.class.getResourceAsStream(resourcePath)
        if (inputStream) {
            return stripWhiteSpace(inputStream.text)
        }
        throw new FileNotFoundException(resourcePath)
    }

    protected String stripWhiteSpace(String str) {
        return str

        StringBuffer out = new StringBuffer()
        str.eachLine {
            out << it.replaceFirst(/": /, '":').replaceFirst(/" : /, '":').trim()
        }
        return out.toString()
    }

}

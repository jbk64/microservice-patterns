package business;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SubscriberDTO {
    private String topic;
    private String url;

    public SubscriberDTO() {
    }

    public SubscriberDTO(String topic, String url) {
        this.topic = topic;
        this.url = url;
    }

    public String getTopic() {
        return topic;
    }

    public String getUrl() {
        return url;
    }

    public String toString() {
        return topic + "-" + url;
    }
}

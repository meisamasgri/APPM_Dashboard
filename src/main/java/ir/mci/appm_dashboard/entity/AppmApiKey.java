package ir.mci.appm_dashboard.entity;

import javax.persistence.*;

@Entity
@Table(name = "appm_api_key")
public class AppmApiKey {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "ip_address")
    private String ipAddress;

    @Basic
    @Column(name = "port")
    private String port;

    @Basic
    @Column(name = "api_key")
    private String apiKey;

    @Basic
    @Column(name = "test_action_id")
    private String testActionId;

    public AppmApiKey() {
    }

    public AppmApiKey(Integer id, String name, String description, String ipAddress, String port, String apiKey, String testActionId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ipAddress = ipAddress;
        this.port = port;
        this.apiKey = apiKey;
        this.testActionId = testActionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getTestActionId() {
        return testActionId;
    }

    public void setTestActionId(String testActionId) {
        this.testActionId = testActionId;
    }

    @Override
    public String toString() {
        return "AppmApiKey{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", port='" + port + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", testActionId='" + testActionId + '\'' +
                '}';
    }
}

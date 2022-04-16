package ir.mci.appm_dashboard.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appm_monitor_list")
public class AppmMonitorList {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "resource_id")
    private String resourceId;

    @Basic
    @Column(name = "resource_name")
    private String resourceName;

    @Basic
    @Column(name = "display_name")
    private String displayName;

    @Basic
    @Column(name = "project")
    private String project;

    @Basic
    @Column(name = "page_url")
    private String pageUrl;

    @Basic
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "type_short_name")
    private String typeShortName;

    @Basic
    @Column(name = "hostname")
    private String hostname;

    @Basic
    @Column(name = "host")
    private String host;

    @Basic
    @Column(name = "host_ip")
    private String hostIp;

    @Basic
    @Column(name = "health_status")
    private String healthStatus;

    @Basic
    @Column(name = "health_message")
    private String healthMessage;

    @Basic
    @Column(name = "availability_status")
    private String availabilityStatus;

    @Basic
    @Column(name = "availability_message")
    private String availabilityMessage;

    @Basic
    @Column(name = "poll_interval")
    private String pollInterval;

    @Basic
    @Column(name = "appm_name")
    private String appmName;

    @Basic
    @Column(name = "appm_id")
    private Integer appmId;




    @Basic
    @Column(name = "sync_time")
    private Date syncTime;

    @Basic
    @Column(name = "managed")
    private String managed;

    public AppmMonitorList() {
    }

    public AppmMonitorList(String resourceId, String resourceName, String displayName, String project, String pageUrl, String type, String typeShortName, String hostname, String host, String hostIp, String healthStatus, String healthMessage, String availabilityStatus, String availabilityMessage, String pollInterval, Integer appmId, Date syncTime, String managed) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.displayName = displayName;
        this.project = project;
        this.pageUrl = pageUrl;
        this.type = type;
        this.typeShortName = typeShortName;
        this.hostname = hostname;
        this.host = host;
        this.hostIp = hostIp;
        this.healthStatus = healthStatus;
        this.healthMessage = healthMessage;
        this.availabilityStatus = availabilityStatus;
        this.availabilityMessage = availabilityMessage;
        this.pollInterval = pollInterval;
        this.appmId = appmId;
        this.syncTime = syncTime;
        this.managed = managed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeShortName() {
        return typeShortName;
    }

    public void setTypeShortName(String typeShortName) {
        this.typeShortName = typeShortName;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getHealthMessage() {
        return healthMessage;
    }

    public void setHealthMessage(String healthMessage) {
        this.healthMessage = healthMessage;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getAvailabilityMessage() {
        return availabilityMessage;
    }

    public void setAvailabilityMessage(String availabilityMessage) {
        this.availabilityMessage = availabilityMessage;
    }

    public String getPollInterval() {
        return pollInterval;
    }

    public void setPollInterval(String pollInterval) {
        this.pollInterval = pollInterval;
    }

    public String getAppmName() {
        return appmName;
    }

    public void setAppmName(String appmName) {
        this.appmName = appmName;
    }

    public Date getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(Date syncTime) {
        this.syncTime = syncTime;
    }

    public Integer getAppmId() {
        return appmId;
    }

    public void setAppmId(Integer appmId) {
        this.appmId = appmId;
    }

    public String getManaged() {
        return managed;
    }

    public void setManaged(String managed) {
        this.managed = managed;
    }

    @Override
    public String toString() {
        return "AppmMonitorList{" +
                "id=" + id +
                ", resourceId='" + resourceId + '\'' +
                ", resourceName='" + resourceName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", project='" + project + '\'' +
                ", pageUrl='" + pageUrl + '\'' +
                ", type='" + type + '\'' +
                ", typeShortName='" + typeShortName + '\'' +
                ", hostname='" + hostname + '\'' +
                ", host='" + host + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", healthStatus='" + healthStatus + '\'' +
                ", healthMessage='" + healthMessage + '\'' +
                ", availabilityStatus='" + availabilityStatus + '\'' +
                ", availabilityMessage='" + availabilityMessage + '\'' +
                ", pollInterval='" + pollInterval + '\'' +
                ", appmName='" + appmName + '\'' +
                ", appmId=" + appmId +
                ", syncTime=" + syncTime +
                ", managed='" + managed + '\'' +
                '}';
    }
}

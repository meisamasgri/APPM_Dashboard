package ir.mci.appm_dashboard.entity.sync;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sync_result")
public class SyncResult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "sync_response_id", nullable = true )
//    public SyncResponse syncResponse;

//
//    @Column(name = "APPM_ID")
//    public String appmID;
//
//    @Column(name = "DELETED" , columnDefinition = "boolean default false")
//    public boolean deleted;
//
//    @Column(name = "TIME")
//    public Date time;
//
//    @Column(name = "APPM_PORT")
//    public String appmPort;
//
//    @Column(name = "APPM_KEY")
//    public String appmKey;
//
//    @Column(name = "MON_GROUP")
//    public String monGroup;
//
//    @Column(name = "MON_URL")
//    public String monURL;
//
//    @Column(name = "MON_CPU")
//    public String monCPU;
//
//    @Column(name = "MON_RAM")
//    public String monRAM;
//
//    @Column(name = "MON_MAN")
//    public String monMan;
//
//    @Column(name = "MON_APPM")
//    public String monAppm;


    @Column(name = "details_page_url")
    public String detailsPageURL;
    @Column(name = "health_attribute_id")
    public String healthAttributeId;
    @Column(name = "associated_groups")
    public String associatedGroups;
    @Column(name = "resource_name")
    public String resourceName;
    @Column(name = "managed")
    public String managed;
    @Column(name = "health_severity")
    public String healthSeverity;
    @Column(name = "availability_attribute_id")
    public String availabilityAttributeId;
    @Column(name = "availability_message" ,columnDefinition = "LONGTEXT")
    public String availabilityMessage;
    @Column(name = "availability_severity")
    public String availabilitySeverity;
    @Column(name = "type_short_name")
    public String typeShortName;
    @Column(name = "type")
    public String type;
    @Column(name = "description")
    public String description;
    @Column(name = "hostname")
    public String hostname;
    @Column(name = "resource_id")
    public String resourceId;
    @Column(name = "health_message",columnDefinition = "LONGTEXT")
    public String healthMessage;
    @Column(name = "port")
    public String port;
    @Column(name = "display_name")
    public String displayName;
    @Column(name = "rca_page_url")
    public String rcaPageUrl;
    @Column(name = "health_status")
    public String healthStatus;
    @Column(name = "host_ip")
    public String hostIp;
    @Column(name = "image_path")
    public String imagePath;
    @Column(name = "availability_status")
    public String availabilityStatus;
    @Column(name = "last_alarm_type")
    public String lastAlarmType;
    @Column(name = "ports")
    public String ports;
    @Column(name = "host")
    public String host;
}
